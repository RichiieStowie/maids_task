package com.maids.librarymanagement.service;

import com.maids.librarymanagement.dto.NewBookRequest;
import com.maids.librarymanagement.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    Book createNewBook(NewBookRequest bookRequest);
    Book updateBookDetails(Integer id, NewBookRequest bookRequest);
    Page<Book> fetchAllBooks(Pageable pageable);
    void deleteBookById(Integer id);
    Book getBook(Integer id);
}
