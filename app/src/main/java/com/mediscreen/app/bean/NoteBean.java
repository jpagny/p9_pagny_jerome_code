package com.mediscreen.app.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteBean {

    private String id;

    private Long patientId;

    private PatientBean patient;

    private LocalDateTime date;

    @NotBlank(message = "Note is required")
    private String note;

}
