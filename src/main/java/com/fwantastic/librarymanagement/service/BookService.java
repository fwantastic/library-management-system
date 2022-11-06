package com.fwantastic.librarymanagement.service;

import com.fwantastic.librarymanagement.model.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {

  Book create(Book book);

  Book update(Book book);

  Optional<Book> findById(String id);

  List<Book> findByTitle(String title);

  List<Book> findAll();
}
