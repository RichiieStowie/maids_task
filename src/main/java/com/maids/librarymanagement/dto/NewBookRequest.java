package com.maids.librarymanagement.dto;

public record NewBookRequest(
        String title,
        String isbn,
        String author,
        String publishedYear
) {
}
