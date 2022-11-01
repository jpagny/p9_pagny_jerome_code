package com.mediscreen.app.service;

import com.mediscreen.app.bean.PatientBean;
import com.mediscreen.app.constant.Gender;
import com.mediscreen.app.proxy.PatientProxy;
import com.mediscreen.app.service.impliment.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PatientServiceTest {

    @Mock
    private PatientProxy patientProxy;

    private PatientService patientService;

    @BeforeEach
    void initService() {
        patientService = new PatientService(patientProxy);
    }

    @Test
    @DisplayName("Should be returned patient when the patient is found by id")
    public void should_beReturnedPatient_when_thePatientIsFoundById() {
        PatientBean patient = new PatientBean(1L, "john", "rick", LocalDate.now().plusYears(-15), 15, Gender.M, "rue du java", "06.45.78.12.36");
        when(patientProxy.get(any(Long.class))).thenReturn(patient);

        PatientBean patientFound = patientService.get(1L);

        assertEquals(patientFound, patient);
    }

    @Test
    @DisplayName("Should be returned a list of patient when get all patients")
    public void should_beReturnedAListOfPatient_when_getAllPatients() {
        List<PatientBean> listPatients = new ArrayList<>();
        listPatients.add(new PatientBean(1L, "john", "rick", LocalDate.now().plusYears(-5), 5, Gender.M, "rue du java", "06.45.78.12.36"));
        listPatients.add(new PatientBean(1L, "john", "pamela", LocalDate.now().plusYears(-20), 20, Gender.F, "rue du java", "06.45.78.12.35"));

        when(patientProxy.getAll()).thenReturn(new ArrayList<>(listPatients));

        List<PatientBean> listPatientsFound = patientService.getAll();

        assertEquals(listPatientsFound, listPatients);
    }

    @Test
    @DisplayName("Should be returned patient when a patient is updated")
    public void should_beReturnedPatientList_when_aPatientIsUpdated() {
        PatientBean patientToUpdate = new PatientBean(1L, "john", "rick", LocalDate.now().plusYears(-10), 10, Gender.M, "rue du java", "06.45.78.12.36");
        patientToUpdate.setLastName("johna");

        when(patientProxy.update(any(PatientBean.class))).thenReturn(patientToUpdate);

        PatientBean patientUpdated = patientService.update(patientToUpdate);

        assertEquals(patientUpdated, patientToUpdate);
    }

    @Test
    @DisplayName("Should be returned patient when a patient is created")
    public void should_beReturnedPatientList_when_aPatientIsCreated() {
        PatientBean patientToCreate = new PatientBean(1L, "john", "rick", LocalDate.now().plusYears(-10), 10, Gender.M, "rue du java", "06.45.78.12.36");

        when(patientProxy.create(any(PatientBean.class))).thenReturn(patientToCreate);

        PatientBean patientCreated = patientService.create(patientToCreate);

        assertEquals(patientCreated, patientToCreate);
    }


}
