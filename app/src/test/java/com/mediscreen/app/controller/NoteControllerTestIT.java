package com.mediscreen.app.controller;

import com.mediscreen.app.bean.NoteBean;
import com.mediscreen.app.bean.PatientBean;
import com.mediscreen.app.service.impliment.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest()
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class NoteControllerTestIT {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private NoteService noteService;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @DisplayName("Should be returned 200 when get list notes page")
    public void should_beReturned200_when_getListNotePage() throws Exception {
        this.mockMvc.perform(get("/note/list"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should be returned 200 when get note add page")
    public void should_beReturned200WhenGetNoteCreatePage() throws Exception {
        this.mockMvc.perform(get("/note/create"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should be redirect to patient/id when added a new note is success")
    public void should_beRedirectToPatientIdWhenAddedANewNoteIsSuccess() throws Exception {

        mockMvc.perform(post("/note/create")
                        .param("patientId", "1")
                        .param("note", "Test"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/patient/info/1"))
                .andReturn();

        ArrayList<NoteBean> note = noteService.getAllByPatientId(1L);
        assertEquals("Test", note.get(0).getNote());
    }

    @Test
    @DisplayName("Should be redirect to note/create and added a message error when there is a bad request")
    public void should_beRedirectToNoteCreateAndAddedAMessageError_when_thereIsABadRequest() throws Exception {

        mockMvc.perform(post("/note/create/")
                        .param("patientId", "1")
                        .param("note", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("note/add"))
                .andExpect(content().string(containsString("Note is required")))
                .andReturn();
    }

    @Test
    @DisplayName("Should be returned 200 when get note update page")
    public void should_beReturned200_when_getNoteUpdatePage() throws Exception {
        this.mockMvc.perform(get("/note/update/abcd12"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should be redirect to patient/id when note updated is success")
    public void should_beRedirectToBidListList_when_bidListUpdatedIsSuccess() throws Exception {
        mockMvc.perform(post("/note/update/")
                        .param("id", "abcd12")
                        .param("patientId", "1")
                        .param("note", "Note corrigé"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/patient/info/1"))
                .andReturn();

        NoteBean noteUpdated = noteService.get("abcd12");

        assertEquals("Note corrigé",noteUpdated.getNote());
    }

    @Test
    @DisplayName("Should be redirect to note/update and added a message error when there is a bad request")
    public void should_beRedirectToNoteUpdateAndAddedAMessageError_when_thereIsABadRequest() throws Exception {

        mockMvc.perform(post("/note/update/")
                        .param("id", "abcd12")
                        .param("patientId", "1")
                        .param("note", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("note/update"))
                .andExpect(content().string(containsString("Note is required")))
                .andReturn();
    }

    @Test
    @DisplayName("Should be deleted note when note is deleted")
    public void should_beDeletedNote_when_noteIsDeleted() throws Exception {
        this.mockMvc.perform(get("/note/delete/abcd13"))
                .andDo(print())
                .andExpect(view().name("redirect:/patient/info/1"));
    }

    @Test
    @DisplayName("Should be returned info note when get info note is success")
    public void should_beReturnedInfoNote_when_getInfoNoteIsSuccess() throws Exception {
        mockMvc.perform(get("/note/info/abcd12"))
                .andExpect(status().isOk())
                .andExpect(view().name("note/info"))
                .andReturn();
    }





}
