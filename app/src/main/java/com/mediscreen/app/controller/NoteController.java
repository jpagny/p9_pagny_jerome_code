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

        if (listNote != null) {
            listNote.stream().forEach(theNote -> theNote.setPatient(patientService.get(theNote.getPatientId())));
        } else {
            listNote = new ArrayList<>();
        }

        model.addAttribute("notes", listNote);

        return "note/list";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(Model model, @PathVariable String id) {
        NoteBean noteBean = noteService.get(id);
        PatientBean patientSelected = patientService.get(noteBean.getPatientId());
        List<PatientBean> listPatients = patientService.getAll();

        model.addAttribute("noteBean", noteBean);
        model.addAttribute("patientSelected", patientSelected);
        model.addAttribute("patients", listPatients);

        return "note/update";
    }

    @PostMapping("/update/")
    public String updateNote(Model model, @Valid NoteBean noteBean, BindingResult result) {

        if (result.hasErrors()) {
            return "note/update";
        }

        noteBean.setDate(LocalDateTime.now());
        NoteBean noteUpdated = noteService.update(noteBean);

        model.addAttribute("notes", noteService.getAll());

        return "redirect:/patient/info/" + noteUpdated.getPatientId();
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        List<PatientBean> listPatients = patientService.getAll();
        model.addAttribute("noteBean", new NoteBean());
        model.addAttribute("patients", listPatients);

        return "note/add";
    }

    @PostMapping("/create")
    public String createNote(Model model, @Valid NoteBean noteBean, BindingResult result) {

        if (result.hasErrors()) {
            return "note/add";
        }

        noteBean.setDate(LocalDateTime.now());
        NoteBean noteCreated = noteService.create(noteBean);

        model.addAttribute("notes", noteService.getAll());

        return "redirect:/patient/info/" + noteCreated.getPatientId();
    }

    @GetMapping("/delete/{id}")
    public String deleteNote(@PathVariable("id") String id) {
        NoteBean note = noteService.get(id);
        noteService.delete(id);

        return "redirect:/patient/info/" + note.getPatientId();
    }


}
