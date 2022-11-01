package com.mediscreen.app.constant;

public enum RiskLevel {
    IN_DANGER("in danger"),
    EARLY_ONSET("early onset"),
    BORDERLINE("borderline"),
    NONE("none"),
    UNKNOWN("unknown");

    public final String label;

    RiskLevel(String label) {
        this.label = label;
    }

}

