package ua.nure.borodin.hotel.model.bean;

import ua.nure.borodin.hotel.model.entity.Entity;

import java.util.Objects;

public class UserOrderBean extends Entity {

    private static final long serialVersionUID = -3045857694731641353L;

    private String firstName;

    private String lastName;

    private int orderBill;

    private String statusName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getOrderBill() {
        return orderBill;
    }

    public void setOrderBill(int orderBill) {
        this.orderBill = orderBill;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserOrderBean that = (UserOrderBean) o;
        return orderBill == that.orderBill &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(statusName, that.statusName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, orderBill, statusName);
    }
}
