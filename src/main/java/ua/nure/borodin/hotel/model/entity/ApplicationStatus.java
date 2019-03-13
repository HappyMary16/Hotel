package ua.nure.borodin.hotel.model.entity;

public enum ApplicationStatus {

    OPENED(0, "Open"), CLOSED(1, "Close");

    int id;

    String name;

    ApplicationStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ApplicationStatus getInstance(int id) {
        return ApplicationStatus.values()[id];
    }

    @Override
    public String toString() {
        return name;
    }

    public int getId() {
        return id;
    }
}
