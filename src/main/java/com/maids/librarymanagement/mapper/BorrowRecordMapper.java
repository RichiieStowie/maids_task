package com.maids.librarymanagement.mapper;

import com.maids.librarymanagement.dto.BorrowRecordResponse;
import com.maids.librarymanagement.entity.BorrowingRecord;

import java.util.function.Function;

public class BorrowRecordMapper {

    public static Function<BorrowingRecord, BorrowRecordResponse> toSimpleResponse = (borrowingRecord -> new BorrowRecordResponse(borrowingRecord.getPatron().getName(), borrowingRecord.getBook().getTitle(), borrowingRecord.getCreatedAt(), borrowingRecord.isReturned()));
}
