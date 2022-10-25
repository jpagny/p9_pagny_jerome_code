package com.mediscreen.note.service.impliment;

import com.mediscreen.note.document.NoteDocument;
import com.mediscreen.note.dto.NoteDTO;
import com.mediscreen.note.exception.ResourceNotFoundException;
import com.mediscreen.note.repository.NoteRepository;
import com.mediscreen.note.service.INoteService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService implements INoteService {

    private final NoteRepository noteRepository;

    private final ModelMapper modelMapper;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
        modelMapper = new ModelMapper();
    }

    /**
     * Fetch a history
     *
     * @param id the id of history
     * @return the history
     * @throws ResourceNotFoundException the history doesn't exist
     */
    @Override
    public NoteDTO get(String id) throws ResourceNotFoundException {
        NoteDocument history = noteRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id)
        );
        return modelMapper.map(history, NoteDTO.class);
    }

    /**
     * Fetch all histories
     *
     * @return a list of histories
     */
    @Override
    public List<NoteDTO> getAll() {
        return noteRepository.findAll()
                .stream()
                .map(history -> modelMapper.map(history, NoteDTO.class))
                .sorted(Comparator.comparing(NoteDTO::getDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<NoteDTO> getAllByPatientId(Long patientId) {
        return noteRepository.findAllByPatientId(patientId)
                .stream()
                .map(history -> modelMapper.map(history, NoteDTO.class))
                .sorted(Comparator.comparing(NoteDTO::getDate).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Update a history
     *
     * @param history the history to update
     * @return history updated
     * @throws ResourceNotFoundException the history doesn't exist
     */
    @Override
    public NoteDTO update(NoteDTO history) throws ResourceNotFoundException {
        noteRepository.findById(history.getId()).orElseThrow(
                () -> new ResourceNotFoundException(String.valueOf(history.getId()))
        );

        NoteDocument historyToUpdate = modelMapper.map(history, NoteDocument.class);

        return modelMapper.map(noteRepository.save(historyToUpdate), NoteDTO.class);
    }

    /**
     * Create a new history
     *
     * @param history the history to create
     * @return the history created
     */
    @Override
    public NoteDTO create(NoteDTO history) {
        NoteDocument historyToCreate = modelMapper.map(history, NoteDocument.class);

        NoteDocument historyCreated = noteRepository.save(historyToCreate);

        return modelMapper.map(historyCreated, NoteDTO.class);
    }

    /**
     * Delete the history
     *
     * @param id the id of history
     * @throws ResourceNotFoundException the history doesn't exist
     */
    @Override
    public void delete(String id) throws ResourceNotFoundException {
        NoteDocument history = noteRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id)
        );
        noteRepository.delete(history);
    }
}
