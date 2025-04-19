package com.jpacourse.persistance.dao;

import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long> {
    public List<VisitEntity> GetAllVisitsForPatient(long patientId);
    public List<VisitEntity> GetAllPastVisitsForPatient(long patientId);
    void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime time, String description);
}
