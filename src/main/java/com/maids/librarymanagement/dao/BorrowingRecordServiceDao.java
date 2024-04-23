package com.maids.librarymanagement.dao;

import com.maids.librarymanagement.entity.BorrowingRecord;

import java.util.List;

public interface BorrowingRecordServiceDao {
    BorrowingRecord save(BorrowingRecord borrowingRecord);
    BorrowingRecord getByBookIdAndPatronId(Integer bookId, Integer patronId);
    Boolean existByBookIdAndPatronId(Integer bookId, Integer patronId);
    List<BorrowingRecord> getBorrowedRecordsByBookAndPatron(Integer bookId, Integer patronId);
}
