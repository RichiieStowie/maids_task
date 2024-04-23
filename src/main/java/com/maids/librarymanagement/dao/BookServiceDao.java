package com.maids.librarymanagement.dao;

import com.maids.librarymanagement.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookServiceDao {
    Book save(Book book);
    Book getById(Integer id);
    Page<Book> getAllBooks(Pageable pageable);
    void deleteById(Integer id);
}
