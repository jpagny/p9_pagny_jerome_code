package com.mediscreen.app.service;

import com.mediscreen.app.bean.PatientBean;

import java.util.ArrayList;
import java.util.Map;

public interface IPatientService {

    PatientBean get(Long id);

    ArrayList<PatientBean> getAll();

    PatientBean update(PatientBean patientBean);

}
