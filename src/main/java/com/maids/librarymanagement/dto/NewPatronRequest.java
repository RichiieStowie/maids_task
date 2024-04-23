package com.maids.librarymanagement.dto;

public record NewPatronRequest(
        String name,
        String phoneNo,
        String email
) {
}
