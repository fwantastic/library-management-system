package com.fwantastic.librarymanagement.controller;

import com.fwantastic.librarymanagement.dto.BookDto;
import com.fwantastic.librarymanagement.model.mapper.BookMapper;
import com.fwantastic.librarymanagement.service.BookService;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(BookController.BOOK_PATH)
public class BookController {

  protected static final String BOOK_PATH = "/books";
  private static final Logger logger = LoggerFactory.getLogger(BookController.class);
  private static final String BOOK_ID_PATH = "/{id}";

  private final BookService bookService;

  /**
   * When the CompletableFuture is returned, it triggers Servlet 3.0 asynchronous processing
   * feature which the execution of the CompletableFuture will be executed in other thread such that
   * the server thread that handle the HTTP request can be free up as quickly as possible to process
   * other HTTP requests.
   * <p>
   * Reference:
   * https://stackoverflow.com/questions/58505549/how-does-spring-get-the-result-from-an-endpoint-that-returns-completablefuture-o
   */
  @PostMapping
  public CompletableFuture<BookDto> create(final @RequestBody BookDto bookDto) {
    logger.info("Received create book request");
    final var book = BookMapper.toBook(bookDto);
    return bookService.create(book).thenApply(BookMapper::toBookDto);
  }

  @PutMapping(BOOK_ID_PATH)
  public CompletableFuture<BookDto> update(final @RequestBody BookDto bookDto) {
    logger.info("Received update book request");
    final var book = BookMapper.toBook(bookDto);
    return bookService.update(book).thenApply(BookMapper::toBookDto);
  }

  @GetMapping(BOOK_ID_PATH)
  public CompletableFuture<BookDto> findById(final @PathVariable(value = "id") String id) {
    logger.info("Received find book by id request");
    return bookService.findById(id)
        .thenApply(book ->
            book.map(BookMapper::toBookDto)
                .orElseThrow(
                    () -> new IllegalArgumentException(String.format("Book ID=[%s] not found", id)))
        )
        ;
  }

  @GetMapping
  public CompletableFuture<List<BookDto>> findAll(
      final @RequestParam(required = false) String title) {
    logger.info("Received find books request");
    if (!StringUtils.isEmpty(title)) {
      return bookService.findByTitle(title).thenApply(
          books -> books.stream().map(BookMapper::toBookDto).collect(Collectors.toList()));
    }
    return bookService.findAll()
        .thenApply(books -> books.stream().map(BookMapper::toBookDto).collect(Collectors.toList()));

  }
}
