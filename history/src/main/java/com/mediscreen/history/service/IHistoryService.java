package com.mediscreen.history.service;

import com.mediscreen.history.dto.HistoryDTO;
import com.mediscreen.history.exception.ResourceNotFoundException;

import java.util.List;

public interface IHistoryService {
    HistoryDTO get(Long id) throws ResourceNotFoundException;

    List<HistoryDTO> getAll();

    HistoryDTO update(HistoryDTO historyDTO) throws ResourceNotFoundException;

    HistoryDTO create(HistoryDTO historyDTO);

    void delete(Long id) throws ResourceNotFoundException;
}
