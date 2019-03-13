package ua.nure.borodin.hotel.model.entity;

import java.util.Objects;

public class Application extends AbstractOrder {

    private static final long serialVersionUID = 5259003552957917105L;

    int numberPlaces;

    Category category;

    ApplicationStatus status;

    public int getNumberPlaces() {
        return numberPlaces;
    }

    public void setNumberPlaces(int numberPlaces) {
        this.numberPlaces = numberPlaces;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Application that = (Application) o;
        return numberPlaces == that.numberPlaces &&
                category == that.category &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberPlaces, category, status);
    }
}


