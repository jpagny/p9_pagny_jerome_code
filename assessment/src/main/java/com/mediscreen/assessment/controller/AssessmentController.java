package com.mediscreen.assessment.controller;

import com.mediscreen.assessment.dto.AssessmentDTO;
import com.mediscreen.assessment.service.impliment.AssessmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assessment")
@AllArgsConstructor
public class AssessmentController {

    private final AssessmentService assessmentService;

    @GetMapping("/{patientId}")
    public ResponseEntity<AssessmentDTO> getAssessment(@PathVariable("patientId") Long patientId) {
        AssessmentDTO assessment = assessmentService.getAssessmentByPatient(patientId);
        return new ResponseEntity<>(assessment, HttpStatus.OK);
    }


}
