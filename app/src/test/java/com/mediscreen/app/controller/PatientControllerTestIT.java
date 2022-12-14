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

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    @DisplayName("Should be returned 200 when get list patients page")
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
                        .param("gender", "F")
                        .param("address","2 Warren Street")
                        .param("phone","387-866-1399"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/patient/list"))
                .andReturn();

        PatientBean patientCreated = patientService.get(11L);

        assertNotNull("",patientCreated);
        assertEquals("Test", patientCreated.getLastName());
    }

    @Test
    @DisplayName("Should be redirect to patient/add and show a message error when there is a bad request")
    public void should_beRedirectToNoteAddAndShowAMessageError_when_thereIsABadRequest() throws Exception {

        mockMvc.perform(post("/patient/create/")
                        .param("lastName", "")
                        .param("firstName", "Test2")
                        .param("birthdate", "2020-02-15")
                        .param("gender", "F")
                        .param("address","2 Warren Street")
                        .param("phone","387-866-1399"))
                .andExpect(status().isOk())
                .andExpect(view().name("patient/add"))
                .andExpect(content().string(containsString("Last name is required")))
                .andReturn();
    }

    @Test
    @DisplayName("Should be redirect to 409 page when added patient already exist")
    public void should_beRedirectTo409PageWhenAddedPatientAlreadyExist() throws Exception {
        mockMvc.perform(post("/patient/create")
                        .param("lastName", "Pippa")
                        .param("firstName", "Rees")
                        .param("birthdate", "1952-09-27")
                        .param("gender", "F")
                        .param("address","745 West Valley Farms Drive")
                        .param("phone","387-866-1399"))
                .andExpect(status().isOk())
                .andExpect(view().name("error/409.html"))
                .andReturn();
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
    public void should_beRedirectToPatientList_when_patientUpdatedIsSuccess() throws Exception {
        mockMvc.perform(post("/patient/update/")
                        .param("id","1")
                        .param("lastName", "Lucasa")
                        .param("firstName", "Ferguson")
                        .param("birthdate","1968-06-22")
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
    @DisplayName("Should be redirect to patient/update and show a message error when there is a bad request")
    public void should_beRedirectToNoteUpdateAndShowAMessageError_when_thereIsABadRequest() throws Exception {

        mockMvc.perform(post("/patient/update/")
                        .param("id","1")
                        .param("lastName", "")
                        .param("firstName", "Ferguson")
                        .param("birthdate","1968-06-22")
                        .param("gender", "M")
                        .param("address","2 Warren Street")
                        .param("phone","387-866-1399"))
                .andExpect(status().isOk())
                .andExpect(view().name("patient/update"))
                .andExpect(content().string(containsString("Last name is required")))
                .andReturn();
    }

    @Test
    @DisplayName("Should be returned info patient when get info patient is success")
    public void should_beReturnedInfoPatient_when_getInfoPatientIsSuccess() throws Exception {
        mockMvc.perform(get("/patient/info/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("patient/info"))
                .andReturn();
    }

    @Test
    @DisplayName("Should be returned 404 page when get info with a wrong id")
    public void should_beReturned404PageWhenGetInfoWIthAWrongId() throws Exception {
        mockMvc.perform(get("/patient/info/100"))
                .andExpect(status().isOk())
                .andExpect(view().name("error/404.html"))
                .andReturn();
    }

    @Test
    @DisplayName("Should be returned 400 page when get info with a bad request")
    public void should_beReturned400PageWhenGetInfoWIthABadRequest() throws Exception {
        mockMvc.perform(get("/patient/info/xxx"))
                .andExpect(status().is(400))
                .andReturn();
    }

}
