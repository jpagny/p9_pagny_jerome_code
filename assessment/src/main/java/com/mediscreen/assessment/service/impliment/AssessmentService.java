package com.mediscreen.assessment.service.impliment;

import com.mediscreen.assessment.bean.NoteBean;
import com.mediscreen.assessment.bean.PatientBean;
import com.mediscreen.assessment.constant.KeyWordTrigger;
import com.mediscreen.assessment.constant.RiskLevel;
import com.mediscreen.assessment.dto.AssessmentDTO;
import com.mediscreen.assessment.proxy.NoteProxy;
import com.mediscreen.assessment.proxy.PatientProxy;
import com.mediscreen.assessment.service.IAssessmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
public class AssessmentService implements IAssessmentService {

    private final PatientProxy patientProxy;
    private final NoteProxy noteProxy;


    @Override
    public AssessmentDTO getAssessmentByPatient(Long patientId) {

        PatientBean patient = patientProxy.get(patientId);
        List<NoteBean> listNotes = noteProxy.getAllById(patientId);
        RiskLevel riskLevel;
        int scoreTrigger;

        scoreTrigger = getScoreTriggerByAllNotes(listNotes);
        riskLevel = checkLevelRisk(patient, scoreTrigger);

        return new AssessmentDTO(LocalDateTime.now(), patient, scoreTrigger, riskLevel);

    }

    private int getScoreTriggerByAllNotes(List<NoteBean> listNotes) {
        AtomicInteger scoreTrigger = new AtomicInteger();
        listNotes.parallelStream().forEach(theNote ->
                scoreTrigger.addAndGet(getScoreTriggerByNote(theNote)
                ));
        return scoreTrigger.get();
    }

    private int getScoreTriggerByNote(NoteBean note) {
        List<KeyWordTrigger> keyWords = new ArrayList<>(Arrays.asList(KeyWordTrigger.values()));
        AtomicInteger scoreTrigger = new AtomicInteger();

        keyWords.parallelStream().forEach(theKey -> {
            if (note.getNote().toLowerCase().contains(theKey.name().toLowerCase())) {
                scoreTrigger.getAndIncrement();
            }
        });

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
