package ua.nure.borodin.hotel.model.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Root of all entities which have identifier field.
 */
public abstract class Entity implements Serializable {

    private static final long serialVersionUID = 5361152901083775471L;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
