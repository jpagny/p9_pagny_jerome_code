package com.mediscreen.assessment.constant;

public enum KeyWordTrigger {

    HEMOGLOBINE_A1C("Hémoglobine A1C"),
    MICROALBUMINE("Microalbumine"),
    TAILLE("Taille"),
    POIDS("Poids"),
    FUMEUR("Fumeur"),
    ANORMAL("Anormal"),
    CHOLESTEROL("Cholestérol"),
    VERTIGE("Vertige"),
    RECHUTE("Rechute"),
    REACTION("Réaction"),
    ANTICOPRS("Anticorps");

    public final String label;

    KeyWordTrigger(String label) {
        this.label = label;
    }

}
