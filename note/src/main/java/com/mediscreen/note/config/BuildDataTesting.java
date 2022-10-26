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
                "abcd11",
                1L,
                LocalDateTime.now(),
                "Le patient déclare qu'il \"se sent très bien\"\n" +
                        "Poids égal ou inférieur au poids recommandé"));

        listNotes.add(new NoteDocument(
                "abcd12",
                1L,
                LocalDateTime.now(),
                "Le patient déclare qu'il se sent fatigué pendant la journée\n" +
                        "Il se plaint également de douleurs musculaires\n" +
                        "Tests de laboratoire indiquant une microalbumine élevée"));

        listNotes.add(new NoteDocument(
                "abcd13",
                1L,
                LocalDateTime.now(),
                "Le patient déclare qu'il ne se sent pas si fatigué que ça\n" +
                        "Fumeur, il a arrêté dans les 12 mois précédents\n" +
                        "Tests de laboratoire indiquant que les anticorps sont élevés"));


        listNotes.add(new NoteDocument(
                "abcd21",
                2L,
                LocalDateTime.now(),
                "Le patient déclare qu'il ressent beaucoup de stress au travail\n" +
                        "Il se plaint également que son audition est anormale dernièrement"));

        listNotes.add(new NoteDocument(
                "abcd22",
                2L,
                LocalDateTime.now(),
                "Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois\n" +
                        "Il remarque également que son audition continue d'être anormale"));

        listNotes.add(new NoteDocument(
                "abcd23",
                2L,
                LocalDateTime.now(),
                "Tests de laboratoire indiquant une microalbumine élevée"));

        listNotes.add(new NoteDocument(
                "abcd24",
                2L,
                LocalDateTime.now(),
                "Le patient déclare que tout semble aller bien\n" +
                        "Le laboratoire rapporte que l'hémoglobine A1C dépasse le niveau recommandé\n" +
                        "Le patient déclare qu’il fume depuis longtemps"));

        listNotes.add(new NoteDocument(
                "abcd31",
                3L,
                LocalDateTime.now(),
                "Le patient déclare qu'il fume depuis peu"));

        listNotes.add(new NoteDocument(
                "abcd32",
                3L,
                LocalDateTime.now(),
                "Tests de laboratoire indiquant une microalbumine élevée"));

        listNotes.add(new NoteDocument(
                "abcd33",
                3L,
                LocalDateTime.now(),
                "Le patient déclare qu'il est fumeur et qu'il a cessé de fumer l'année dernière\n" +
                        "Il se plaint également de crises d’apnée respiratoire anormales\n" +
                        "Tests de laboratoire indiquant un taux de cholestérol LDL élevé"));

        listNotes.add(new NoteDocument(
                "abcd34",
                3L,
                LocalDateTime.now(),
                "Tests de laboratoire indiquant un taux de cholestérol LDL élevé"));

        listNotes.add(new NoteDocument(
                "abcd41",
                4L,
                LocalDateTime.now(),
                "Le patient déclare qu'il lui est devenu difficile de monter les escaliers\n" +
                        "Il se plaint également d’être essoufflé\n" +
                        "Tests de laboratoire indiquant que les anticorps sont élevés\n" +
                        "Réaction aux médicaments"));

        listNotes.add(new NoteDocument(
                "abcd42",
                4L,
                LocalDateTime.now(),
                "Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps"));

        listNotes.add(new NoteDocument(
                "abcd43",
                4L,
                LocalDateTime.now(),
                "Le patient déclare avoir commencé à fumer depuis peu\n" +
                        "Hémoglobine A1C supérieure au niveau recommandé"));

        listNotes.add(new NoteDocument(
                "abcd51",
                5L,
                LocalDateTime.now(),
                "Le patient déclare avoir des douleurs au cou occasionnellement\n" +
                        "Le patient remarque également que certains aliments ont un goût différent\n" +
                        "Réaction apparente aux médicaments\n" +
                        "Poids corporel supérieur au poids recommandé"));

        listNotes.add(new NoteDocument(
                "abcd52",
                5L,
                LocalDateTime.now(),
                "Le patient déclare avoir eu plusieurs épisodes de vertige depuis la dernière visite.\n" +
                        "Taille incluse dans la fourchette concernée"));

        listNotes.add(new NoteDocument(
                "abcd53",
                5L,
                LocalDateTime.now(),
                "Le patient déclare qu'il souffre encore de douleurs cervicales occasionnelles\n" +
                        "Tests de laboratoire indiquant une microalbumine élevée\n" +
                        "Fumeur, il a arrêté dans les 12 mois précédents"));

        listNotes.add(new NoteDocument(
                "abcd54",
                5L,
                LocalDateTime.now(),
                "Le patient déclare avoir eu plusieurs épisodes de vertige depuis la dernière visite.\n" +
                        "Tests de laboratoire indiquant que les anticorps sont élevés"));

        listNotes.add(new NoteDocument(
                "abcd61",
                6L,
                LocalDateTime.now(),
                "Le patient déclare qu'il se sent bien\n" +
                        "Poids corporel supérieur au poids recommandé"));

        listNotes.add(new NoteDocument(
                "abcd62",
                6L,
                LocalDateTime.now(),
                "Le patient déclare qu'il se sent bien"));

        listNotes.add(new NoteDocument(
                "abcd71",
                7L,
                LocalDateTime.now(),
                "Le patient déclare qu'il se réveille souvent avec une raideur articulaire\n" +
                        "Il se plaint également de difficultés pour s’endormir\n" +
                        "Poids corporel supérieur au poids recommandé\n" +
                        "Tests de laboratoire indiquant un taux de cholestérol LDL élevé"));

        listNotes.add(new NoteDocument(
                "abcd81",
                8L,
                LocalDateTime.now(),
                "Les tests de laboratoire indiquent que les anticorps sont élevés\n" +
                        "Hémoglobine A1C supérieure au niveau recommandé"));

        listNotes.add(new NoteDocument(
                "abcd91",
                9L,
                LocalDateTime.now(),
                "Le patient déclare avoir de la difficulté à se concentrer sur ses devoirs scolaires\n" +
                        "Hémoglobine A1C supérieure au niveau recommandé"));

        listNotes.add(new NoteDocument(
                "abcd92",
                9L,
                LocalDateTime.now(),
                "Le patient déclare qu'il s’impatiente facilement en cas d’attente prolongée\n" +
                        "Il signale également que les produits du distributeur automatique ne sont pas bons\n" +
                        "Tests de laboratoire signalant des taux anormaux de cellules sanguines"));

        listNotes.add(new NoteDocument(
                "abcd93",
                9L,
                LocalDateTime.now(),
                "Le patient signale qu'il est facilement irrité par des broutilles\n" +
                        "Il déclare également que l'aspirateur des voisins fait trop de bruit\n" +
                        "Tests de laboratoire indiquant que les anticorps sont élevés"));

        listNotes.add(new NoteDocument(
                "abcd101",
                10L,
                LocalDateTime.now(),
                "Le patient déclare qu'il n'a aucun problème"));

        listNotes.add(new NoteDocument(
                "abcd102",
                10L,
                LocalDateTime.now(),
                "Le patient déclare qu'il n'a aucun problème\n" +
                        "Taille incluse dans la fourchette concernée\n" +
                        "Hémoglobine A1C supérieure au niveau recommandé"));

        listNotes.add(new NoteDocument(
                "abcd103",
                10L,
                LocalDateTime.now(),
                "Le patient déclare qu'il n'a aucun problème\n" +
                        "Poids corporel supérieur au poids recommandé\n" +
                        "Le patient a signalé plusieurs épisodes de vertige depuis sa dernière visite"));

        listNotes.add(new NoteDocument(
                "abcd104",
                10L,
                LocalDateTime.now(),
                "Le patient déclare qu'il n'a aucun problème\n" +
                        "Tests de laboratoire indiquant une microalbumine élevée"));


        noteRepository.saveAll(listNotes);
    }
}
