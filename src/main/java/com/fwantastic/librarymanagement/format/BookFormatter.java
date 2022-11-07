package com.fwantastic.librarymanagement.format;

import com.fwantastic.librarymanagement.model.Book;
import java.time.Instant;

public enum BookFormatter implements Formatter<Book> {

  LAST_MODIFICATION_DATE {
    @Override
    public Book format(final Book book) {
      return book.toBuilder()
          .lastModificationDate(Instant.now()).build();
    }
  }
}
