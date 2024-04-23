package com.maids.librarymanagement.dao;

import com.maids.librarymanagement.entity.Patron;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatronServiceDao {
    Patron save(Patron patron);
    Patron getById(Integer id);
    Page<Patron> getAllPatrons(Pageable pageable);
    void deleteById(Integer id);
}
