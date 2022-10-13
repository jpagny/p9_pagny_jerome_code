package com.mediscreen.patient.service.impliment;

import com.mediscreen.patient.dto.PatientDTO;
import com.mediscreen.patient.entity.PatientEntity;
import com.mediscreen.patient.exception.ResourceNotFoundException;
import com.mediscreen.patient.repository.PatientRepository;
import com.mediscreen.patient.service.IPatientService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PatientService implements IPatientService {

    private final PatientRepository patientRepository;
    private ModelMapper modelMapper;

    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
        modelMapper = new ModelMapper();
    }


    @Override
    public PatientDTO getPatient(Long id) throws ResourceNotFoundException {
        PatientEntity patient = patientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.valueOf(id))
        );
        return modelMapper.map(patient, PatientDTO.class);
    }

    @Override
    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, PatientDTO.class))
                .collect(Collectors.toList());
    }

}
