package com.fwantastic.librarymanagement.service;

import com.fwantastic.librarymanagement.dao.BookDao;
import com.fwantastic.librarymanagement.exception.BadBookException;
import com.fwantastic.librarymanagement.format.BookFormatter;
import com.fwantastic.librarymanagement.model.Book;
import com.fwantastic.librarymanagement.validation.BookValidator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

  private final BookDao bookDao;

  @Async
  @Override
  public CompletableFuture<Book> create(final Book book) {
    logger.info("Create a book [{}]", book);
    Book formattedBook = format(book);

    if (!validate(formattedBook)) {
      logger.error("Failed validation for book [{}]", formattedBook);
      return CompletableFuture.failedFuture(new BadBookException("Bad book"));
    }

    return CompletableFuture.completedFuture(bookDao.save(formattedBook));
  }

  @Async
  @Override
  public CompletableFuture<Book> update(final Book book) {
    logger.info("Update a book [{}]", book);
    Book formattedBook = format(book);

    if (!validate(formattedBook)) {
      logger.error("Failed validation for book [{}]", formattedBook);
      return CompletableFuture.failedFuture(new BadBookException("Bad book"));
    }

    return CompletableFuture.completedFuture(bookDao.save(formattedBook));
  }

  @Async
  @Override
  public CompletableFuture<Optional<Book>> findById(final String id) {
    logger.info("Find a book with id=[{}]", id);
    return CompletableFuture.completedFuture(bookDao.findById(id));
  }

  /**
   * spring-data-dynamodb does not support case sensitive search
   */
  @Async
  @Override
  public CompletableFuture<List<Book>> findByTitle(final String title, final Pageable pageable) {
    return CompletableFuture.completedFuture(bookDao.findByTitle(title, pageable).getContent());
  }

  @Async
  @Override
  public CompletableFuture<List<Book>> findAll(final Pageable pageable) {
    logger.info("Find all books");
    return CompletableFuture.completedFuture(bookDao.findAll(pageable).getContent());
  }

  public boolean validate(final Book book) {
    return Stream.of(BookValidator.values()).allMatch(validator -> validator.validate(book));
  }

  private Book format(final Book book) {
    Book formatted = book.toBuilder().build();

    for (BookFormatter formatter : BookFormatter.values()) {
      formatted = formatter.format(formatted);
    }

    logger.info("Book is formatted. [{}]", formatted);

    return formatted;
  }
}
