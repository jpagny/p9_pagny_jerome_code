package com.mediscreen.app.service.impliment;

import com.mediscreen.app.bean.PatientBean;
import com.mediscreen.app.proxy.PatientProxy;
import com.mediscreen.app.service.IPatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class PatientService implements IPatientService {

    private final PatientProxy patientProxy;

    @Override
    public PatientBean get(Long id) {
        return patientProxy.getPatient(id);
    }

    @Override
    public ArrayList<PatientBean> getAll() {
        return patientProxy.getAllPatient();
    }

    @Override
    public PatientBean update(PatientBean patientBean) {
        return patientProxy.update(patientBean);
    }

    @Override
    public PatientBean create(PatientBean patientBean) {
        return patientProxy.create(patientBean);
    }

}
