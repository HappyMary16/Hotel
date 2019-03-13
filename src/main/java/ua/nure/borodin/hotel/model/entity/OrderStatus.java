package ua.nure.borodin.hotel.model.entity;


/**
 * OrderStatus entity.
 */
public enum OrderStatus {

	OPENED(0), CONFIRMED(1), PAID(2), CLOSED(3);

	private int id;

	OrderStatus(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public static OrderStatus getInstance(int id) {
		return OrderStatus.values()[id];
	}
}