package com.fwantastic.librarymanagement.dto;


import java.time.Instant;
import lombok.Builder;
import lombok.Getter;

/**
 * Spring Boot uses Jackson for Object <-> JSON conversion and Jackson sets fields via reflection,
 * so setters are not needed
 */
@Getter
@Builder(toBuilder = true)
public class BookDto {

  private String id;
  private String title;
  private int rating;
  private String author;
  private Instant lastModificationDate;

}
