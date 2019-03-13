package ua.nure.borodin.hotel.model.entity;

public enum Role {

    ADMIN(0, "Admin"), USER(1, "User");

    private int id;

    private String name;

    Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }

    public static Role getInstance(int id) {
        return Role.values()[id];
    }
}
