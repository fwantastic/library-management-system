package com.fwantastic.librarymanagement.config;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import java.time.Instant;

public class InstantTypeConverter implements DynamoDBTypeConverter<String, Instant> {

  @Override
  public String convert(final Instant instant) {
    return instant.toString();
  }

  @Override
  public Instant unconvert(final String s) {
    return Instant.parse(s);
  }
}
