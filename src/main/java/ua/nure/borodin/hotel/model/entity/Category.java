package ua.nure.borodin.hotel.model.entity;

public enum  Category {

    STANDARD(1, "Standard"), APARTMENT(2, "Apartment"), BUSINESS(3, "Business"), SUITE(4, "Suite");

    private int id;

    private String name;

    Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public static Category getInstance(int id) {
        return Category.values()[id - 1];
    }

    @Override
    public String toString() {
        return name;
    }
}
