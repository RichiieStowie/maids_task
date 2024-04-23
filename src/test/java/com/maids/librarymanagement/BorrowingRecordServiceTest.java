package com.maids.librarymanagement;

import com.maids.librarymanagement.dao.BookServiceDao;
import com.maids.librarymanagement.dao.BorrowingRecordServiceDao;
import com.maids.librarymanagement.dao.PatronServiceDao;
import com.maids.librarymanagement.dto.BorrowRecordResponse;
import com.maids.librarymanagement.entity.Book;
import com.maids.librarymanagement.entity.BorrowingRecord;
import com.maids.librarymanagement.entity.Patron;
import com.maids.librarymanagement.exceptions.NotAllowedException;
import com.maids.librarymanagement.mapper.BorrowRecordMapper;
import com.maids.librarymanagement.service.impl.BorrowingRecordServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BorrowingRecordServiceTest {

    private final int bookId = 1;
    private final int patronId = 1;
    @Mock
    private BorrowingRecordServiceDao borrowingRecordServiceDao;
    @Mock
    private BookServiceDao bookServiceDao;
    @Mock
    private PatronServiceDao patronServiceDao;
    @Mock
    private BorrowRecordMapper borrowRecordMapper;
    @InjectMocks
    private BorrowingRecordServiceImpl borrowingRecordService;

    @Test
    void testBorrowBook_NewRecord() {
        Book book = Book.builder()
                .title("Title1")
                .build();
        Patron patron = Patron.builder()
                .name("user 1")
                .build();
        BorrowingRecord borrowingRecord = BorrowingRecord.builder()
                .patron(patron)
                .book(book)
                .build();
        when(bookServiceDao.getById(bookId)).thenReturn(book);
        when(patronServiceDao.getById(patronId)).thenReturn(patron);
        when(borrowingRecordServiceDao.existByBookIdAndPatronId(bookId, patronId)).thenReturn(false);
        when(borrowingRecordServiceDao.save(any())).thenReturn(borrowingRecord);

        BorrowRecordResponse result = borrowingRecordService.borrowBook(bookId, patronId);

        assertNotNull(result);
    }

    @Test
    void testBorrowBook_PreviousNotReturned() {
        Book book = Book.builder()
                .title("Title1")
                .build();
        Patron patron = Patron.builder()
                .name("user 1")
                .build();
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setReturned(false);
        when(bookServiceDao.getById(bookId)).thenReturn(book);
        when(patronServiceDao.getById(patronId)).thenReturn(patron);
        when(borrowingRecordServiceDao.existByBookIdAndPatronId(bookId, patronId)).thenReturn(true);
        when(borrowingRecordServiceDao.getByBookIdAndPatronId(bookId, patronId)).thenReturn(borrowingRecord);

        assertThrows(NotAllowedException.class, () -> borrowingRecordService.borrowBook(bookId, patronId));
    }

}


