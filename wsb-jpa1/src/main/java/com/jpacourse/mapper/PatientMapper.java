package com.jpacourse.mapper;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import com.jpacourse.rest.exception.EntityNotFoundException;

import java.util.List;

public final class PatientMapper {
    public static PatientTO mapToTO(final PatientEntity entity,
                                    final List<VisitEntity> pastVisits) {
        if(entity == null || entity.getId() == null){
            throw new EntityNotFoundException(-1L);
        }

        final PatientTO patientTO = new PatientTO();
        patientTO.setPatientNumber(entity.getPatientNumber());
        patientTO.setEmail(entity.getEmail());
        patientTO.setDateOfBirth(entity.getDateOfBirth());
        patientTO.setId(entity.getId());
        patientTO.setFirstName(entity.getFirstName());
        patientTO.setLastName(entity.getLastName());
        patientTO.setTelephoneNumber(entity.getTelephoneNumber());
        patientTO.setPastVisits(pastVisits);

        return patientTO;
    }
}
