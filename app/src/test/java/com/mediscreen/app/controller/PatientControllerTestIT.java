package com.mediscreen.app.controller;

import com.mediscreen.app.bean.PatientBean;
import com.mediscreen.app.service.impliment.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest()
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PatientControllerTestIT {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private PatientService patientService;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @DisplayName("Should be returned 200 when get bidList page")
    public void should_beReturned200_when_getListPatientPage() throws Exception {
        this.mockMvc.perform(get("/patient/list"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should be returned 200 when get patient add page")
    public void should_beReturned200WhenGetPatientCreatePage() throws Exception {
        this.mockMvc.perform(get("/patient/create"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should be redirect to patient/list when added a new patient is success")
    public void should_beRedirectToPatientListWhenAddedANewPatientIsSuccess() throws Exception {
        mockMvc.perform(post("/patient/create")
                        .param("lastName", "Test")
                        .param("firstName", "Test2")
                        .param("birthdate", "2020-02-15")
                        .param("gender", "F"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/patient/list"))
                .andReturn();

        PatientBean patientCreated = patientService.get(11L);

        assertNotNull("",patientCreated);
        assertEquals("Test", patientCreated.getLastName());
    }

    @Test
    @DisplayName("Should be returned 200 when get patient update page")
    public void should_beReturned200_when_getPatientUpdatePage() throws Exception {
        this.mockMvc.perform(get("/patient/update/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should be redirect to patient/list when patient updated is success")
    public void should_beRedirectToBidListList_when_bidListUpdatedIsSuccess() throws Exception {
        mockMvc.perform(post("/patient/update/")
                        .param("id","1")
                        .param("lastName", "Lucasa")
                        .param("firstName", "Ferguson")
                        .param("birthdate","1968-06-22\t")
                        .param("gender", "M")
                        .param("address","2 Warren Street")
                        .param("phone","387-866-1399"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/patient/list"))
                .andReturn();

        PatientBean patientBean = patientService.get(1L);

        assertNotNull("",patientBean);
        assertEquals("Lucasa", patientBean.getLastName());
    }

    @Test
    @DisplayName("Should be returned info patient when get info patient is success")
    public void should_beReturnedInfoPatient_when_getInfoPatientIsSuccess() throws Exception {
        mockMvc.perform(get("/patient/info/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("patient/info"))
                .andReturn();
    }







}
