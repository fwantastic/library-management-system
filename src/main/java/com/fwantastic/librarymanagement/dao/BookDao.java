package com.fwantastic.librarymanagement.dao;

import com.fwantastic.librarymanagement.model.Book;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@EnableScanCount
@Repository
public interface BookDao extends PagingAndSortingRepository<Book, String> {

  Page<Book> findByTitle(String title, Pageable pageable);

  Page<Book> findAll(Pageable pageable);

}
