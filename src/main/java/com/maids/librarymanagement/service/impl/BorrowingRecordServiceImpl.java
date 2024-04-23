package com.maids.librarymanagement.service.impl;

import com.maids.librarymanagement.aop.annotation.TrackExecutionTime;
import com.maids.librarymanagement.dao.BookServiceDao;
import com.maids.librarymanagement.dao.BorrowingRecordServiceDao;
import com.maids.librarymanagement.dao.PatronServiceDao;
import com.maids.librarymanagement.dto.BorrowRecordResponse;
import com.maids.librarymanagement.entity.Book;
import com.maids.librarymanagement.entity.BorrowingRecord;
import com.maids.librarymanagement.entity.Patron;
import com.maids.librarymanagement.exceptions.NotAllowedException;
import com.maids.librarymanagement.exceptions.NotFoundException;
import com.maids.librarymanagement.mapper.BorrowRecordMapper;
import com.maids.librarymanagement.service.BorrowingRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowingRecordServiceImpl implements BorrowingRecordService {
    private final BorrowingRecordServiceDao borrowingRecordServiceDao;
    private final BookServiceDao bookServiceDao;
    private final PatronServiceDao patronServiceDao;

    @Override
    @Transactional
    @TrackExecutionTime
    public BorrowRecordResponse borrowBook(Integer bookId, Integer patronId) {
        var book = bookServiceDao.getById(bookId);
        var patron = patronServiceDao.getById(patronId);
        boolean isRecordPresent = borrowingRecordServiceDao.existByBookIdAndPatronId(bookId, patronId);
        if (!isRecordPresent) {
            var record = borrowingRecordServiceDao.save(createNewRecord(patron, book));

            return BorrowRecordMapper.toSimpleResponse.apply(record);
        }

        List<BorrowingRecord> records = borrowingRecordServiceDao.getBorrowedRecordsByBookAndPatron(bookId, patronId);
        if (isAnyBookNotReturned(records)) {
            throw new NotAllowedException("You must return previous book borrowed!");
        }

        var record = borrowingRecordServiceDao.save(createNewRecord(patron, book));
        return BorrowRecordMapper.toSimpleResponse.apply(record);
    }

    private boolean isAnyBookNotReturned(List<BorrowingRecord> records) {
        return records.stream()
                .anyMatch(it -> !it.isReturned());
    }

    @Override
    @Transactional
    @TrackExecutionTime
    public BorrowRecordResponse returnBook(Integer bookId, Integer patronId) {
        boolean isRecordPresent = borrowingRecordServiceDao.existByBookIdAndPatronId(bookId, patronId);
        if (!isRecordPresent) {
            throw new NotFoundException("Record is not found!");
        }

        var record = borrowingRecordServiceDao.getByBookIdAndPatronId(bookId, patronId);
        record.setReturned(true);

        return BorrowRecordMapper.toSimpleResponse.apply(borrowingRecordServiceDao.save(record));
    }

    private BorrowingRecord createNewRecord(Patron patron, Book book) {
        return BorrowingRecord.builder()
                .patron(patron)
                .book(book)
                .isReturned(false)
                .build();
    }
}
