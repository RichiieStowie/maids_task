package com.maids.librarymanagement.controller;

import com.maids.librarymanagement.dto.BorrowRecordResponse;
import com.maids.librarymanagement.service.BorrowingRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/api/borrow")
public class BorrowController {
    private final BorrowingRecordService borrowingRecordService;

    @PostMapping("{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowRecordResponse> borrowBook(@PathVariable Integer bookId, @PathVariable Integer patronId) {
        var response = borrowingRecordService.borrowBook(bookId, patronId);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowRecordResponse> returnBook(@PathVariable Integer bookId, @PathVariable Integer patronId) {
        var response = borrowingRecordService.returnBook(bookId, patronId);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
