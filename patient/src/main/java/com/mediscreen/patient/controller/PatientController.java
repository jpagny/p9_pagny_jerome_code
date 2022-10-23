package com.mediscreen.patient.controller;

import com.mediscreen.patient.dto.PatientDTO;
import com.mediscreen.patient.exception.ResourceAlreadyExistException;
import com.mediscreen.patient.exception.ResourceNotFoundException;
import com.mediscreen.patient.service.impliment.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/list")
    @Operation(summary = "Find all patients",
            description = "Also returns a link to retrieve all patients",
            responses = {
                    @ApiResponse(
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PatientDTO.class))),
                    @ApiResponse(
                            responseCode = "204", description = "No content"
                    )
            })
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<PatientDTO> patients = patientService.getAll();

        if (patients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Parameter(description = "Long id.", required = true)
    @Operation(summary = "Find a patient by id",
            description = "Also returns a link to patient information",
            responses = {
                    @ApiResponse(
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PatientDTO.class))),
                    @ApiResponse(
                            responseCode = "200", description = "Patient fetched",
                            content = @Content(schema = @Schema(implementation = PatientDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404", description = "No found"
                    )
            })
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable("id") Long id) {
        try {
            PatientDTO patient = patientService.get(id);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/")
    @Parameter(description = "PatientDTO patient.", required = true, schema = @Schema(implementation = PatientDTO.class))
    @Operation(summary = "Update a patient",
            description = "Also returns a link to patient updated",
            responses = {
                    @ApiResponse(
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PatientDTO.class))),
                    @ApiResponse(
                            responseCode = "200", description = "Patient updated",
                            content = @Content(schema = @Schema(implementation = PatientDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404", description = "No found"
                    )})
    public ResponseEntity<PatientDTO> update(@RequestBody PatientDTO patient) {
        try {
            PatientDTO patientUpdated = patientService.update(patient);
            return new ResponseEntity<>(patientUpdated, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    @Parameter(description = "PatientDTO patient.", required = true, schema = @Schema(implementation = PatientDTO.class))
    @Operation(summary = "Create a patient",
            description = "Also returns a link to patient created",
            responses = {
                    @ApiResponse(
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PatientDTO.class))),
                    @ApiResponse(
                            responseCode = "200", description = "Patient created",
                            content = @Content(schema = @Schema(implementation = PatientDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "409", description = "Conflict - patient is already exist in our database"
                    )
            })
    public ResponseEntity<PatientDTO> create(@RequestBody PatientDTO patient) {
        try {
            PatientDTO patientCreated = patientService.create(patient);
            return new ResponseEntity<>(patientCreated, HttpStatus.OK);
        } catch (ResourceAlreadyExistException ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}")
    @Parameter(description = "Long id.", required = true)
    @Operation(summary = "Delete a patient",
            description = "Patient will be deleted",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "Patient deleted",
                            content = @Content(schema = @Schema(implementation = PatientDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404", description = "Not found"
                    )
            })
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            patientService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
