package com.mediscreen.diabetesAssessment.controller;

import com.mediscreen.diabetesAssessment.bean.PatientBean;
import com.mediscreen.diabetesAssessment.constant.RiskLevel;
import com.mediscreen.diabetesAssessment.dto.DiabetesAssessmentDTO;
import com.mediscreen.diabetesAssessment.service.impliment.DiabetesAssessmentService;
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

    @GetMapping("/{patientId}")
    public ResponseEntity<DiabetesAssessmentDTO> getAssessmentByPatientID(@PathVariable("patientId") Long patientId) {
        DiabetesAssessmentDTO assessment = assessmentService.getAssessmentByPatient(patientId);
        return new ResponseEntity<>(assessment, HttpStatus.OK);
    }

    @GetMapping("/list/{riskLevel}")
    public ResponseEntity<List<PatientBean>> getAssessmentByRiskLevel(@PathVariable("riskLevel") String riskLevel) {
        List<PatientBean> listPatients = assessmentService.getPatientByRiskLevel(riskLevel);
        return new ResponseEntity<>(listPatients, HttpStatus.OK);
    }


}
