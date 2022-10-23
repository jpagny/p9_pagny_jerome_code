package com.mediscreen.app.controller;

import com.mediscreen.app.bean.PatientBean;
import com.mediscreen.app.service.impliment.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/info/{id}")
    public String getPatient(Model model, @PathVariable Long id) {
        PatientBean response = patientService.get(id);

        model.addAttribute("patient", response);

        return "patient/info";
    }

    @GetMapping("/list")
    public String getPatientList(Model model) {
        ArrayList<PatientBean> response = patientService.getAll();

        model.addAttribute("patients", response);

        return "patient/list";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(Model model, @PathVariable Long id) {
        PatientBean response = patientService.get(id);

        model.addAttribute("patient", response);

        return "patient/update";
    }

    @PostMapping("/update")
    public String updatePatient(Model model, @Valid PatientBean patient, BindingResult result) {

        if (result.hasErrors()) {
            return "patient/update";
        }

        patientService.update(patient);

        model.addAttribute("patients", patientService.getAll());

        return "redirect:/patient/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("patient", new PatientBean());
        return "patient/add";
    }

    @PostMapping("/create")
    public String createPatient(Model model, @Valid PatientBean patient, BindingResult result) {

        if (result.hasErrors()) {
            return "patient/add";
        }

        patientService.create(patient);

        model.addAttribute("patients", patientService.getAll());

        return "redirect:/patient/list";
    }







}


