package com.mediscreen.app.controller;

import com.mediscreen.app.bean.NoteBean;
import com.mediscreen.app.bean.PatientBean;
import com.mediscreen.app.service.impliment.NoteService;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/note")
@AllArgsConstructor
public class NoteController {
    private final PatientService patientService;
    private final NoteService noteService;

    @GetMapping("/info/{id}")
    public String getNote(Model model, @PathVariable String id) {
        NoteBean note = noteService.get(id);
        PatientBean patient = patientService.get(note.getPatientId());

        model.addAttribute("patient", patient);
        model.addAttribute("note", note);

        return "note/info";
    }

    @GetMapping("/list")
    public String getNoteList(Model model) {
        ArrayList<NoteBean> listNote = noteService.getAll();

        listNote.stream().forEach(theNote-> theNote.setPatient(patientService.get(theNote.getPatientId())));

        model.addAttribute("notes", listNote);

        return "note/list";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(Model model, @PathVariable String id) {
        NoteBean response = noteService.get(id);
        PatientBean patientSelected = patientService.get(response.getPatientId());
        List<PatientBean> listPatients = patientService.getAll();

        model.addAttribute("theNote", response);
        model.addAttribute("patientSelected", patientSelected);
        model.addAttribute("patients", listPatients);


        return "note/update";
    }

    @PostMapping("/update")
    public String updateNote(Model model, @Valid NoteBean note, BindingResult result) {

        if (result.hasErrors()) {
            return "note/update";
        }

        note.setDate(LocalDateTime.now());
        NoteBean noteUpdated = noteService.update(note);

        model.addAttribute("notes", noteService.getAll());

        return "redirect:/patient/info/" + noteUpdated.getPatientId();
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        List<PatientBean> listPatients = patientService.getAll();
        model.addAttribute("note", new NoteBean());
        model.addAttribute("patients", listPatients);

        return "note/add";
    }

    @PostMapping("/create")
    public String createNote(Model model, @Valid NoteBean note, BindingResult result) {

        if (result.hasErrors()) {
            return "note/add";
        }

        note.setDate(LocalDateTime.now());
        NoteBean noteCreated = noteService.create(note);

        model.addAttribute("notes", noteService.getAll());

        return "redirect:/patient/info/" + noteCreated.getPatientId();
    }


}
