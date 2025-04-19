package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistance.dao.PatientDao;
import com.jpacourse.persistance.dao.VisitDao;
import com.jpacourse.persistance.entity.DoctorEntity;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import com.jpacourse.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class PatientServiceTest {

    @InjectMocks
    private PatientServiceImpl patientService;

    @Mock
    private PatientDao patientDao;

    @Mock
    private VisitDao visitDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById_shouldReturnPatientTOWithPastVisits() {
        // Given
        Long patientId = 1L;
        PatientEntity patient = new PatientEntity();
        patient.setId(patientId);
        patient.setFirstName("Jan");
        patient.setLastName("Kowalski");

        VisitEntity pastVisit = new VisitEntity();
        pastVisit.setId(100L);
        pastVisit.setPatient(patient);
        pastVisit.setTime(LocalDateTime.now().minusDays(2));

        when(patientDao.findOne(patientId)).thenReturn(patient);
        when(visitDao.getAllPastVisitsForPatient(patientId)).thenReturn(List.of(pastVisit));

        // When
        PatientTO result = patientService.findById(patientId);

        // Then
        assertNotNull(result);
        assertEquals("Jan", result.getFirstName());
        assertEquals(1, result.getPastVisits().size());
        assertEquals(100L, result.getPastVisits().get(0).getId());
    }
}
