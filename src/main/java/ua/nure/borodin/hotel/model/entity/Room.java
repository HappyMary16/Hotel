package ua.nure.borodin.hotel.model.entity;

import java.util.Objects;

public class Room extends Entity {

    private static final long serialVersionUID = -9050951450533266593L;

    int numberPlace;

    int price;

    Category category;

    public int getNumberPlace() {
        return numberPlace;
    }

    public void setNumberPlace(int numberPlace) {
        this.numberPlace = numberPlace;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Room room = (Room) o;
        return numberPlace == room.numberPlace &&
                price == room.price &&
                category == room.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberPlace, price, category);
    }
}
