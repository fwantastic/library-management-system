package com.fwantastic.librarymanagement.service;

import com.fwantastic.librarymanagement.dao.BookDao;
import com.fwantastic.librarymanagement.model.Book;
import java.util.List;
import java.util.Optional;
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
  public Book create(final Book book) {
    logger.info("Create a book [{}]", book);
    return bookDao.save(book.updateLastModificationDate());
  }

  @Override
  public Book update(final Book book) {
    logger.info("Update a book [{}]", book);
    return bookDao.save(book.updateLastModificationDate());
  }

  @Override
  public Optional<Book> findById(final String id) {
    logger.info("Find a book with id=[{}]", id);
    return bookDao.findById(id);
  }

  /**
   * spring-data-dynamodb does not support case sensitive search
   */
  @Override
  public List<Book> findByTitle(final String title) {
    return bookDao.findByTitle(title);
  }

  @Override
  public List<Book> findAll() {
    logger.info("find all books");
    return (List<Book>) bookDao.findAll();
  }
}
