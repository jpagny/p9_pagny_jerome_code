package com.mediscreen.history.service.impliment;

import com.mediscreen.history.dto.HistoryDTO;
import com.mediscreen.history.exception.ResourceNotFoundException;
import com.mediscreen.history.model.HistoryEntity;
import com.mediscreen.history.repository.HistoryRepository;
import com.mediscreen.history.service.IHistoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryService implements IHistoryService {

    private final HistoryRepository historyRepository;

    private final ModelMapper modelMapper;

    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
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
    public HistoryDTO get(String id) throws ResourceNotFoundException {
        HistoryEntity history = historyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id)
        );
        return modelMapper.map(history, HistoryDTO.class);
    }

    /**
     * Fetch all histories
     *
     * @return a list of histories
     */
    @Override
    public List<HistoryDTO> getAll() {
        return historyRepository.findAll()
                .stream()
                .map(history -> modelMapper.map(history, HistoryDTO.class))
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
    public HistoryDTO update(HistoryDTO history) throws ResourceNotFoundException {
        historyRepository.findById(history.getId()).orElseThrow(
                () -> new ResourceNotFoundException(String.valueOf(history.getId()))
        );

        HistoryEntity historyToUpdate = modelMapper.map(history, HistoryEntity.class);

        return modelMapper.map(historyRepository.save(historyToUpdate), HistoryDTO.class);
    }

    /**
     * Create a new history
     *
     * @param history the history to create
     * @return the history created
     */
    @Override
    public HistoryDTO create(HistoryDTO history) {
        HistoryEntity historyToCreate = modelMapper.map(history, HistoryEntity.class);

        HistoryEntity historyCreated = historyRepository.save(historyToCreate);

        return modelMapper.map(historyCreated, HistoryDTO.class);
    }

    /**
     * Delete the history
     *
     * @param id the id of history
     * @throws ResourceNotFoundException the history doesn't exist
     */
    @Override
    public void delete(String id) throws ResourceNotFoundException {
        HistoryEntity history = historyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id)
        );
        historyRepository.delete(history);
    }
}
