package com.maids.librarymanagement.dao.impl;

import com.maids.librarymanagement.dao.BorrowingRecordServiceDao;
import com.maids.librarymanagement.entity.BorrowingRecord;
import com.maids.librarymanagement.exceptions.NotFoundException;
import com.maids.librarymanagement.repository.BorrowingRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowingRecordServiceDaoImpl implements BorrowingRecordServiceDao {
    private final BorrowingRecordRepository borrowingRecordRepository;
    @Override
    public BorrowingRecord save(BorrowingRecord borrowingRecord) {
        return borrowingRecordRepository.save(borrowingRecord);
    }

    @Override
    public BorrowingRecord getByBookIdAndPatronId(Integer bookId, Integer patronId) {
        return borrowingRecordRepository.findByBookIdAndPatronIdAndIsReturned(bookId, patronId, false)
                .orElseThrow(() -> new NotFoundException("Item not Found!"));
    }

    @Override
    public Boolean existByBookIdAndPatronId(Integer bookId, Integer patronId) {
        return borrowingRecordRepository.existsByBookIdAndPatronId(bookId, patronId);
    }

    @Override
    public List<BorrowingRecord> getBorrowedRecordsByBookAndPatron(Integer bookId, Integer patronId) {
        return borrowingRecordRepository.findByBookIdAndPatronId(bookId, patronId);
    }
}
