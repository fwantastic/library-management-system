package com.fwantastic.librarymanagement.dao;

import com.fwantastic.librarymanagement.model.Book;
import java.util.List;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface BookDao extends PagingAndSortingRepository<Book, String> {
  List<Book> findByTitle(String title);
}
