package com.maids.librarymanagement.service.impl;

import com.maids.librarymanagement.aop.annotation.TrackExecutionTime;
import com.maids.librarymanagement.dao.BookServiceDao;
import com.maids.librarymanagement.dto.NewBookRequest;
import com.maids.librarymanagement.entity.Book;
import com.maids.librarymanagement.service.BookService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookServiceDao bookServiceDao;

    @Override
    @TrackExecutionTime
    @CachePut(value = "allBooks", key = "'all'")
    public Book createNewBook(NewBookRequest bookRequest) {
        Book book = Book.builder()
                .title(bookRequest.title())
                .author(bookRequest.author())
                .publicationYear(bookRequest.publishedYear())
                .isbn(bookRequest.isbn())
                .build();

        return bookServiceDao.save(book);
    }

    @Override
    @Transactional
    @TrackExecutionTime
    @CachePut(value = "books", key = "#id")
    public Book updateBookDetails(Integer id, NewBookRequest bookRequest) {
        Book book = bookServiceDao.getById(id);
        if (StringUtils.hasText(bookRequest.author())) {
            book.setAuthor(bookRequest.author());
        }
        if (StringUtils.hasText(bookRequest.isbn())) {
            book.setIsbn(bookRequest.isbn());
        }
        if (StringUtils.hasText(bookRequest.title())) {
            book.setTitle(bookRequest.title());
        }
        if (StringUtils.hasText(bookRequest.publishedYear())) {
            book.setPublicationYear(bookRequest.publishedYear());
        }

        return bookServiceDao.save(book);
    }

    @Override
    @TrackExecutionTime
    public Page<Book> fetchAllBooks(Pageable pageable) {
        var books = bookServiceDao.getAllBooks(pageable);

        return new PageImpl<>(books.getContent(), books.getPageable(), books.getTotalElements());
    }

    @Override
    @CacheEvict(value = "books", key = "#id")
    public void deleteBookById(Integer id) {
        bookServiceDao.deleteById(id);
    }

    @Override
    @TrackExecutionTime
    @Cacheable(value = "books", key = "#id")
    public Book getBook(Integer id) {
        return bookServiceDao.getById(id);
    }
}
