package com.fwantastic.librarymanagement.service;

import com.fwantastic.librarymanagement.dao.BookDao;
import com.fwantastic.librarymanagement.model.Book;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

  private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

  private final BookDao bookDao;

  @Override
  public CompletableFuture<Book> create(final Book book) {
    logger.info("Create a book [{}]", book);
    return CompletableFuture.completedFuture(bookDao.save(book.updateLastModificationDate()));
  }

  @Override
  public CompletableFuture<Book> update(final Book book) {
    logger.info("Update a book [{}]", book);
    return CompletableFuture.completedFuture(bookDao.save(book.updateLastModificationDate()));
  }

  @Override
  public CompletableFuture<Optional<Book>> findById(final String id) {
    logger.info("Find a book with id=[{}]", id);
    return CompletableFuture.completedFuture(bookDao.findById(id));
  }

  /**
   * spring-data-dynamodb does not support case sensitive search
   */
  @Override
  public CompletableFuture<List<Book>> findByTitle(final String title) {
    return CompletableFuture.completedFuture(bookDao.findByTitle(title));
  }

  @Override
  public CompletableFuture<List<Book>> findAll() {
    logger.info("find all books");
    return CompletableFuture.completedFuture((List<Book>) bookDao.findAll());
  }
}
