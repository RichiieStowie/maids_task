package com.maids.librarymanagement.repository;

import com.maids.librarymanagement.entity.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Integer> {
    Optional<BorrowingRecord> findByBookIdAndPatronIdAndIsReturned(Integer bookId, Integer patronId, Boolean isReturned);

    Boolean existsByBookIdAndPatronId(Integer bookId, Integer patronId);

    List<BorrowingRecord> findByBookIdAndPatronId(Integer bookId, Integer patronId);
}
