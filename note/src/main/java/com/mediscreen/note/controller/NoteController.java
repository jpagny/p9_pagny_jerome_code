package com.mediscreen.note.controller;

import com.mediscreen.note.dto.NoteDTO;
import com.mediscreen.note.exception.ResourceNotFoundException;
import com.mediscreen.note.service.impliment.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<NoteDTO>> getAll() {
        List<NoteDTO> notes = noteService.getAll();

        if (notes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @GetMapping("/{patientId}/list")
    public ResponseEntity<List<NoteDTO>> getAll(@PathVariable("patientId") Long patientId) {
        List<NoteDTO> notes = noteService.getAllByPatientId(patientId);

        if (notes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteDTO> get(@PathVariable("id") String id) {
        try {
            NoteDTO note = noteService.get(id);
            return new ResponseEntity<>(note, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/")
    public ResponseEntity<NoteDTO> update(@RequestBody NoteDTO note) {
        try {
            NoteDTO noteUpdated = noteService.update(note);
            return new ResponseEntity<>(noteUpdated, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<NoteDTO> create(@RequestBody NoteDTO note) {
        NoteDTO noteCreated = noteService.create(note);
        return new ResponseEntity<>(noteCreated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        try {
            noteService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}



