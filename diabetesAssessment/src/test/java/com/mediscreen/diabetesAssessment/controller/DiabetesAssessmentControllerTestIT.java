package com.mediscreen.diabetesAssessment.controller;

import com.mediscreen.diabetesAssessment.service.impliment.DiabetesAssessmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc
public class DiabetesAssessmentControllerTestIT {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private DiabetesAssessmentService diabetesAssessmentService;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    @DisplayName("Should be returned 200 when get patientId")
    public void should_beReturned200_when_getNoteWithRightId() throws Exception {
        mockMvc.perform(get("/assess/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("riskLevel")));
    }

    @Test
    @DisplayName("Should be returned 200 when get list patients by risk level")
    public void should_beReturned200_when_getListPatientsByRiskLevel() throws Exception {
        mockMvc.perform(get("/assess/list/early onset"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("lastName")));
    }



}
