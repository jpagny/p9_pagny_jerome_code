package com.mediscreen.note.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteDTO {

    private String id;

    @NotBlank(message = "Patient is required")
    private Long patientId;

    private LocalDateTime date;

    @NotBlank(message = "Note is required")
    private String note;

}
