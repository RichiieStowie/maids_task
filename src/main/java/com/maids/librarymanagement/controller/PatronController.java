package com.maids.librarymanagement.controller;

import com.maids.librarymanagement.dto.NewBookRequest;
import com.maids.librarymanagement.dto.NewPatronRequest;
import com.maids.librarymanagement.entity.Book;
import com.maids.librarymanagement.entity.Patron;
import com.maids.librarymanagement.service.PatronService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/api/patrons")
public class PatronController {
    private final PatronService patronService;

    @PostMapping("")
    public ResponseEntity<Patron> addNewPatron(@RequestBody NewPatronRequest newPatronRequest) {
        Patron patron = patronService.createPatron(newPatronRequest);
        return new ResponseEntity<>(patron, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Patron> getPatronDetails(@PathVariable Integer id) {
        Patron patron = patronService.getPatron(id);
        return new ResponseEntity<>(patron, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Patron> updatePatronDetails(@PathVariable Integer id, @RequestBody NewPatronRequest newPatronRequest) {
        Patron patron = patronService.updatePatronDetails(id, newPatronRequest);
        return new ResponseEntity<>(patron, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Integer id) {
        patronService.deletePatronById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Page<Patron>> getPatrons(@SortDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        var patrons = patronService.fetchAllPatrons(pageable);
        return new ResponseEntity<>(patrons, HttpStatus.OK);
    }
}
