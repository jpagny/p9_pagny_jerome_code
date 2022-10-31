package com.mediscreen.app.service;

import com.mediscreen.app.bean.DiabetesAssessmentBean;
import com.mediscreen.app.bean.NoteBean;
import com.mediscreen.app.bean.PatientBean;
import com.mediscreen.app.constant.Gender;
import com.mediscreen.app.constant.RiskLevel;
import com.mediscreen.app.proxy.DiabetesAssessmentProxy;
import com.mediscreen.app.service.impliment.DiabetesAssessmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DiabetesAssessmentServiceTest {
    @Mock
    private DiabetesAssessmentProxy diabetesAssessmentProxy;

    private DiabetesAssessmentService diabetesAssessmentService;

    @BeforeEach
    void initService() {
        diabetesAssessmentService = new DiabetesAssessmentService(diabetesAssessmentProxy);
    }

    @Test
    @DisplayName("Should be returned result of diabetes assessment when method proxy getByPatientId is called")
    public void should_be_returnedRiskLevelNone_when_theScoreTriggerIsEqualTo0() {

        DiabetesAssessmentBean diabetesAssessmentBean = new DiabetesAssessmentBean(LocalDateTime.now(),0, RiskLevel.NONE);

        when(diabetesAssessmentProxy.getById(any(Long.class))).thenReturn(diabetesAssessmentBean);

        DiabetesAssessmentBean result = diabetesAssessmentService.getByPatientId(1L);

        assertEquals(0, result.getScoreTrigger());
        assertEquals(result.getRiskLevel(), RiskLevel.NONE);
    }

    @Test
    @DisplayName("Should be returned list of  patients with risk level xxx when method getByRiskLevel is called")
    public void should_be_returnedListPatient_when_methodGetPatientsByRiskLevelIsCalled(){
        PatientBean patientBean1 = new PatientBean(1L, "Test", "Test", LocalDate.now().plusYears(-10).toString(),10, Gender.F, "xx", "xx");
        PatientBean patientBean2 = new PatientBean(2L, "Test", "Test", LocalDate.now().plusYears(-10).toString(),10, Gender.M, "xx", "xx");
        ArrayList<PatientBean> listPatients = new ArrayList<>();
        listPatients.add(patientBean1);
        listPatients.add(patientBean2);

        when(diabetesAssessmentProxy.getByRiskLevel(any(String.class))).thenReturn(listPatients);

        List<PatientBean> result = diabetesAssessmentService.getByRiskLevel(RiskLevel.EARLY_ONSET.label);

        assertEquals(2, result.size());
    }



}
