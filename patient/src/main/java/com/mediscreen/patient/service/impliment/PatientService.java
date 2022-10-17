package com.mediscreen.patient.service.impliment;

import com.mediscreen.patient.dto.PatientDTO;
import com.mediscreen.patient.entity.PatientEntity;
import com.mediscreen.patient.exception.ResourceAlreadyExistException;
import com.mediscreen.patient.exception.ResourceNotFoundException;
import com.mediscreen.patient.repository.PatientRepository;
import com.mediscreen.patient.service.IPatientService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PatientService implements IPatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
        modelMapper = new ModelMapper();
    }

    @Override
    public PatientDTO get(Long id) throws ResourceNotFoundException {
        PatientEntity patient = patientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.valueOf(id))
        );
        return modelMapper.map(patient, PatientDTO.class);
    }

    @Override
    public List<PatientDTO> getAll() {
        return patientRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, PatientDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PatientDTO update(PatientDTO patient) throws ResourceNotFoundException {
        patientRepository.findById(patient.getId()).orElseThrow(
                () -> new ResourceNotFoundException(String.valueOf(patient.getId()))
        );

        PatientEntity patientToUpdate = modelMapper.map(patient, PatientEntity.class);

        return modelMapper.map(patientRepository.save(patientToUpdate), PatientDTO.class);
    }

    @Override
    public PatientDTO create(PatientDTO patient) throws ResourceAlreadyExistException {
        PatientEntity patientToCreate = modelMapper.map(patient, PatientEntity.class);

        Optional<PatientEntity> patientExist = patientRepository.findByLastNameAndFirstName(
                patient.getLastName(),
                patient.getFirstName()
        );

        if (patientExist.isPresent()) {
            throw new ResourceAlreadyExistException(patient.getLastName() + " - " + patient.getFirstName());
        }

        return modelMapper.map(patientRepository.save(patientToCreate), PatientDTO.class);
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        PatientEntity patient = patientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.valueOf(id))
        );
        patientRepository.delete(patient);
    }

}
