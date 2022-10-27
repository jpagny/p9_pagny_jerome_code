package com.mediscreen.diabetesAssessment.service;

import com.mediscreen.diabetesAssessment.bean.NoteBean;
import com.mediscreen.diabetesAssessment.bean.PatientBean;
import com.mediscreen.diabetesAssessment.constant.Gender;
import com.mediscreen.diabetesAssessment.constant.RiskLevel;
import com.mediscreen.diabetesAssessment.dto.DiabetesAssessmentDTO;
import com.mediscreen.diabetesAssessment.proxy.NoteProxy;
import com.mediscreen.diabetesAssessment.proxy.PatientProxy;
import com.mediscreen.diabetesAssessment.service.impliment.DiabetesAssessmentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DiabetesAssessmentServiceTest {


    @MockBean
    private PatientProxy patientProxy;

    @MockBean
    private NoteProxy noteProxy;

    @Autowired
    private DiabetesAssessmentService diabetesAssessmentService;

    @Test
    @DisplayName("Should be returned risk level NONE when the score trigger is equal to 0")
    public void should_be_returnedRiskLevelNone_when_theScoreTriggerIsEqualTo0() {

        PatientBean patientBean = new PatientBean(1L, "Test", "Test", LocalDate.now(), Gender.F, "xx", "xx");
        NoteBean noteBean = new NoteBean("abcd", 1L, LocalDateTime.now(), "Test");
        ArrayList<NoteBean> listNoteBeanByPatientId = new ArrayList<>();
        listNoteBeanByPatientId.add(noteBean);

        when(patientProxy.get(any(Long.class))).thenReturn(patientBean);
        when(noteProxy.getAllById(any(Long.class))).thenReturn(listNoteBeanByPatientId);

        DiabetesAssessmentDTO result = diabetesAssessmentService.getAssessmentByPatient(1L);

        assertEquals(0, result.getScoreTrigger());
        assertEquals(result.getRiskLevel(), RiskLevel.NONE);
    }

    @Test
    @DisplayName("Should be returned risk level Borderline when the score trigger is equal to 2 and have more 30 old")
    public void should_be_returnedRiskLevelBorderline_when_theScoreTriggerIsEqualTo2AndHaveMore30Old() {

        PatientBean patientBean = new PatientBean(1L, "Test", "Test", LocalDate.now().plusYears(-40), Gender.F, "xx", "xx");
        NoteBean noteBean = new NoteBean("abcd", 1L, LocalDateTime.now(), "Le patient a une taille petit et il est fumeur");
        ArrayList<NoteBean> listNoteBeanByPatientId = new ArrayList<>();
        listNoteBeanByPatientId.add(noteBean);

        when(patientProxy.get(any(Long.class))).thenReturn(patientBean);
        when(noteProxy.getAllById(any(Long.class))).thenReturn(listNoteBeanByPatientId);

        DiabetesAssessmentDTO result = diabetesAssessmentService.getAssessmentByPatient(1L);

        assertEquals(2, result.getScoreTrigger());
        assertEquals(result.getRiskLevel(), RiskLevel.BORDERLINE);
    }

    @Test
    @DisplayName("Should be returned risk level In danger when the score trigger is equal to 6 and have more 30 old")
    public void should_be_returnedRiskLevelInDanger_when_theScoreTriggerIsEqualTo6AndHaveMore30Old() {

        PatientBean patientBean = new PatientBean(1L, "Test", "Test", LocalDate.now().plusYears(-40), Gender.F, "xx", "xx");
        NoteBean noteBean = new NoteBean("abcd", 1L, LocalDateTime.now(), "Le patient a une taille anormalement petit et il est fumeur. Rechute, poids, réaction");
        ArrayList<NoteBean> listNoteBeanByPatientId = new ArrayList<>();
        listNoteBeanByPatientId.add(noteBean);

        when(patientProxy.get(any(Long.class))).thenReturn(patientBean);
        when(noteProxy.getAllById(any(Long.class))).thenReturn(listNoteBeanByPatientId);

        DiabetesAssessmentDTO result = diabetesAssessmentService.getAssessmentByPatient(1L);

        assertEquals(6, result.getScoreTrigger());
        assertEquals(result.getRiskLevel(), RiskLevel.IN_DANGER);
    }

    @Test
    @DisplayName("Should be returned risk level Early onset when the score trigger is equal to 8 and have more 30 old")
    public void should_be_returnedRiskLevelEarlyOnset_when_theScoreTriggerIsEqualTo8AndHaveMore30Old() {

        PatientBean patientBean = new PatientBean(1L, "Test", "Test", LocalDate.now().plusYears(-40), Gender.F, "xx", "xx");
        NoteBean noteBean = new NoteBean("abcd", 1L, LocalDateTime.now(), "Le patient a une taille anormalement petit et il est fumeur. Rechute, poids, réaction, anticorps, vertige");
        ArrayList<NoteBean> listNoteBeanByPatientId = new ArrayList<>();
        listNoteBeanByPatientId.add(noteBean);

        when(patientProxy.get(any(Long.class))).thenReturn(patientBean);
        when(noteProxy.getAllById(any(Long.class))).thenReturn(listNoteBeanByPatientId);

        DiabetesAssessmentDTO result = diabetesAssessmentService.getAssessmentByPatient(1L);

        assertEquals(8, result.getScoreTrigger());
        assertEquals(result.getRiskLevel(), RiskLevel.EARLY_ONSET);
    }

    @Test
    @DisplayName("Should be returned risk level In danger when the score trigger is equal to 3 and have less 30 old and the patient is man")
    public void should_be_returnedRiskLevelInDanger_when_theScoreTriggerIsEqualTo3AndHaveMore30OldAndThePatientIsMan() {

        PatientBean patientBean = new PatientBean(1L, "Test", "Test", LocalDate.now(), Gender.M, "xx", "xx");
        NoteBean noteBean = new NoteBean("abcd", 1L, LocalDateTime.now(), "Le patient a une taille anormalement petit et il est fumeur");
        ArrayList<NoteBean> listNoteBeanByPatientId = new ArrayList<>();
        listNoteBeanByPatientId.add(noteBean);

        when(patientProxy.get(any(Long.class))).thenReturn(patientBean);
        when(noteProxy.getAllById(any(Long.class))).thenReturn(listNoteBeanByPatientId);

        DiabetesAssessmentDTO result = diabetesAssessmentService.getAssessmentByPatient(1L);

        assertEquals(3, result.getScoreTrigger());
        assertEquals(result.getRiskLevel(), RiskLevel.IN_DANGER);
    }

    @Test
    @DisplayName("Should be returned risk level In danger when the score trigger is equal to 3 and have less 30 old and the patient is man")
    public void should_be_returnedRiskLevelEarlyOnset_when_theScoreTriggerIsEqualTo5AndHaveMore30OldAndThePatientIsMan() {

        PatientBean patientBean = new PatientBean(1L, "Test", "Test", LocalDate.now(), Gender.M, "xx", "xx");
        NoteBean noteBean = new NoteBean("abcd", 1L, LocalDateTime.now(), "Le patient a une taille anormalement petit et il est fumeur. Poids, anticorps");
        ArrayList<NoteBean> listNoteBeanByPatientId = new ArrayList<>();
        listNoteBeanByPatientId.add(noteBean);

        when(patientProxy.get(any(Long.class))).thenReturn(patientBean);
        when(noteProxy.getAllById(any(Long.class))).thenReturn(listNoteBeanByPatientId);

        DiabetesAssessmentDTO result = diabetesAssessmentService.getAssessmentByPatient(1L);

        assertEquals(5, result.getScoreTrigger());
        assertEquals(result.getRiskLevel(), RiskLevel.EARLY_ONSET);
    }

    @Test
    @DisplayName("Should be returned risk level UNKNOWN when the rule is unknown and the patient is man")
    public void should_be_returnedRiskLevelUnknown_when_theRuleIsUnknownAndThePatientIsMan() {

        PatientBean patientBean = new PatientBean(1L, "Test", "Test", LocalDate.now(), Gender.M, "xx", "xx");
        NoteBean noteBean = new NoteBean("abcd", 1L, LocalDateTime.now(), "Le patient a une taille anormalement");
        ArrayList<NoteBean> listNoteBeanByPatientId = new ArrayList<>();
        listNoteBeanByPatientId.add(noteBean);

        when(patientProxy.get(any(Long.class))).thenReturn(patientBean);
        when(noteProxy.getAllById(any(Long.class))).thenReturn(listNoteBeanByPatientId);

        DiabetesAssessmentDTO result = diabetesAssessmentService.getAssessmentByPatient(1L);

        assertEquals(2, result.getScoreTrigger());
        assertEquals(result.getRiskLevel(), RiskLevel.UNKNOWN);
    }

    @Test
    @DisplayName("Should be returned risk level In danger when the score trigger is equal to 4 and have less 30 old and the patient is woman")
    public void should_be_returnedRiskLevelInDanger_when_theScoreTriggerIsEqualTo4AndHaveMore30OldAndThePatientIsWoman() {

        PatientBean patientBean = new PatientBean(1L, "Test", "Test", LocalDate.now(), Gender.F, "xx", "xx");
        NoteBean noteBean = new NoteBean("abcd", 1L, LocalDateTime.now(), "Le patient a une taille anormalement petite et elle est fumeur.se. Anticorps");
        ArrayList<NoteBean> listNoteBeanByPatientId = new ArrayList<>();
        listNoteBeanByPatientId.add(noteBean);

        when(patientProxy.get(any(Long.class))).thenReturn(patientBean);
        when(noteProxy.getAllById(any(Long.class))).thenReturn(listNoteBeanByPatientId);

        DiabetesAssessmentDTO result = diabetesAssessmentService.getAssessmentByPatient(1L);

        assertEquals(4, result.getScoreTrigger());
        assertEquals(result.getRiskLevel(), RiskLevel.IN_DANGER);
    }

    @Test
    @DisplayName("Should be returned risk level Early onset when the score trigger is equal to 7 and have less 30 old and the patient is woman")
    public void should_be_returnedRiskLevelEarlyOnset_when_theScoreTriggerIsEqualTo7AndHaveMore30OldAndThePatientIsWoman() {

        PatientBean patientBean = new PatientBean(1L, "Test", "Test", LocalDate.now(), Gender.F, "xx", "xx");
        NoteBean noteBean = new NoteBean("abcd", 1L, LocalDateTime.now(), "Le patient a une taille anormalement petite et elle est fumeur.se. Anticorps, poids, cholestérol, rechute");
        ArrayList<NoteBean> listNoteBeanByPatientId = new ArrayList<>();
        listNoteBeanByPatientId.add(noteBean);

        when(patientProxy.get(any(Long.class))).thenReturn(patientBean);
        when(noteProxy.getAllById(any(Long.class))).thenReturn(listNoteBeanByPatientId);

        DiabetesAssessmentDTO result = diabetesAssessmentService.getAssessmentByPatient(1L);

        assertEquals(7, result.getScoreTrigger());
        assertEquals(result.getRiskLevel(), RiskLevel.EARLY_ONSET);
    }

    @Test
    @DisplayName("Should be returned risk level UNKNOWN when the rule is unknown and the patient is woman")
    public void should_be_returnedRiskLevelUnknown_when_theRuleIsUnknownAndThePatientIsWoman() {

        PatientBean patientBean = new PatientBean(1L, "Test", "Test", LocalDate.now(), Gender.F, "xx", "xx");
        NoteBean noteBean = new NoteBean("abcd", 1L, LocalDateTime.now(), "Le patient a une taille anormalement");
        ArrayList<NoteBean> listNoteBeanByPatientId = new ArrayList<>();
        listNoteBeanByPatientId.add(noteBean);

        when(patientProxy.get(any(Long.class))).thenReturn(patientBean);
        when(noteProxy.getAllById(any(Long.class))).thenReturn(listNoteBeanByPatientId);

        DiabetesAssessmentDTO result = diabetesAssessmentService.getAssessmentByPatient(1L);

        assertEquals(2, result.getScoreTrigger());
        assertEquals(result.getRiskLevel(), RiskLevel.UNKNOWN);
    }

    @Test
    @DisplayName("Should be returned list of 2 patients with risk level EARLY_ONSET when method getPatients by risk level is called")
    public void should_be_returnedListPatient_when_methodGetPatientsByRiskLevelIsCalled(){
        PatientBean patientBean1 = new PatientBean(1L, "Test", "Test", LocalDate.now(), Gender.F, "xx", "xx");
        PatientBean patientBean2 = new PatientBean(2L, "Test", "Test", LocalDate.now(), Gender.M, "xx", "xx");
        PatientBean patientBean3 = new PatientBean(3L, "Test", "Test", LocalDate.now(), Gender.F, "xx", "xx");
        ArrayList<PatientBean> listPatients = new ArrayList<>();
        listPatients.add(patientBean1);
        listPatients.add(patientBean2);
        listPatients.add(patientBean3);

        NoteBean noteBean1 = new NoteBean("abcd", 1L, LocalDateTime.now(), "Le patient a une taille anormalement petite et elle est fumeur.se. Anticorps, poids, cholestérol, rechute");
        NoteBean noteBean2 = new NoteBean("abcd", 2L, LocalDateTime.now(), "Le patient a une taille anormalement petit et il est fumeur. Poids, anticorps");
        NoteBean noteBean3 = new NoteBean("abcd", 1L, LocalDateTime.now(), "Le patient a une taille anormalement petit et il est fumeur.");
        ArrayList<NoteBean> listNotes1 = new ArrayList<>();
        listNotes1.add(noteBean1);
        ArrayList<NoteBean> listNotes2 = new ArrayList<>();
        listNotes2.add(noteBean2);
        ArrayList<NoteBean> listNotes3 = new ArrayList<>();
        listNotes3.add(noteBean3);

        when(patientProxy.getAll()).thenReturn(listPatients);
        when(patientProxy.get(1L)).thenReturn(patientBean1);
        when(patientProxy.get(2L)).thenReturn(patientBean2);
        when(patientProxy.get(3L)).thenReturn(patientBean3);
        when(noteProxy.getAllById(1L)).thenReturn(listNotes1);
        when(noteProxy.getAllById(2L)).thenReturn(listNotes2);
        when(noteProxy.getAllById(3L)).thenReturn(listNotes3);

        List<PatientBean> result = diabetesAssessmentService.getPatientByRiskLevel(RiskLevel.EARLY_ONSET.label);

        assertEquals(2, result.size());
    }

}
