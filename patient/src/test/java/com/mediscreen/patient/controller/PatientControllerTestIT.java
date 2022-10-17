package com.mediscreen.patient.controller;

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

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
    @DisplayName("Should be redirect to patient/list when patient updated is success")
    public void should_beRedirectToPatientList_when_patientUpdatedIsSuccess() throws Exception {

        PatientDTO thePatient = patientService.get(1L);
        thePatient.setLastName("johna");
        String json = Helper.mapToJson(thePatient);

        mockMvc.perform(put("/patient/update")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.lastName").value("johna"))
                .andExpect(jsonPath("$.firstName").value("jonathan"))
                .andReturn();
    }


}
