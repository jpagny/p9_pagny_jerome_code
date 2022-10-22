package com.mediscreen.history.controller;

import com.mediscreen.history.dto.NoteDTO;
import com.mediscreen.history.exception.ResourceNotFoundException;
import com.mediscreen.history.service.impliment.NoteService;
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
    public ResponseEntity<List<NoteDTO>> getAllHistories() {
        List<NoteDTO> histories = noteService.getAll();

        if (histories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(histories, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<NoteDTO> getHistory(@PathVariable("id") String id) {
        try {
            NoteDTO history = noteService.get(id);
            return new ResponseEntity<>(history, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<NoteDTO> update(@RequestBody NoteDTO history) {
        try {
            NoteDTO historyUpdated = noteService.update(history);
            return new ResponseEntity<>(historyUpdated, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<NoteDTO> create(@RequestBody NoteDTO history) {
        NoteDTO historyCreated = noteService.create(history);
        return new ResponseEntity<>(historyCreated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        try {
            noteService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}



