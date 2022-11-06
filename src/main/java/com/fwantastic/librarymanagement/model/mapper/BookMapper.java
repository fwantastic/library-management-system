package com.fwantastic.librarymanagement.model.mapper;

import com.fwantastic.librarymanagement.dto.BookDto;
import com.fwantastic.librarymanagement.model.Book;

public class BookMapper {

  public static Book toBook(final BookDto dto) {
    return Book.builder().id(dto.getId())
        .title(dto.getTitle())
        .rating(dto.getRating())
        .author(dto.getAuthor())
        .build();
  }

  public static BookDto toBookDto(final Book book) {
    return BookDto.builder().id(book.getId())
        .title(book.getTitle())
        .rating(book.getRating())
        .author(book.getAuthor())
        .lastModificationDate(book.getLastModificationDate())
        .build();
  }

}
