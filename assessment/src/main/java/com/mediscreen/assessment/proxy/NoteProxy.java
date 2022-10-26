package com.mediscreen.assessment.proxy;

import com.mediscreen.assessment.bean.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@FeignClient(name = "note-api")
public interface NoteProxy {

    @GetMapping(value = "/note/{patientId}/list/")
    ArrayList<NoteBean> getAllById(@PathVariable Long patientId);

}
