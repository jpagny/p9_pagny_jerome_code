package com.mediscreen.patient.service;

import com.mediscreen.patient.dto.PatientDTO;
import com.mediscreen.patient.exception.ResourceNotFoundException;

import java.util.List;

public interface IPatientService {

    PatientDTO getPatient(Long id) throws ResourceNotFoundException;

    List<PatientDTO> getAllPatients();

}
