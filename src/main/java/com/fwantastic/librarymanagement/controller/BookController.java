package com.fwantastic.librarymanagement.controller;

import com.fwantastic.librarymanagement.model.Book;
import com.fwantastic.librarymanagement.service.BookService;
import java.util.List;
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
  public Book create(final @RequestBody Book book) {
    return bookService.create(book);
  }

  @PutMapping("/{id}")
  public Book update(final @RequestBody Book book) {
    return bookService.update(book);
  }

  @GetMapping("/{id}")
  public Book findById(final @PathVariable(value = "id") String id) {
    return bookService.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("book not found"));
  }

  @GetMapping
  public List<Book> findAll(final @RequestParam(required = false) String title) {
    if (!StringUtils.isEmpty(title)) {
      return bookService.findByTitle(title);
    }
    return bookService.findAll();
  }
}
