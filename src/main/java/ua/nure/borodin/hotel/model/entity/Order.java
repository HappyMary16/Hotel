package ua.nure.borodin.hotel.model.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Order extends AbstractOrder {

    private static final long serialVersionUID = -6297979517185130633L;

    private Integer bill;

    private OrderStatus status;

    private List<Long> roomsId;

    private Date setBill;

    public Order() {
    }

    public Order(Long userId, Date from, Date to, Integer bill, OrderStatus status, List<Long> roomsId) {
        super(userId, from, to);
        this.bill = bill;
        this.status = status;
        this.roomsId = roomsId;
        this.setBill = setBill;
    }

    public Integer getBill() {
        return bill;
    }

    public void setBill(Integer bill) {
        this.bill = bill;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<Long> getRoomsId() {
        return roomsId;
    }

    public void setRoomsId(List<Long> roomsId) {
        this.roomsId = roomsId;
    }

    public Date getSetBill() {
        return setBill;
    }

    public void setSetBill(Date setBill) {
        this.setBill = setBill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Order order = (Order) o;
        return Objects.equals(bill, order.bill) &&
                status == order.status &&
                Objects.equals(roomsId, order.roomsId) &&
                Objects.equals(setBill, order.setBill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bill, status, roomsId, setBill);
    }

    @Override
    public String toString() {
        return super.toString() + "Order{" +
                "bill=" + bill +
                ", status=" + status +
                ", roomsId=" + roomsId +
                ", setBill=" + setBill +
                '}';
    }
}
