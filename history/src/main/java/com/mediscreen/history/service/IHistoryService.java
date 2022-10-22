package com.mediscreen.history.service;

import com.mediscreen.history.dto.HistoryDTO;
import com.mediscreen.history.exception.ResourceNotFoundException;

import java.util.List;

public interface IHistoryService {
    HistoryDTO get(String id) throws ResourceNotFoundException;

    List<HistoryDTO> getAll();

    HistoryDTO update(HistoryDTO historyDTO) throws ResourceNotFoundException;

    HistoryDTO create(HistoryDTO historyDTO);

    void delete(String id) throws ResourceNotFoundException;
}
