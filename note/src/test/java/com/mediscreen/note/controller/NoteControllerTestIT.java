package com.mediscreen.note.controller;

import com.mediscreen.note.dto.NoteDTO;
import com.mediscreen.note.exception.ResourceNotFoundException;
import com.mediscreen.note.helper.Helper;
import com.mediscreen.note.service.impliment.NoteService;
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

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest()
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class NoteControllerTestIT {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private NoteService noteService;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    @DisplayName("Should be returned 200 when get note with right id")
    public void should_beReturned200_when_getNoteWithRightId() throws Exception {
        mockMvc.perform(get("/note/abcd11"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("note")));
    }

    @Test
    @DisplayName("Should be returned 200 when get note with wrong id")
    public void should_beReturned200_when_getNoteWithWrongId() throws Exception {
        mockMvc.perform(get("/note/100"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should be returned 200 when get all notes")
    public void should_beReturned200_when_getAllNotes() throws Exception {

        List<NoteDTO> allNotes = noteService.getAll();

        mockMvc.perform(get("/note/list"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(allNotes.get(0).getId())));
    }

    @Test
    @DisplayName("Should be returned 204 when note list is empty")
    public void should_beReturned204_when_noteListIsEmpty() throws Exception {

        List<NoteDTO> allNotes = noteService.getAll();

        allNotes.forEach(theNote -> {
            try {
                noteService.delete(theNote.getId());
            } catch (ResourceNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        mockMvc.perform(get("/note/list"))
                .andExpect(status().is(204));
    }

    @Test
    @DisplayName("Should be returned 200 when get all notes by patient Id")
    public void should_beReturned200_when_getAllNotesByPatientId() throws Exception {

        List<NoteDTO> allNotes = noteService.getAllByPatientId(1L);

        mockMvc.perform(get("/note/1/list"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(allNotes.get(0).getId())));
    }

    @Test
    @DisplayName("Should be returned 204 when note list is empty fetched by patient id")
    public void should_beReturned204_when_noteListFetchedByPatientIDIsEmpty() throws Exception {
        List<NoteDTO> allNotes = noteService.getAllByPatientId(1L);

        allNotes.forEach(theNote -> {
            try {
                noteService.delete(theNote.getId());
            } catch (ResourceNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        mockMvc.perform(get("/note/1/list"))
                .andExpect(status().is(204));
    }

    @Test
    @DisplayName("Should be returned 200 when note updated is success")
    public void should_beReturned200_when_noteUpdateIsSuccess() throws Exception {

        NoteDTO theNote = noteService.get("abcd11");
        theNote.setNote("Note modified");
        String json = Helper.mapToJson(theNote);

        mockMvc.perform(put("/note/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.note").value("Note modified"))
                .andReturn();
    }

    @Test
    @DisplayName("Should be returned 404 when note updated doesnt exist")
    public void should_beReturned404_when_noteUpdateDoesntExist() throws Exception {

        NoteDTO theNote = noteService.get("abcd11");
        theNote.setId("unknown");
        String json = Helper.mapToJson(theNote);

        mockMvc.perform(put("/note/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
                .andExpect(status().is(404))
                .andReturn();
    }

    @Test
    @DisplayName("Should be returned 200 when note create is success")
    public void should_beReturned200_when_noteCreateIsSuccess() throws Exception {
        NoteDTO theNote = new NoteDTO("1", 1L, LocalDateTime.now(), "Test");
        String json = Helper.mapToJson(theNote);

        mockMvc.perform(post("/note/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.note").value("Test"))
                .andReturn();
    }

    @Test
    @DisplayName("Should be returned 200 when note delete is success")
    public void should_beReturned200_when_noteDeleteIsSuccess() throws Exception {
        mockMvc.perform(delete("/note/abcd11"))
                .andExpect(status().is(200))
                .andReturn();
    }

    @Test
    @DisplayName("Should be returned 404 when note delete doesn't exist")
    public void should_beReturned200_when_noteDeleteDoesntExist() throws Exception {
        mockMvc.perform(delete("/note/100"))
                .andExpect(status().is(404))
                .andReturn();
    }

}
