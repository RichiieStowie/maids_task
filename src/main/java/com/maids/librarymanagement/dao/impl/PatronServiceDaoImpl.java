package com.maids.librarymanagement.dao.impl;

import com.maids.librarymanagement.dao.PatronServiceDao;
import com.maids.librarymanagement.entity.Patron;
import com.maids.librarymanagement.exceptions.NotFoundException;
import com.maids.librarymanagement.repository.PatronRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatronServiceDaoImpl implements PatronServiceDao {
    public static final String ITEM_NOT_FOUND = "Item not found!";
    private final PatronRepository patronRepository;

    @Override
    public Patron save(Patron patron) {
        return patronRepository.save(patron);
    }

    @Override
    public Patron getById(Integer id) {
        return patronRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ITEM_NOT_FOUND));
    }

    @Override
    public Page<Patron> getAllPatrons(Pageable pageable) {
        return patronRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Integer id) {
        patronRepository.deleteById(id);
    }
}
