package com.jpacourse.persistance.dao.impl;

import com.jpacourse.persistance.dao.PatientDao;
import com.jpacourse.persistance.entity.DoctorEntity;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {
    @Override
    public List<VisitEntity> GetAllVisitsForPatient(long patientId) {

        // return (long) entityManager.createQuery("Select count(*) from " + getDomainClassName()).getSingleResult();
        return this.entityManager
                .createQuery("SELECT * FROM Visit WHERE PATIENT_ID = :patientId", VisitEntity.class)
                .setParameter("patientId", patientId)
                .getResultList();
    }

    @Override
    public List<VisitEntity> GetAllPastVisitsForPatient(long patientId) {
        return this.GetAllVisitsForPatient(patientId)
                .stream()
                .filter(visit -> visit.getTime().isBefore(LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    @Override
    public void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime time, String description) {
        PatientEntity patient = findOne(patientId);
        DoctorEntity doctor = entityManager.find(DoctorEntity.class, doctorId);

        VisitEntity visit = new VisitEntity();
        visit.setTime(time);
        visit.setDescription(description);
        visit.setDoctor(doctor);
        visit.setPatient(patient);

        patient.getVisits().add(visit);

        update(patient); // zakładamy, że update używa merge
    }
}
