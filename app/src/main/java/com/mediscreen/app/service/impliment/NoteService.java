package com.mediscreen.app.service.impliment;

import com.mediscreen.app.bean.NoteBean;
import com.mediscreen.app.proxy.NoteProxy;
import com.mediscreen.app.service.INoteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
@Slf4j
public class NoteService implements INoteService {

    private final NoteProxy noteProxy;

    @Override
    public NoteBean get(String id) {
        log.debug("Call noteProxy.get(id)");
        return noteProxy.get(id);
    }

    @Override
    public ArrayList<NoteBean> getAll() {
        log.debug("Call noteProxy.getAll()");
        return noteProxy.getAll();
    }

    @Override
    public ArrayList<NoteBean> getAllByPatientId(Long patientId) {
        log.debug("Call noteProxy.getAllById(patientId)");
        return noteProxy.getAllById(patientId);
    }

    @Override
    public NoteBean update(NoteBean note) {
        log.debug("Call noteProxy.update(note)");
        return noteProxy.update(note);
    }

    @Override
    public NoteBean create(NoteBean note) {
        log.debug("Call noteProxy.create(note)");
        return noteProxy.create(note);
    }

    @Override
    public void delete(String id){
        log.debug("Call noteProxy.delete(id)");
        noteProxy.delete(id);
    }

}
