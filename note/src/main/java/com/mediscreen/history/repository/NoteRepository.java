package com.mediscreen.history.repository;

import com.mediscreen.history.document.NoteDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends MongoRepository<NoteDocument, String> {
}
