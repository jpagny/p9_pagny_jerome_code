package com.mediscreen.history.service;

import com.mediscreen.history.dto.NoteDTO;
import com.mediscreen.history.exception.ResourceNotFoundException;

import java.util.List;

public interface INoteService {
    NoteDTO get(String id) throws ResourceNotFoundException;

    List<NoteDTO> getAll();

    NoteDTO update(NoteDTO noteDTO) throws ResourceNotFoundException;

    NoteDTO create(NoteDTO noteDTO);

    void delete(String id) throws ResourceNotFoundException;
}
