package com.mediscreen.patient.controller;

import com.mediscreen.patient.constant.Gender;
import com.mediscreen.patient.dto.PatientDTO;
import com.mediscreen.patient.helper.Helper;
import com.mediscreen.patient.service.impliment.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest()
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PatientControllerTestIT {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private PatientService patientService;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    @DisplayName("Should be returned 200 when get patient with right id")
    public void should_beReturned200_when_getPatientWithRightId() throws Exception {
        mockMvc.perform(get("/patient/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("john")));
    }

    @Test
    @DisplayName("Should be returned 200 when get patient with wrong id")
    public void should_beReturned200_when_getPatientWithWrongId() throws Exception {
        mockMvc.perform(get("/patient/100"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should be returned 200 when get all patients")
    public void should_beReturned200_when_getAllPatients() throws Exception {

        List<PatientDTO> allPatients = patientService.getAll();

        mockMvc.perform(get("/patient/list"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(allPatients.get(0).getId().toString())));
    }

    @Test
    @DisplayName("Should be returned 204 when patient list is empty")
    public void should_beReturned200_when_patientListIsEmpty() throws Exception {
        patientService.delete(1L);
        patientService.delete(2L);

        mockMvc.perform(get("/patient/list"))
                .andExpect(status().is(204));
    }

    @Test
    @DisplayName("Should be returned 200 when patient updated is success")
    public void should_beReturned200_when_patientUpdateIsSuccess() throws Exception {

        PatientDTO thePatient = patientService.get(1L);
        thePatient.setLastName("johna");
        String json = Helper.mapToJson(thePatient);

        mockMvc.perform(put("/patient/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.lastName").value("johna"))
                .andExpect(jsonPath("$.firstName").value("jonathan"))
                .andReturn();
    }

    @Test
    @DisplayName("Should be returned 404 when patient updated doesnt exist")
    public void should_beReturned200_when_patientUpdateDoesntExist() throws Exception {

        PatientDTO thePatient = patientService.get(1L);
        thePatient.setId(1000L);
        String json = Helper.mapToJson(thePatient);

        mockMvc.perform(put("/patient/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
                .andExpect(status().is(404))
                .andReturn();
    }

    @Test
    @DisplayName("Should be returned 200 when patient create is success")
    public void should_beReturned200_when_patientCreateIsSuccess() throws Exception {
        PatientDTO thePatient = new PatientDTO(3L, "Test", "Test2", LocalDate.now(), Gender.F, "xx", "xx");
        String json = Helper.mapToJson(thePatient);

        mockMvc.perform(post("/patient/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.lastName").value("Test"))
                .andExpect(jsonPath("$.firstName").value("Test2"))
                .andReturn();
    }

    @Test
    @DisplayName("Should be returned 409 when patient create is already exist")
    public void should_beReturned503_when_patientCreateIsAlreadyExist() throws Exception {
        PatientDTO thePatient = new PatientDTO(4L, "john", "jonathan", LocalDate.now(), Gender.F, "xx", "xx");
        String json = Helper.mapToJson(thePatient);

        mockMvc.perform(post("/patient/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
                .andExpect(status().is(409))
                .andReturn();
    }

    @Test
    @DisplayName("Should be returned 200 when patient delete is success")
    public void should_beReturned200_when_patientDeleteIsSuccess() throws Exception {
        mockMvc.perform(delete("/patient/1"))
                .andExpect(status().is(200))
                .andReturn();
    }

    @Test
    @DisplayName("Should be returned 404 when patient delete doesn't exist")
    public void should_beReturned200_when_patientDeleteDoesntExist() throws Exception {
        mockMvc.perform(delete("/patient/100"))
                .andExpect(status().is(404))
                .andReturn();
    }


}
