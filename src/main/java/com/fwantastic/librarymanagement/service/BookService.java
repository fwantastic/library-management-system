package com.fwantastic.librarymanagement.service;

import com.fwantastic.librarymanagement.model.Book;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.springframework.data.domain.Pageable;

public interface BookService {

  CompletableFuture<Book> create(Book book);

  CompletableFuture<Book> update(Book book);

  CompletableFuture<Optional<Book>> findById(String id);

  CompletableFuture<List<Book>> findByTitle(String title, Pageable pageable);

  CompletableFuture<List<Book>> findAll(Pageable pageable);
}
