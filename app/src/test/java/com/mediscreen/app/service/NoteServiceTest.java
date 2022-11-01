package com.mediscreen.app.service;

import com.mediscreen.app.bean.NoteBean;
import com.mediscreen.app.bean.PatientBean;
import com.mediscreen.app.constant.Gender;
import com.mediscreen.app.proxy.NoteProxy;
import com.mediscreen.app.service.impliment.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class NoteServiceTest {

    @Mock
    private NoteProxy noteProxy;

    private NoteService noteService;

    @BeforeEach
    void initService() {
        noteService = new NoteService(noteProxy);
    }


    @Test
    @DisplayName("Should be returned note when the note is found by id")
    public void should_beReturnedNote_when_theNoteIsFoundById() {

        PatientBean patient = new PatientBean(1L, "Test", "Test 2",
                LocalDate.now().plusYears(-20), 20, Gender.F, "xxx", "xx");
        NoteBean note = new NoteBean("1", patient.getId(), patient, LocalDateTime.now(), "Test");

        when(noteProxy.get(any(String.class))).thenReturn(note);

        NoteBean noteFound = noteService.get("1");

        assertEquals(noteFound, note);
    }

    @Test
    @DisplayName("Should be returned a list of notes when get all notes")
    public void should_beReturnedAListOfNote_when_getAllNotes() {
        List<NoteBean> listNote = new ArrayList<>();
        PatientBean patient1 = new PatientBean(1L, "Test", "Test 2",
                LocalDate.now().plusYears(-20), 20, Gender.F, "xxx", "xx");
        PatientBean patient2 = new PatientBean(2L, "Test3", "Test 4",
                LocalDate.now().plusYears(-20), 20, Gender.F, "xxx", "xx");
        listNote.add(new NoteBean("2", 1L, patient1, LocalDateTime.now().plusDays(2), "Test2"));
        listNote.add(new NoteBean("1", 2L, patient2, LocalDateTime.now(), "Test"));

        when(noteProxy.getAll()).thenReturn(new ArrayList<>(listNote));

        List<NoteBean> listNotesFound = noteService.getAll();

        assertEquals(listNotesFound, listNote);
    }

    @Test
    @DisplayName("Should be returned note when a note is updated")
    public void should_beReturnedNoteList_when_aNoteIsUpdated() {
        PatientBean patient = new PatientBean(2L, "Test3", "Test 4",
                LocalDate.now().plusYears(-20), 20, Gender.F, "xxx", "xx");
        NoteBean noteToUpdate = new NoteBean("2", 1L, patient, LocalDateTime.now().plusDays(2), "Test2");
        noteToUpdate.setNote("Note modified");

        when(noteProxy.update(any(NoteBean.class))).thenReturn(noteToUpdate);

        NoteBean noteUpdated = noteService.update(noteToUpdate);

        assertEquals(noteUpdated, noteToUpdate);
    }

    @Test
    @DisplayName("Should be returned note when a note is created")
    public void should_beReturnedNoteList_when_aNoteIsCreated() {
        PatientBean patient = new PatientBean(2L, "Test3", "Test 4",
                LocalDate.now().plusYears(-20), 20, Gender.F, "xxx", "xx");
        NoteBean noteToCreate = new NoteBean("1", 1L, patient, LocalDateTime.now(), "Test");

        when(noteProxy.create(any(NoteBean.class))).thenReturn(noteToCreate);

        NoteBean noteCreated = noteService.create(noteToCreate);

        assertEquals(noteCreated, noteToCreate);
    }

    @Test
    @DisplayName("Should be used delete method when note is deleted")
    public void should_beUsedDeleteMethod_when_noteIsDeleted() {
        PatientBean patient = new PatientBean(2L, "Test3", "Test 4",
                LocalDate.now().plusYears(-20), 20, Gender.F, "xxx", "xx");

        NoteBean noteToDelete = new NoteBean("1", 1L, patient, LocalDateTime.now(), "Test");

        when(noteProxy.delete(any(String.class))).thenReturn(null);

        noteService.delete(noteToDelete.getId());

        verify(noteProxy).delete(noteToDelete.getId());
    }

    @Test
    @DisplayName("Should be returned a list of notes from a patient when get all notes by patientID")
    public void should_beReturnedAListOfNoteFromAPatient_when_getAllNotesByPatientID() {
        PatientBean patient1 = new PatientBean(1L, "Test", "Test2",
                LocalDate.now().plusYears(-10), 20, Gender.F, "xxx", "xx");
        PatientBean patient2 = new PatientBean(2L, "Test3", "Test 4",
                LocalDate.now().plusYears(-20), 20, Gender.F, "xxx", "xx");
        List<NoteBean> listNote = new ArrayList<>();
        listNote.add(new NoteBean("1", patient1.getId(), patient1, LocalDateTime.now(), "Test"));
        listNote.add(new NoteBean("2", patient1.getId(), patient1, LocalDateTime.now(), "Test2"));
        listNote.add(new NoteBean("2", patient2.getId(), patient2, LocalDateTime.now(), "Test3"));

        when(noteProxy.getAllById(1L)).thenReturn((ArrayList<NoteBean>) listNote.stream()
                .filter(note -> note.getPatientId().equals(1L))
                .collect(Collectors.toList()));

        List<NoteBean> listNotesFound = noteService.getAllByPatientId(1L);

        assertEquals(2, listNotesFound.size());
    }


}
