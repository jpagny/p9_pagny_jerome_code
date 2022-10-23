package com.mediscreen.note;

import com.mediscreen.note.config.BuildDataTesting;
import com.mediscreen.note.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class NoteApplication implements CommandLineRunner {

    @Value("${demo.mode}")
    private boolean demoMode;

    private final NoteRepository noteRepository;

    public NoteApplication(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(NoteApplication.class, args);
    }

    @Override
    public void run(String... args) {
        if (demoMode) {
            BuildDataTesting buildData = new BuildDataTesting(noteRepository);
            buildData.buildData();
        }
    }

}
