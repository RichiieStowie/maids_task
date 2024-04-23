package com.maids.librarymanagement.service.impl;

import com.maids.librarymanagement.aop.annotation.TrackExecutionTime;
import com.maids.librarymanagement.dao.PatronServiceDao;
import com.maids.librarymanagement.dto.NewPatronRequest;
import com.maids.librarymanagement.entity.Patron;
import com.maids.librarymanagement.service.PatronService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class PatronServiceImpl implements PatronService {
    private final PatronServiceDao patronServiceDao;

    @Override
    @TrackExecutionTime
    public Patron createPatron(NewPatronRequest newPatronRequest) {
        Patron patron = Patron.builder()
                .name(newPatronRequest.name())
                .email(newPatronRequest.email())
                .phoneNumber(newPatronRequest.phoneNo())
                .build();
        return patronServiceDao.save(patron);
    }

    @Override
    @TrackExecutionTime
    @Transactional
    public Patron updatePatronDetails(Integer id, NewPatronRequest patronRequest) {
        Patron patron = patronServiceDao.getById(id);
        if (StringUtils.hasText(patronRequest.email())) {
            patron.setEmail(patronRequest.email());
        }
        if (StringUtils.hasText(patronRequest.phoneNo())) {
            patron.setPhoneNumber(patronRequest.phoneNo());
        }
        if (StringUtils.hasText(patronRequest.name())) {
            patron.setName(patronRequest.name());
        }
        return patronServiceDao.save(patron);
    }

    @Override
    public Page<Patron> fetchAllPatrons(Pageable pageable) {
        var page = patronServiceDao.getAllPatrons(pageable);
        return new PageImpl<>(page.getContent(), pageable, page.getTotalElements());
    }

    @Override
    public void deletePatronById(Integer id) {
        patronServiceDao.deleteById(id);
    }

    @Override
    public Patron getPatron(Integer id) {
        return patronServiceDao.getById(id);
    }
}
