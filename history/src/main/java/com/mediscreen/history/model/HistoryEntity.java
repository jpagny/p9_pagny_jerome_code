package com.mediscreen.history.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("history")
public class HistoryEntity {

    @Id
    private String id;

    private Long patientId;

    private LocalDate date;

    private String note;

}
