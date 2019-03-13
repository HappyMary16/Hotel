package ua.nure.borodin.hotel.model.entity;

import java.util.Date;
import java.util.Objects;

public abstract class AbstractOrder extends Entity {

    private static final long serialVersionUID = -8297919334555587221L;

    private Long userId;

    private Date from;

    private Date to;

    public AbstractOrder(Long userId, Date from, Date to) {
        this.userId = userId;
        this.from = from;
        this.to = to;
    }

    public AbstractOrder() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AbstractOrder that = (AbstractOrder) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(from, that.from) &&
                Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, from, to);
    }

    @Override
    public String toString() {
        return "AbstractOrder{" +
                "userId=" + userId +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
