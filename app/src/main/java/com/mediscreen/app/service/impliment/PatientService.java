package com.mediscreen.app.service.impliment;

import com.mediscreen.app.bean.PatientBean;
import com.mediscreen.app.proxy.PatientProxy;
import com.mediscreen.app.service.IPatientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
@Slf4j
public class PatientService implements IPatientService {

    private final PatientProxy patientProxy;

    @Override
    public PatientBean get(Long id) {
        log.debug("Call patientProxy.get(id)");
        return patientProxy.get(id);
    }

    @Override
    public ArrayList<PatientBean> getAll() {
        log.debug("Call patientProxy.getAll()");
        return patientProxy.getAll();
    }

    @Override
    public PatientBean update(PatientBean patientBean) {
        log.debug("Call patientProxy.update(patientBean)");
        return patientProxy.update(patientBean);
    }

    @Override
    public PatientBean create(PatientBean patientBean) {
        log.debug("Call patientProxy.create(patientBean)");
        return patientProxy.create(patientBean);
    }

}
