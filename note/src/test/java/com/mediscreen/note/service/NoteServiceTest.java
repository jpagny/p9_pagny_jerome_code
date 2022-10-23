package com.mediscreen.note.service;

import com.mediscreen.note.document.NoteDocument;
import com.mediscreen.note.dto.NoteDTO;
import com.mediscreen.note.exception.ResourceNotFoundException;
import com.mediscreen.note.repository.NoteRepository;
import com.mediscreen.note.service.impliment.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class NoteServiceTest {

    private NoteService noteService;

    @Mock
    private NoteRepository noteRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @BeforeEach
    void initService() {
        noteService = new NoteService(noteRepository);
    }

    @Test
    @DisplayName("Should be returned note when the note is found by id")
    public void should_beReturnedNote_when_theNoteIsFoundById() throws ResourceNotFoundException {

        NoteDTO note = new NoteDTO("1", 1L, LocalDateTime.now(), "Test");
        NoteDocument noteToFind = modelMapper.map(note,NoteDocument.class);

        when(noteRepository.findById(any(String.class))).thenReturn(Optional.of(noteToFind));

        NoteDTO noteFound = noteService.get("1");

        assertEquals(noteFound, note);
    }

    @Test
    @DisplayName("Should be exception when the note is not found by id")
    public void should_beException_when_theNoteIsNotFoundById() {
        when(noteRepository.findById(any(String.class))).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> noteService.get("1"));

        String expectedMessage = "Resource doesn't exist : 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Should be returned a list of notes when get all notes")
    public void should_beReturnedAListOfNote_when_getAllNotes() {
        List<NoteDTO> listNote = new ArrayList<>();
        listNote.add(new NoteDTO("1", 1L, LocalDateTime.now(), "Test"));
        listNote.add(new NoteDTO("2", 1L, LocalDateTime.now(), "Test2"));

        when(noteRepository.findAll()).thenReturn(listNote.stream()
                .map(theNote -> modelMapper.map(theNote, NoteDocument.class))
                .collect(Collectors.toList()));

        List<NoteDTO> listNotesFound = noteService.getAll();

        assertEquals(listNotesFound, listNote);
    }

    @Test
    @DisplayName("Should be returned note when a note is updated")
    public void should_beReturnedNoteList_when_aNoteIsUpdated() throws ResourceNotFoundException {
        NoteDTO noteToUpdate = new NoteDTO("1", 1L, LocalDateTime.now(), "Test");
        noteToUpdate.setNote("Note modified");
        NoteDocument noteDocumentUpdated = modelMapper.map(noteToUpdate,NoteDocument.class);

        when(noteRepository.findById(any(String.class))).thenReturn(Optional.of(noteDocumentUpdated));
        when(noteRepository.save(any(NoteDocument.class))).thenReturn(noteDocumentUpdated);

        NoteDTO noteUpdated = noteService.update(noteToUpdate);

        assertEquals(noteUpdated, noteToUpdate);
    }

    @Test
    @DisplayName("Should be exception when the note to update doesn't exist")
    public void should_beException_when_theNoteToUpdateDoesntExist() {
        NoteDTO noteToUpdate = new NoteDTO("1", 1L, LocalDateTime.now(), "Test");

        when(noteRepository.findById(any(String.class))).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> noteService.update(noteToUpdate));

        String expectedMessage = "Resource doesn't exist : 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Should be returned note when a note is created")
    public void should_beReturnedNoteList_when_aNoteIsCreated() {
        NoteDTO noteToCreate = new NoteDTO("1", 1L, LocalDateTime.now(), "Test");
        NoteDocument note = modelMapper.map(noteToCreate, NoteDocument.class);

        when(noteRepository.save(note)).thenReturn(note);

        NoteDTO noteCreated = noteService.create(noteToCreate);

        assertEquals(noteCreated, noteToCreate);
    }

    @Test
    @DisplayName("Should be used delete method when note is deleted")
    public void should_beUsedDeleteMethod_when_noteIsDeleted() throws ResourceNotFoundException {
        NoteDTO noteToDelete = new NoteDTO("1", 1L, LocalDateTime.now(), "Test");
        NoteDocument note = modelMapper.map(noteToDelete, NoteDocument.class);

        when(noteRepository.findById(any(String.class))).thenReturn(Optional.ofNullable(note));

        noteService.delete(noteToDelete.getId());

        assertNotNull(note);
        verify(noteRepository).delete(note);
    }


    @Test
    @DisplayName("Should be exception when the note to delete doesn't exist")
    public void should_beException_when_theNoteToDeleteDoesntExist() {
        when(noteRepository.findById(any(String.class))).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> noteService.delete("1"));

        String expectedMessage = "Resource doesn't exist : 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}
