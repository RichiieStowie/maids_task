package com.maids.librarymanagement.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

public record ErrorResponse(
        HttpStatus status,
        String message
) {
}
