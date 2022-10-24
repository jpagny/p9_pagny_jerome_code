package com.mediscreen.app.service;

import com.mediscreen.app.bean.NoteBean;
import com.mediscreen.app.bean.PatientBean;

import java.util.ArrayList;

public interface INoteService {

    NoteBean get(String id);

    ArrayList<NoteBean> getAll();

    ArrayList<NoteBean> getAllByPatientId(Long patientId);

    NoteBean update(NoteBean note);

    NoteBean create(NoteBean note);

    void delete(String id);

}
