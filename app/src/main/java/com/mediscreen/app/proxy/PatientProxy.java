package com.mediscreen.app.proxy;

import com.mediscreen.app.bean.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "patient", url = "${patient.service.url}")
public interface PatientProxy {

    @GetMapping(value = "/patient/getAll/")
    Map<String, PatientBean> getAllPatient();

    @GetMapping( value = "/patient/get/{id}")
    PatientBean getPatient(@PathVariable("id") long id);

}
