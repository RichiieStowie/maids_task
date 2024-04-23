package com.maids.librarymanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BorrowingRecord extends BaseEntity {

    @ManyToOne
    private Book book;

    @ManyToOne
    private Patron patron;

    private boolean isReturned;



}
