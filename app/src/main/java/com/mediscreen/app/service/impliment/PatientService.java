package com.mediscreen.app.service.impliment;

import com.mediscreen.app.bean.PatientBean;
import com.mediscreen.app.proxy.PatientProxy;
import com.mediscreen.app.service.IPatientService;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class PatientService implements IPatientService {

    private final PatientProxy patientProxy;

    @Override
    public PatientBean getPatient(Long id) {
        return patientProxy.getPatient(id);
    }

    @Override
    public Map<String, PatientBean> getAllPatient() {
        return patientProxy.getAllPatient();
    }

}
