package com.mediscreen.app.proxy;

import com.mediscreen.app.bean.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@FeignClient(name = "patient-api")
public interface PatientProxy {

    @GetMapping(value = "/patient/list/")
    ArrayList<PatientBean> getAll();

    @GetMapping( value = "/patient/{id}")
    PatientBean get(@PathVariable("id") long id);

    @PutMapping(value = "/patient/")
    PatientBean update(@RequestBody PatientBean patientBean);

    @PostMapping(value="/patient/")
    PatientBean create(@RequestBody PatientBean patientBean);

    @DeleteMapping(value="/patient/{id}")
    PatientBean create(@PathVariable("id") long id);

}
