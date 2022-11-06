package com.fwantastic.librarymanagement.controller;

import com.fwantastic.librarymanagement.dto.BookDto;
import com.fwantastic.librarymanagement.model.Book;
import com.fwantastic.librarymanagement.model.mapper.BookMapper;
import com.fwantastic.librarymanagement.service.BookService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/books")
public class BookController {

  private final BookService bookService;

  @PostMapping
  public BookDto create(final @RequestBody Book book) {
    final var bookCreated = bookService.create(book);
    return BookMapper.toBookDto(bookCreated);
  }

  @PutMapping("/{id}")
  public BookDto update(final @RequestBody Book book) {
    final var bookUpdated = bookService.update(book);
    return BookMapper.toBookDto(bookUpdated);
  }

  @GetMapping("/{id}")
  public BookDto findById(final @PathVariable(value = "id") String id) {
    return bookService.findById(id)
        .map(BookMapper::toBookDto)
        .orElseThrow(
            () -> new IllegalArgumentException(String.format("Book ID=[%s] not found", id)));
  }

  @GetMapping
  public List<BookDto> findAll(final @RequestParam(required = false) String title) {
    if (!StringUtils.isEmpty(title)) {
      return bookService.findByTitle(title).stream().map(BookMapper::toBookDto)
          .collect(Collectors.toList());
    }
    return bookService.findAll().stream().map(BookMapper::toBookDto).collect(Collectors.toList());
  }
}
