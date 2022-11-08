package com.mediscreen.patient.service.impliment;

import com.mediscreen.patient.dto.PatientDTO;
import com.mediscreen.patient.entity.PatientEntity;
import com.mediscreen.patient.exception.ResourceAlreadyExistException;
import com.mediscreen.patient.exception.ResourceNotFoundException;
import com.mediscreen.patient.repository.PatientRepository;
import com.mediscreen.patient.service.IPatientService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class PatientService implements IPatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
        modelMapper = new ModelMapper();
    }

    /**
     * Fetch a patient
     *
     * @param id the id of patient
     * @return the patient
     * @throws ResourceNotFoundException the patient doesn't exist
     */
    @Override
    public PatientDTO get(Long id) throws ResourceNotFoundException {
        PatientEntity patient = patientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.valueOf(id))
        );
        log.info("The patient was successfully found with the id : {}", id);
        return modelMapper.map(patient, PatientDTO.class);
    }

    /**
     * Fetch all patients
     *
     * @return a list of patients
     */
    @Override
    public List<PatientDTO> getAll() {
        log.debug("List all patients");
        return patientRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, PatientDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Update a patient
     *
     * @param patient the patient to update
     * @return patient updated
     * @throws ResourceNotFoundException the patient doesn't exist
     */
    @Override
    public PatientDTO update(PatientDTO patient) throws ResourceNotFoundException {
        patientRepository.findById(patient.getId()).orElseThrow(
                () -> new ResourceNotFoundException(String.valueOf(patient.getId()))
        );

        PatientEntity patientToUpdate = modelMapper.map(patient, PatientEntity.class);

        log.info("The patient was successfully updated");

        return modelMapper.map(patientRepository.save(patientToUpdate), PatientDTO.class);
    }

    /**
     * Create a new patient
     *
     * @param patient the patient to create
     * @return the patient created
     * @throws ResourceAlreadyExistException the patient is already exist in database
     */
    @Override
    public PatientDTO create(PatientDTO patient) throws ResourceAlreadyExistException {
        PatientEntity patientToCreate = modelMapper.map(patient, PatientEntity.class);

        Optional<PatientEntity> patientExist = patientRepository.findByLastNameAndFirstName(
                patientToCreate.getLastName(),
                patientToCreate.getFirstName()
        );

        if (patientExist.isPresent()) {
            throw new ResourceAlreadyExistException(patientToCreate.getLastName() + " - " + patientToCreate.getFirstName());
        }

        PatientEntity patientCreated = patientRepository.save(patientToCreate);

        log.info("The patient was successfully created");

        return modelMapper.map(patientCreated, PatientDTO.class);
    }

    /**
     * Delete the patient
     *
     * @param id the id of patient
     * @throws ResourceNotFoundException the patient doesn't exist
     */
    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        PatientEntity patient = patientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.valueOf(id))
        );
        patientRepository.delete(patient);
        log.info("The patient was successfully deleted");
    }

}
