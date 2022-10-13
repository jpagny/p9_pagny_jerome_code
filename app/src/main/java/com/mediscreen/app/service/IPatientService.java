package com.mediscreen.app.service;

import com.mediscreen.app.bean.PatientBean;

import java.util.Map;

public interface IPatientService {

    PatientBean getPatient(Long id);

    Map<String, PatientBean> getAllPatient();

}
