package com.mediscreen.app.controller;

import com.mediscreen.app.bean.PatientBean;
import com.mediscreen.app.service.impliment.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Map;

@Controller
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/{id}")
    public String getPatient(Model model, @PathVariable Long id) {
        PatientBean response = patientService.getPatient(id);

        model.addAttribute("last_name", response.getLastName());
        model.addAttribute("first_name", response.getFirstName());
        model.addAttribute("birthdate", response.getBirthdate());
        model.addAttribute("sex", response.getSex());
        model.addAttribute("address", response.getAddress());
        model.addAttribute("phone", response.getPhone());

        return "patient/info";
    }


    @GetMapping("/list")
    public String getPatientList(Model model) {
        ArrayList<PatientBean> response = patientService.getAllPatient();

        model.addAttribute("patients", response);

        return "patient/list";
    }

}


