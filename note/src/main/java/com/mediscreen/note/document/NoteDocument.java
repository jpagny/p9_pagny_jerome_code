package com.mediscreen.note.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("note")
public class NoteDocument {

    @Id
    private String id;

    private Long patientId;

    private LocalDate date;

    private String note;

}
