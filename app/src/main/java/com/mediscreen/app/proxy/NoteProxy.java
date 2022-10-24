package com.mediscreen.app.proxy;

import com.mediscreen.app.bean.NoteBean;
import com.mediscreen.app.bean.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@FeignClient(name = "note", url = "${note.service.url}")
public interface NoteProxy {

    @GetMapping(value = "/note/list/")
    ArrayList<NoteBean> getAll();

    @GetMapping(value = "/note/{patientId}/list/")
    ArrayList<NoteBean> getAllById(@PathVariable Long patientId);

    @GetMapping( value = "/note/{id}")
    NoteBean get(@PathVariable("id") String id);

    @PutMapping(value = "/note/")
    NoteBean update(@RequestBody NoteBean note);

    @PostMapping(value="/note/")
    NoteBean create(@RequestBody NoteBean note);

    @DeleteMapping(value="/note/{id}")
    NoteBean delete(@PathVariable("id") String id);


}
