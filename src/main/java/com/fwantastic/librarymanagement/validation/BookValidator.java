package com.fwantastic.librarymanagement.validation;

import com.fwantastic.librarymanagement.model.Book;
import org.apache.commons.lang3.StringUtils;

public enum BookValidator implements Validator<Book> {
  TITLE {
    @Override
    public boolean validate(final Book book) {
      final var title = book.getTitle();
      if (StringUtils.isBlank(title)) {
        return false;
      } else if (title.length() > 100) {
        return false;
      }
      return true;
    }
  },
  RATING {
    @Override
    public boolean validate(final Book book) {
      final var rating = book.getRating();
      if (rating < 0 || rating > 5) {
        return false;
      }
      return true;
    }
  },
  AUTHOR {
    @Override
    public boolean validate(final Book book) {
      final var author = book.getAuthor();
      if (StringUtils.isBlank(author)) {
        return false;
      }
      return true;
    }
  },
  LAST_MODIFICATION_DATE {
    @Override
    public boolean validate(final Book book) {
      return book.getLastModificationDate() != null;
    }
  }
}
