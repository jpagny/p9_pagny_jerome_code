package com.mediscreen.note.controller;

import com.mediscreen.note.dto.NoteDTO;
import com.mediscreen.note.exception.ResourceNotFoundException;
import com.mediscreen.note.service.impliment.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/note")
@Tag(name = "note", description = "the Note API")
public class NoteController {

    private final NoteService noteService;

    @GetMapping(value = "/list", produces = {"application/json"})
    @Operation(summary = "Find all notes", description = "Also returns a link to retrieve all notes")
    @ApiResponse(responseCode = "200",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = NoteDTO.class))))
    @ApiResponse(responseCode = "204", description = "No Content",
            content = @Content())
    public ResponseEntity<List<NoteDTO>> getAll() {
        List<NoteDTO> notes = noteService.getAll();

        if (notes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @GetMapping(value = "/{patientId}/list", produces = {"application/json"})
    @Operation(summary = "Find all notes by patient id", description = "Also returns a link to retrieve all notes from a patient")
    @ApiResponse(responseCode = "200",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = NoteDTO.class))))
    @ApiResponse(responseCode = "204", description = "No Content",
            content = @Content())
    public ResponseEntity<List<NoteDTO>> getAll(
            @Parameter(description = "Long patientId.", required = true)
            @PathVariable("patientId") Long patientId) {
        List<NoteDTO> notes = noteService.getAllByPatientId(patientId);

        if (notes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = {"application/json"})
    @Operation(summary = "Find a note by id", description = "Also returns a link to note information")
    @ApiResponse(responseCode = "200", description = "Note fetched",
            content = @Content(schema = @Schema(implementation = NoteDTO.class)))
    @ApiResponse(responseCode = "404", description = "No found",
            content = @Content())
    public ResponseEntity<NoteDTO> get(
            @Parameter(description = "String id.", required = true)
            @PathVariable("id") String id) {
        try {
            NoteDTO note = noteService.get(id);
            return new ResponseEntity<>(note, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/", produces = {"application/json"})
    @Operation(summary = "Update a note", description = "Also returns a link to note updated")
    @ApiResponse(responseCode = "200", description = "Note updated",
            content = @Content(schema = @Schema(implementation = NoteDTO.class)))
    @ApiResponse(responseCode = "404", description = "No found",
            content = @Content())
    public ResponseEntity<NoteDTO> update(
            @Parameter(description = "NoteDTO note.", required = true, schema = @Schema(implementation = NoteDTO.class))
            @RequestBody NoteDTO note) {
        try {
            NoteDTO noteUpdated = noteService.update(note);
            return new ResponseEntity<>(noteUpdated, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/", produces = {"application/json"})
    @Operation(summary = "Create a note", description = "Also returns a link to note created")
    @ApiResponse(responseCode = "200", description = "Patient created",
            content = @Content(schema = @Schema(implementation = NoteDTO.class)))
    public ResponseEntity<NoteDTO> create(
            @Parameter(description = "NoteDTO note.", required = true, schema = @Schema(implementation = NoteDTO.class))
            @RequestBody NoteDTO note) {
        NoteDTO noteCreated = noteService.create(note);
        return new ResponseEntity<>(noteCreated, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = {"application/json"})
    @Operation(summary = "Delete a note", description = "Patient will be note")
    @ApiResponse(responseCode = "200", description = "Note deleted",
            content = @Content())
    @ApiResponse(responseCode = "404", description = "Not found",
            content = @Content())
    public ResponseEntity<String> delete(
            @Parameter(description = "String id.", required = true)
            @PathVariable("id") String id) {
        try {
            noteService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}



