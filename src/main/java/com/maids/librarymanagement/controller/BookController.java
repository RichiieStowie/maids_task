package com.maids.librarymanagement.controller;

import com.maids.librarymanagement.dto.NewBookRequest;
import com.maids.librarymanagement.entity.Book;
import com.maids.librarymanagement.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/api/books")
public class BookController {
    private final BookService bookService;

    @PostMapping("")
    public ResponseEntity<Book> addNewBook(@RequestBody NewBookRequest bookRequest) {
        Book book = bookService.createNewBook(bookRequest);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<Page<Book>> getAllBooks(@SortDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        var books = bookService.fetchAllBooks(pageable);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> getBook(@PathVariable Integer id) {
        Book book = bookService.getBook(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Book> updateBookDetails(@PathVariable Integer id, @RequestBody NewBookRequest bookRequest) {
        Book book = bookService.updateBookDetails(id, bookRequest);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        bookService.deleteBookById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
