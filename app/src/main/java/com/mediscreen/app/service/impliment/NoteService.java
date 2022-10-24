package com.mediscreen.app.service.impliment;

import com.mediscreen.app.bean.NoteBean;
import com.mediscreen.app.proxy.NoteProxy;
import com.mediscreen.app.service.INoteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class NoteService implements INoteService {

    private final NoteProxy noteProxy;

    @Override
    public NoteBean get(String id) {
        return noteProxy.get(id);
    }

    @Override
    public ArrayList<NoteBean> getAll() {
        return noteProxy.getAll();
    }

    @Override
    public ArrayList<NoteBean> getAllByPatientId(Long patientId) {
        return noteProxy.getAllById(patientId);
    }

    @Override
    public NoteBean update(NoteBean note) {
        return noteProxy.update(note);
    }

    @Override
    public NoteBean create(NoteBean note) {
        return noteProxy.create(note);
    }

    @Override
    public void delete(String id){
        noteProxy.delete(id);
    }

}
