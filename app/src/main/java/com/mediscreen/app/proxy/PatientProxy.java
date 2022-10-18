package com.mediscreen.app.proxy;

import com.mediscreen.app.bean.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@FeignClient(name = "patient", url = "${patient.service.url}")
public interface PatientProxy {

    @GetMapping(value = "/patient/list/")
    ArrayList<PatientBean> getAllPatient();

    @GetMapping( value = "/patient/{id}")
    PatientBean getPatient(@PathVariable("id") long id);

    @PutMapping(value = "/patient/update")
    PatientBean update(@RequestBody PatientBean patientBean);

    @PostMapping(value="/patient/create")
    PatientBean create(@RequestBody PatientBean patientBean);

    @DeleteMapping(value="/patient/delete/{id}")
    PatientBean create(@PathVariable("id") long id);

}
