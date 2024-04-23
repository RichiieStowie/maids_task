package com.maids.librarymanagement.service;

import com.maids.librarymanagement.dto.BorrowRecordResponse;
import com.maids.librarymanagement.entity.BorrowingRecord;

public interface BorrowingRecordService {
    BorrowRecordResponse borrowBook(Integer bookId, Integer patronId);
    BorrowRecordResponse returnBook(Integer bookId, Integer patronId);
}
