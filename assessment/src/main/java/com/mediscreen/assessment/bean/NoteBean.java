package com.mediscreen.assessment.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteBean {

    private String id;

    private Long patientId;

    private LocalDateTime date;

    private String note;

}