package com.jpacourse.service.impl;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.persistance.dao.PatientDao;
import com.jpacourse.persistance.dao.VisitDao;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import com.jpacourse.service.PatientService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {
    private final PatientDao patientDao;
    private final VisitDao visitDao;

    @Autowired
    public PatientServiceImpl(PatientDao patientDao, VisitDao visitDao){
        this.patientDao = patientDao;
        this.visitDao = visitDao;
    }
    @Override
    public PatientTO findById(Long id) {
        try {
            final PatientEntity entity = patientDao.findOne(id);
            PatientTO patientTO = PatientMapper.mapToTO(entity, null);

            List<VisitEntity> pastVisits = visitDao.getAllPastVisitsForPatient(id);
            patientTO.setPastVisits(pastVisits);

            return patientTO;
        }
        catch (Exception ex) { throw ex; }
    }

    @Override
    public PatientTO getPatientWithPastVisits(Long patientId) {
        PatientEntity patientEntity = patientDao.findOne(patientId);
        List<VisitEntity> pastVisits = visitDao.getAllPastVisitsForPatient(patientId);
        return PatientMapper.mapToTO(patientEntity, pastVisits);
    }
}
