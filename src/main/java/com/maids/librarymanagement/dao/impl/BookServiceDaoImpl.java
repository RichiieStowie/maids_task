package com.maids.librarymanagement.dao.impl;

import com.maids.librarymanagement.dao.BookServiceDao;
import com.maids.librarymanagement.entity.Book;
import com.maids.librarymanagement.exceptions.NotFoundException;
import com.maids.librarymanagement.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceDaoImpl implements BookServiceDao {
    private final BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getById(Integer id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book not found!"));
    }

    @Override
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Integer id) {
        bookRepository.deleteById(id);
    }
}
