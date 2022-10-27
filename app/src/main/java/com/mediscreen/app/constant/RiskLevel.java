package com.mediscreen.app.constant;

public enum RiskLevel {
    IN_DANGER("in danger"),
    BORDERLINE("borderline"),
    EARLY_ONSET("early onset"),
    NONE("none"),
    UNKNOWN("unknown");

    public final String label;

    RiskLevel(String label) {
        this.label = label;
    }

}

