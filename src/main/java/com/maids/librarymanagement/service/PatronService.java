package com.maids.librarymanagement.service;


import com.maids.librarymanagement.dto.NewPatronRequest;
import com.maids.librarymanagement.entity.Patron;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatronService {
    Patron createPatron(NewPatronRequest newPatronRequest);
    Patron updatePatronDetails(Integer id, NewPatronRequest patronRequest);
    Page<Patron> fetchAllPatrons(Pageable pageable);
    void deletePatronById(Integer id);
    Patron getPatron(Integer id);
}
