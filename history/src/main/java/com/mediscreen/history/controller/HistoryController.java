package com.mediscreen.history.controller;

import com.mediscreen.history.dto.HistoryDTO;
import com.mediscreen.history.exception.ResourceNotFoundException;
import com.mediscreen.history.service.impliment.HistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<HistoryDTO>> getAllHistories() {
        List<HistoryDTO> histories = historyService.getAll();

        if (histories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(histories, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<HistoryDTO> getHistory(@PathVariable("id") String id) {
        try {
            HistoryDTO history = historyService.get(id);
            return new ResponseEntity<>(history, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<HistoryDTO> update(@RequestBody HistoryDTO history) {
        try {
            HistoryDTO historyUpdated = historyService.update(history);
            return new ResponseEntity<>(historyUpdated, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<HistoryDTO> create(@RequestBody HistoryDTO history) {
        HistoryDTO historyCreated = historyService.create(history);
        return new ResponseEntity<>(historyCreated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        try {
            historyService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}



