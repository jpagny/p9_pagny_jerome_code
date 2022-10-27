package com.mediscreen.diabetesAssessment.constant;

public enum RiskLevel {
    NONE("none"),
    BORDERLINE("borderline"),
    IN_DANGER("in danger"),
    EARLY_ONSET("early onset"),
    UNKNOWN("unknown");

    public final String label;

    RiskLevel(String label) {
        this.label = label;
    }

}

