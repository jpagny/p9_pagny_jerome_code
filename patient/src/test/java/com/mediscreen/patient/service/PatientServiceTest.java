package com.mediscreen.patient.service;

import com.mediscreen.patient.constant.Gender;
import com.mediscreen.patient.dto.PatientDTO;
import com.mediscreen.patient.entity.PatientEntity;
import com.mediscreen.patient.exception.ResourceAlreadyExistException;
import com.mediscreen.patient.exception.ResourceNotFoundException;
import com.mediscreen.patient.repository.PatientRepository;
import com.mediscreen.patient.service.impliment.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PatientServiceTest {

    private PatientService patientService;

    @Mock
    private PatientRepository patientRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @BeforeEach
    void initService() {
        patientService = new PatientService(patientRepository);
    }

    @Test
    @DisplayName("Should be returned user when the patient is found by id")
    public void should_beReturnedUser_when_theUserIsFoundById() throws ResourceNotFoundException {
        PatientDTO patient = new PatientDTO(1L, "john", "rick", LocalDate.now(), Gender.MEN, "rue du java", "06.45.78.12.36");
        when(patientRepository.findById(any(Long.class))).thenReturn(Optional.of(new PatientEntity(1L, "john", "rick", LocalDate.now(), Gender.MEN, "rue du java", "06.45.78.12.36")));

        PatientDTO patientFound = patientService.get(1L);

        assertEquals(patientFound, patient);
    }

    @Test
    @DisplayName("Should be exception when the patient is not found by id")
    public void should_beException_when_thePatientIsNotFoundById() {
        when(patientRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> patientService.get(1L));

        String expectedMessage = "Resource doesn't exist : 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Should be returned a list of patient when get all patients")
    public void should_beReturnedAListOfPatient_when_getAllPatients() {
        List<PatientDTO> listPatients = new ArrayList<>();
        listPatients.add(new PatientDTO(1L, "john", "rick", LocalDate.now(), Gender.MEN, "rue du java", "06.45.78.12.36"));
        listPatients.add(new PatientDTO(1L, "john", "pamela", LocalDate.now(), Gender.WOMEN, "rue du java", "06.45.78.12.35"));

        when(patientRepository.findAll()).thenReturn(listPatients.stream()
                .map(thePatient -> modelMapper.map(thePatient, PatientEntity.class))
                .collect(Collectors.toList()));

        List<PatientDTO> listUserFound = patientService.getAll();

        assertEquals(listUserFound, listPatients);
    }

    @Test
    @DisplayName("Should be returned patient when a patient is updated")
    public void should_beReturnedPatientList_when_aPatientIsUpdated() throws ResourceNotFoundException {
        PatientDTO patientToUpdate = new PatientDTO(1L, "john", "rick", LocalDate.now(), Gender.MEN, "rue du java", "06.45.78.12.36");
        patientToUpdate.setLastName("johna");

        PatientEntity patientEntity = new PatientEntity(1L, "johna", "rick", LocalDate.now(), Gender.MEN, "rue du java", "06.45.78.12.36");

        when(patientRepository.findById(any(Long.class))).thenReturn(Optional.of(patientEntity));
        when(patientRepository.save(any(PatientEntity.class))).thenReturn(patientEntity);

        PatientDTO patientUpdated = patientService.update(patientToUpdate);

        assertEquals(patientUpdated, patientToUpdate);
    }

    @Test
    @DisplayName("Should be exception when the patient to update doesn't exist")
    public void should_beException_when_thePatientToUpdateDoesntExist() {
        PatientDTO patientToUpdate = new PatientDTO(1L, "john", "rick", LocalDate.now(), Gender.MEN, "rue du java", "06.45.78.12.36");

        when(patientRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> patientService.update(patientToUpdate));

        String expectedMessage = "Resource doesn't exist : 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Should be returned patient when a patient is created")
    public void should_beReturnedPatientList_when_aPatientIsCreated() throws ResourceAlreadyExistException {
        PatientDTO patientToCreate = new PatientDTO(1L, "john", "rick", LocalDate.now(), Gender.MEN, "rue du java", "06.45.78.12.36");
        PatientEntity patient = modelMapper.map(patientToCreate, PatientEntity.class);

        when(patientRepository.findByLastNameAndFirstName(any(String.class), any(String.class)))
                .thenReturn(Optional.empty());
        when(patientRepository.save(patient)).thenReturn(patient);

        PatientDTO patientCreated = patientService.create(patientToCreate);

        assertEquals(patientCreated, patientToCreate);
    }

    @Test
    @DisplayName("Should be exception when the patient to create is already exist")
    public void should_beException_when_thePatientToCreateIsAlreadyExist() {
        PatientDTO patientToCreate = new PatientDTO(1L, "john", "rick", LocalDate.now(), Gender.MEN, "rue du java", "06.45.78.12.36");
        PatientEntity patient = modelMapper.map(patientToCreate, PatientEntity.class);

        when(patientRepository.findByLastNameAndFirstName(any(String.class), any(String.class)))
                .thenReturn(Optional.ofNullable(patient));
        Exception exception = assertThrows(ResourceAlreadyExistException.class, () -> patientService.create(patientToCreate));

        String expectedMessage = "Resource is already exist with resource : " + patientToCreate.getLastName() + " - " + patientToCreate.getFirstName();
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Should be used delete method when patient is deleted")
    public void should_beUsedDeleteMethod_when_patientIsDeleted() throws ResourceNotFoundException {
        PatientDTO patientToDelete = new PatientDTO(1L, "john", "rick", LocalDate.now(), Gender.MEN, "rue du java", "06.45.78.12.36");
        PatientEntity patient = modelMapper.map(patientToDelete, PatientEntity.class);

        when(patientRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(patient));

        patientService.delete(patientToDelete.getId());

        assertNotNull(patient);
        verify(patientRepository).delete(patient);
    }


    @Test
    @DisplayName("Should be exception when the patient to delete doesn't exist")
    public void should_beException_when_thePatientToDeleteDoesntExist() {
        when(patientRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> patientService.delete(1L));

        String expectedMessage = "Resource doesn't exist : 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


}
