package com.mediscreen.history.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryDTO {

    private Long id;

    private Long patientId;

    private LocalDate date;

    private String note;

}
