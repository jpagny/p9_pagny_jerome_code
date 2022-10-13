package com.mediscreen.app.service;

import com.mediscreen.app.bean.PatientBean;

import java.util.ArrayList;
import java.util.Map;

public interface IPatientService {

    PatientBean getPatient(Long id);

    ArrayList<PatientBean> getAllPatient();

}
