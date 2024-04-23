package com.maids.librarymanagement;

import com.maids.librarymanagement.dao.PatronServiceDao;
import com.maids.librarymanagement.dto.NewPatronRequest;
import com.maids.librarymanagement.entity.Patron;
import com.maids.librarymanagement.service.impl.PatronServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatronServiceTest {
    @Mock
    private PatronServiceDao patronServiceDao;

    @InjectMocks
    private PatronServiceImpl patronService;

    @Test
    void testCreatePatron() {

        NewPatronRequest patronRequest = new NewPatronRequest("John Doe", "john@example.com", "123456789");
        Patron expectedPatron = Patron.builder()
                .id(1)
                .name("John Doe")
                .email("john@example.com")
                .phoneNumber("123456789")
                .build();

        when(patronServiceDao.save(any())).thenReturn(expectedPatron);

        Patron createdPatron = patronService.createPatron(patronRequest);

        assertEquals(expectedPatron, createdPatron);
        verify(patronServiceDao, times(1)).save(any());
    }

    @Test
    void testUpdatePatronDetails() {

        int patronId = 1;
        NewPatronRequest patronRequest = new NewPatronRequest("Jane Doe", "jane@example.com", "987654321");
        Patron existingPatron = Patron.builder()
                .id(patronId)
                .name("John Doe")
                .email("john@example.com")
                .phoneNumber("123456789")
                .build();

        Patron updatedPatron = Patron.builder()
                .id(patronId)
                .name("Jane Doe")
                .email("jane@example.com")
                .phoneNumber("987654321")
                .build();


        when(patronServiceDao.getById(patronId)).thenReturn(existingPatron);
        when(patronServiceDao.save(any())).thenReturn(updatedPatron);

        Patron result = patronService.updatePatronDetails(patronId, patronRequest);

        assertEquals(updatedPatron, result);
        verify(patronServiceDao, times(1)).getById(patronId);
        verify(patronServiceDao, times(1)).save(any());
    }

    @Test
    void testFetchAllPatrons() {

        List<Patron> patrons = new ArrayList<>();
        patrons.add(Patron.builder()
                .id(1)
                .name("John Doe")
                .email("john@example.com")
                .phoneNumber("123456789")
                .build());

        patrons.add(Patron.builder()
                .id(2)
                .name("Jane Doe")
                .email("jane@example.com")
                .phoneNumber("987654321")
                .build());
        Page<Patron> page = new PageImpl<>(patrons);

        when(patronServiceDao.getAllPatrons(any(Pageable.class))).thenReturn(page);
        Pageable pageable = Pageable.unpaged();

        Page<Patron> result = patronService.fetchAllPatrons(pageable);

        assertEquals(page, result);
        verify(patronServiceDao, times(1)).getAllPatrons(any(Pageable.class));
    }

    @Test
    void testDeletePatronById() {
        int patronId = 1;

        patronService.deletePatronById(patronId);

        verify(patronServiceDao, times(1)).deleteById(patronId);
    }

    @Test
    void testGetPatron() {

        int patronId = 1;
        Patron expectedPatron = Patron.builder()
                .id(patronId)
                .name("John Doe")
                .email("john@example.com")
                .phoneNumber("123456789")
                .build();


        when(patronServiceDao.getById(patronId)).thenReturn(expectedPatron);

        Patron result = patronService.getPatron(patronId);

        assertEquals(expectedPatron, result);
        verify(patronServiceDao, times(1)).getById(patronId);
    }
}
