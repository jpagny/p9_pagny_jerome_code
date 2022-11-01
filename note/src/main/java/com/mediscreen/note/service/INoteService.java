package com.mediscreen.note.service;

import com.mediscreen.note.dto.NoteDTO;
import com.mediscreen.note.exception.ResourceNotFoundException;

import java.util.List;

public interface INoteService {
    NoteDTO get(String id) throws ResourceNotFoundException;

    List<NoteDTO> getAll();

    List<NoteDTO> getAllByPatientId(Long patientId);

    NoteDTO update(NoteDTO noteDTO) throws ResourceNotFoundException;

    NoteDTO create(NoteDTO noteDTO);

    void delete(String id) throws ResourceNotFoundException;
}
