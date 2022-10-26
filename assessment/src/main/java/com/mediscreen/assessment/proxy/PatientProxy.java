package com.mediscreen.assessment.proxy;

import com.mediscreen.assessment.bean.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "patient-api")
public interface PatientProxy {
    @GetMapping( value = "/patient/{id}")
    PatientBean get(@PathVariable("id") long id);

}
