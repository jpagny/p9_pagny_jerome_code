package com.mediscreen.app.controller;

import com.mediscreen.app.bean.PatientBean;
import com.mediscreen.app.constant.RiskLevel;
import com.mediscreen.app.service.impliment.DiabetesAssessmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/diabetesAssessment")
@AllArgsConstructor
public class DiabetesAssessmentController {

    private final DiabetesAssessmentService diabetesAssessmentService;

    @GetMapping("/list")
    public String getNote(Model model) {

        ArrayList<RiskLevel> listRiskLevel = new ArrayList<>(Arrays.asList(RiskLevel.values()));
        TreeMap<RiskLevel, List<PatientBean>> listPatientsByRiskLevel = new TreeMap<>();

        listRiskLevel.forEach(theRiskLevel -> {
            List<PatientBean> listPatients = diabetesAssessmentService.getByRiskLevel(theRiskLevel.label);
            if ( !listPatients.isEmpty() ) {
                listPatientsByRiskLevel.put(theRiskLevel, listPatients);
            }
        });

        model.addAttribute("list", listPatientsByRiskLevel);

        return "diabetesAssessment/list";
    }

}
