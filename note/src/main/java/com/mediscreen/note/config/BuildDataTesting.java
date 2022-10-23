package com.mediscreen.note.config;

import com.mediscreen.note.document.NoteDocument;
import com.mediscreen.note.repository.NoteRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class BuildDataTesting {

    private final NoteRepository noteRepository;

    public BuildDataTesting(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public void buildData() {

        noteRepository.deleteAll();

        ArrayList<NoteDocument> listNotes = new ArrayList<>();

        listNotes.add(new NoteDocument(
                "6177a31824f1d205e0b0692d",
                1L,
                LocalDateTime.now(),
                "Le patient déclare qu'il « se sent bien » Poids égal ou inférieur au niveau recommandé"));

        listNotes.add(new NoteDocument(
                "6177a31824f1d205e0b0692c",
                2L,
                LocalDateTime.now(),
                "Le patient déclare qu'il « se sent bien » Poids égal ou inférieur au niveau recommandé"));

        noteRepository.saveAll(listNotes);
    }
}
