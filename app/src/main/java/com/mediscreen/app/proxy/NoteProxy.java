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

    @GetMapping( value = "/note/{id}")
    PatientBean get(@PathVariable("id") long id);

    @PutMapping(value = "/note/")
    PatientBean update(@RequestBody NoteBean patientBean);

    @PostMapping(value="/note/")
    PatientBean create(@RequestBody NoteBean patientBean);

    @DeleteMapping(value="/note/{id}")
    PatientBean create(@PathVariable("id") long id);


}
