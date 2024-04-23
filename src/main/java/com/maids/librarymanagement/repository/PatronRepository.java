package com.maids.librarymanagement.repository;

import com.maids.librarymanagement.entity.Book;
import com.maids.librarymanagement.entity.Patron;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Integer> {
    Page<Patron> findAll(Pageable pageable);
}
