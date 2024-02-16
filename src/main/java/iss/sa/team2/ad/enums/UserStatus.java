package iss.sa.team2.ad.enums;

public enum UserStatus {
	NORMAL("Normal"),
    MUTE_1_DAY("Muted for 1 day"),
    MUTE_7_DAYS("Muted for 7 days"),
    MUTE_30_DAYS("Muted for 30 days");

    private final String description;

    UserStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
