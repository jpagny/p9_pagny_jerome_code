package com.mediscreen.note.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteDTO {

    private String id;

    private Long patientId;

    private LocalDate date;

    private String note;

}
