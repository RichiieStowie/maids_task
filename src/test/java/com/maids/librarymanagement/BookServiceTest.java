package com.maids.librarymanagement;

import com.maids.librarymanagement.dao.BookServiceDao;
import com.maids.librarymanagement.dto.NewBookRequest;
import com.maids.librarymanagement.entity.Book;
import com.maids.librarymanagement.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookServiceDao bookServiceDao;

    @InjectMocks
    private BookServiceImpl bookService;



    @Test
    void testCreateNewBook() {
        NewBookRequest bookRequest = new NewBookRequest("Title", "Author", "2022", "ISBN");
        Book expectedBook = Book.builder()
                .title("Old Title")
                .author("Old Author")
                .publicationYear("2022")
                .isbn("ISBN")
                .build();

        when(bookServiceDao.save(any())).thenReturn(expectedBook);

        Book createdBook = bookService.createNewBook(bookRequest);

        assertEquals(expectedBook, createdBook);
        verify(bookServiceDao, times(1)).save(any());
    }

    @Test
    void testUpdateBookDetails() {

        int bookId = 1;
        NewBookRequest bookRequest = new NewBookRequest("New Title", "New Author", "2023", "New ISBN");
        Book existingBook = Book.builder()
                .id(bookId)
                .title("Old Title")
                .author("Old Author")
                .publicationYear("2022")
                .isbn("Old ISBN")
                .build();

        Book updatedBook = Book.builder()
                .id(bookId)
                .title("New Title")
                .author("New Author")
                .publicationYear("2023")
                .isbn("New ISBN")
                .build();

        when(bookServiceDao.getById(bookId)).thenReturn(existingBook);
        when(bookServiceDao.save(any())).thenReturn(updatedBook);

        Book result = bookService.updateBookDetails(bookId, bookRequest);

        assertEquals(updatedBook, result);
        verify(bookServiceDao, times(1)).getById(bookId);
        verify(bookServiceDao, times(1)).save(any());
    }

    @Test
    void testFetchAllBooks() {
        List<Book> books = new ArrayList<>();
        Book book1 = Book.builder()
                .id(1)
                .title("Title1")
                .author("Author1")
                .publicationYear("2022")
                .isbn("ISBN1")
                .build();

        Book book2 = Book.builder()
                .id(2)
                .title("Title2")
                .author("Author2")
                .publicationYear("2023")
                .isbn("ISBN2")
                .build();
        books.add(book1);
        books.add(book2);
        Page<Book> page = new PageImpl<>(books);

        when(bookServiceDao.getAllBooks(any(Pageable.class))).thenReturn(page);
        Pageable pageable = Pageable.unpaged();

        Page<Book> result = bookService.fetchAllBooks(pageable);

        assertEquals(page, result);
        verify(bookServiceDao, times(1)).getAllBooks(any(Pageable.class));
    }

    @Test
    void testDeleteBookById() {
        int bookId = 1;

        bookService.deleteBookById(bookId);

        verify(bookServiceDao, times(1)).deleteById(bookId);
    }

    @Test
    void testGetBook() {
        int bookId = 1;
        Book expectedBook = Book.builder()
                .id(bookId)
                .title("Old Title")
                .author("Old Author")
                .publicationYear("2022")
                .isbn("Old ISBN")
                .build();

        when(bookServiceDao.getById(bookId)).thenReturn(expectedBook);

        Book result = bookService.getBook(bookId);

        assertEquals(expectedBook, result);
        verify(bookServiceDao, times(1)).getById(bookId);
    }
}