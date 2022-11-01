package com.mediscreen.note.repository;

import com.mediscreen.note.document.NoteDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends MongoRepository<NoteDocument, String> {
    List<NoteDocument> findAllByPatientId(Long patientId);
}
