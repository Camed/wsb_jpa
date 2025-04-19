package com.jpacourse.rest;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.rest.exception.EntityNotFoundException;
import com.jpacourse.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {
    @Autowired
    private final PatientService patientService;

    public PatientController(PatientService patientService) { this.patientService = patientService; }

    @GetMapping("/patient/{id}")
    public ResponseEntity<PatientTO> GetPatientById(@PathVariable final Long id){
        try {
            final PatientTO patient = patientService.findById(id);
            return ResponseEntity.ok(patient);
        }
        catch (EntityNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
