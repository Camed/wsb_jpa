package com.jpacourse.persistance.dao;

import com.jpacourse.persistance.dao.impl.PatientDaoImpl;
import com.jpacourse.persistance.entity.DoctorEntity;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import com.jpacourse.persistance.enums.Specialization;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatientDaoTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PatientDaoImpl patientDao;
    @Test
    public void testAddVisitToPatient() {
        // given
        PatientEntity patient = new PatientEntity();
        patient.setFirstName("Jan");
        patient.setLastName("Kowalski");
        patient.setTelephoneNumber("123456789");
        patient.setPatientNumber("P123");

        DoctorEntity doctor = new DoctorEntity();
        doctor.setFirstName("Anna");
        doctor.setLastName("Nowak");
        doctor.setDoctorNumber("D456");
        doctor.setTelephoneNumber("987654321");
        doctor.setSpecialization(Specialization.OCULIST);

        entityManager.persist(doctor);
        entityManager.persist(patient);
        entityManager.flush();

        // when
        patientDao.addVisitToPatient(patient.getId(), doctor.getId(), LocalDateTime.now(), "Badanie kontrolne");

        // then
        PatientEntity updated = patientDao.findOne(patient.getId());
        List<VisitEntity> visits = updated.getVisits();
        assertEquals(1, visits.size());
        assertEquals("Badanie kontrolne", visits.get(0).getDescription());
        assertEquals(doctor.getId(), visits.get(0).getDoctor().getId());
    }
}
