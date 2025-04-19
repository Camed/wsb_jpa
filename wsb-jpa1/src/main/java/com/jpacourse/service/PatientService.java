package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;

public interface PatientService {
    PatientTO findById(final Long id);
    PatientTO getPatientWithPastVisits(final Long patientId);
}
