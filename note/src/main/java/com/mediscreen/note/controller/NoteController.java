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
        List<NoteDTO> histories = noteService.getAll();

        if (histories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(histories, HttpStatus.OK);
    }

    @GetMapping("/{patientId}/list")
    public ResponseEntity<List<NoteDTO>> getAll(@PathVariable("patientId") Long patientId) {
        List<NoteDTO> histories = noteService.getAllByPatientId(patientId);

        if (histories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(histories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteDTO> get(@PathVariable("id") String id) {
        try {
            NoteDTO history = noteService.get(id);
            return new ResponseEntity<>(history, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/")
    public ResponseEntity<NoteDTO> update(@RequestBody NoteDTO history) {
        try {
            NoteDTO historyUpdated = noteService.update(history);
            return new ResponseEntity<>(historyUpdated, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<NoteDTO> create(@RequestBody NoteDTO history) {
        NoteDTO historyCreated = noteService.create(history);
        return new ResponseEntity<>(historyCreated, HttpStatus.OK);
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



