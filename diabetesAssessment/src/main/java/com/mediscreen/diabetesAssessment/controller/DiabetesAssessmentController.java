package com.mediscreen.diabetesAssessment.controller;

import com.mediscreen.diabetesAssessment.bean.PatientBean;
import com.mediscreen.diabetesAssessment.dto.DiabetesAssessmentDTO;
import com.mediscreen.diabetesAssessment.service.impliment.DiabetesAssessmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/assess")
@AllArgsConstructor
public class DiabetesAssessmentController {

    private final DiabetesAssessmentService assessmentService;

    @GetMapping(value = "/{patientId}", produces = {"application/json"})
    @Operation(summary = "Find the diabetes assessment by a patient", description = "Also returns a link to retrieve the diabetes assessment")
    @ApiResponse(responseCode = "200",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = DiabetesAssessmentDTO.class))))
    public ResponseEntity<DiabetesAssessmentDTO> getAssessmentByPatientID(
            @Parameter(description = "Long patientId.", required = true)
            @PathVariable("patientId") Long patientId) {
        DiabetesAssessmentDTO assessment = assessmentService.getAssessmentByPatient(patientId);
        return new ResponseEntity<>(assessment, HttpStatus.OK);
    }

    @GetMapping(value = "/list/{riskLevel}", produces = {"application/json"})
    @Operation(summary = "Find all notes", description = "Also returns a link to retrieve all patient order by risk level")
    @ApiResponse(responseCode = "200",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = PatientBean.class))))
    public ResponseEntity<List<PatientBean>> getAssessmentByRiskLevel(@PathVariable("riskLevel") String riskLevel) {
        List<PatientBean> listPatients = assessmentService.getPatientByRiskLevel(riskLevel);
        return new ResponseEntity<>(listPatients, HttpStatus.OK);
    }


}
