package com.mediscreen.note.controller;

import com.mediscreen.note.dto.NoteDTO;
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
        mockMvc.perform(get("/note/6177a31824f1d205e0b0692d"))
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
    public void should_beReturned200_when_noteListIsEmpty() throws Exception {
        noteService.delete("6177a31824f1d205e0b0692d");
        noteService.delete("6177a31824f1d205e0b0692c");

        mockMvc.perform(get("/note/list"))
                .andExpect(status().is(204));
    }

    @Test
    @DisplayName("Should be returned 200 when note updated is success")
    public void should_beReturned200_when_noteUpdateIsSuccess() throws Exception {

        NoteDTO theNote = noteService.get("6177a31824f1d205e0b0692d");
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

        NoteDTO theNote = noteService.get("6177a31824f1d205e0b0692d");
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
        mockMvc.perform(delete("/note/6177a31824f1d205e0b0692d"))
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
