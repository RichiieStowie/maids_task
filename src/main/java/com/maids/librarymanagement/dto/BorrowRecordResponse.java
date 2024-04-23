package com.maids.librarymanagement.dto;

import java.time.Instant;

public record BorrowRecordResponse(
        String patronName,
        String bookTitle,
        Instant borrowDate,
        Boolean isReturned
) {
}
