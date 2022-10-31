package com.mediscreen.diabetesAssessment.service.impliment;

import com.mediscreen.diabetesAssessment.bean.NoteBean;
import com.mediscreen.diabetesAssessment.bean.PatientBean;
import com.mediscreen.diabetesAssessment.constant.KeyWordTrigger;
import com.mediscreen.diabetesAssessment.constant.RiskLevel;
import com.mediscreen.diabetesAssessment.dto.DiabetesAssessmentDTO;
import com.mediscreen.diabetesAssessment.proxy.NoteProxy;
import com.mediscreen.diabetesAssessment.proxy.PatientProxy;
import com.mediscreen.diabetesAssessment.service.IDiabetesAssessmentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
@AllArgsConstructor
public class DiabetesAssessmentService implements IDiabetesAssessmentService {

    private final PatientProxy patientProxy;
    private final NoteProxy noteProxy;


    @Override
    public DiabetesAssessmentDTO getAssessmentByPatient(Long patientId) {

        PatientBean patient = patientProxy.get(patientId);
        List<NoteBean> listNotes = noteProxy.getAllById(patientId);

        if ( listNotes == null){
            listNotes = new ArrayList<>();
        }

        RiskLevel riskLevel;
        int scoreTrigger;

        scoreTrigger = getScoreTriggerByAllNotes(listNotes);
        riskLevel = checkLevelRisk(patient, scoreTrigger);

        return new DiabetesAssessmentDTO(LocalDateTime.now(), scoreTrigger, riskLevel);
    }

    @Override
    public List<PatientBean> getPatientByRiskLevel(String riskLevel) {

        List<PatientBean> listPatient = patientProxy.getAll();
        ArrayList<PatientBean> listPatientWithThisRiskLevel = new ArrayList<>();

        listPatient.parallelStream().forEach(thePatient -> {
            DiabetesAssessmentDTO diabetesAssessmentDTO = getAssessmentByPatient(thePatient.getId());
            log.debug(diabetesAssessmentDTO.getRiskLevel().label);
            if (diabetesAssessmentDTO.getRiskLevel().label.equals(riskLevel)) {
                listPatientWithThisRiskLevel.add(thePatient);
            }
        });

        return listPatientWithThisRiskLevel;
    }

    private int getScoreTriggerByAllNotes(List<NoteBean> listNotes) {

        AtomicInteger scoreTrigger = new AtomicInteger();

        listNotes.forEach(theNote -> scoreTrigger.addAndGet(getScoreTriggerByNote(theNote)));

        log.debug("Total score for all notes is : " + scoreTrigger.get());
        return scoreTrigger.get();
    }

    private int getScoreTriggerByNote(NoteBean note) {

        List<KeyWordTrigger> keyWords = new ArrayList<>(Arrays.asList(KeyWordTrigger.values()));
        AtomicInteger scoreTrigger = new AtomicInteger();

        keyWords.forEach(theKey -> {
            if (note.getNote().toLowerCase().contains(theKey.label.toLowerCase())) {
                log.debug("Key word found : " + theKey.label.toLowerCase());
                scoreTrigger.getAndIncrement();
            }
        });

        log.debug("Total score for this note is : " + scoreTrigger.get());
        return scoreTrigger.get();
    }

    private RiskLevel checkLevelRisk(PatientBean patient, int scoreTrigger) {

        RiskLevel riskLevel = checkLevelRiskCommune(patient.getAge(), scoreTrigger);

        if (riskLevel == null) {
            riskLevel = switch (patient.getGender()) {
                case M -> checkLevelRiskWithGenderMan(patient.getAge(), scoreTrigger);
                case F -> checkLevelRiskWithGenderWoman(patient.getAge(), scoreTrigger);
            };
        }

        return riskLevel;
    }

    private RiskLevel checkLevelRiskCommune(int age, int scoreTrigger) {

        if (scoreTrigger == 0)
            return RiskLevel.NONE;

        else if (age > 30 && scoreTrigger == 2)
            return RiskLevel.BORDERLINE;

        else if (age > 30 && scoreTrigger == 6)
            return RiskLevel.IN_DANGER;

        else if (age > 30 && scoreTrigger >= 8)
            return RiskLevel.EARLY_ONSET;

        else
            return null;
    }

    private RiskLevel checkLevelRiskWithGenderMan(int age, int scoreTrigger) {

        if (age < 30 && scoreTrigger == 3)
            return RiskLevel.IN_DANGER;

        else if (age < 30 && scoreTrigger == 5)
            return RiskLevel.EARLY_ONSET;

        return RiskLevel.UNKNOWN;
    }

    private RiskLevel checkLevelRiskWithGenderWoman(int age, int scoreTrigger) {

        if (age < 30 && scoreTrigger == 4)
            return RiskLevel.IN_DANGER;

        else if (age < 30 && scoreTrigger == 7)
            return RiskLevel.EARLY_ONSET;

        return RiskLevel.UNKNOWN;
    }


}
