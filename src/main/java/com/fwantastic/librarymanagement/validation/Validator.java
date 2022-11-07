package com.fwantastic.librarymanagement.validation;

public interface Validator<T> {

  boolean validate(T input);
}
