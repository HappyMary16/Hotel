package ua.nure.borodin.hotel.dao;

/**
 * Holder for fields names of DB tables and beans.
 */
public final class Fields {
	
	// entities
	public static final String ENTITY__ID = "id";
	
	public static final String USER__LOGIN = "login";
	public static final String USER__PASSWORD = "password";
	public static final String USER__FIRST_NAME = "first_name";
	public static final String USER__LAST_NAME = "last_name";
	public static final String USER__ROLE_ID = "role_id";
	
	public static final String ORDER__BILL = "bill";
	public static final String ORDER__USER_ID = "user_id";
	public static final String ORDER__STATUS_ID= "status_id";
	public static final String ORDER__ROOM_ID= "room_id";
	public static final String ORDER__SET_BILL_DATE = "set_bill";
	public static final String ORDER__START_ORDER_DATE = "start_order";
	public static final String ORDER__END_ORDER_DATE= "end_order";
	
	public static final String ROOM_PRICE = "price";
	public static final String ROOM_NUMBER_PLACE = "number_places";
	public static final String ROOM_CATEGORY_ID = "category_id";

	// beans
	public static final String USER_ORDER_BEAN__ORDER_ID = "id";	
	public static final String USER_ORDER_BEAN__USER_FIRST_NAME = "first_name";	
	public static final String USER_ORDER_BEAN__USER_LAST_NAME = "last_name";	
	public static final String USER_ORDER_BEAN__ORDER_BILL = "bill";	
	public static final String USER_ORDER_BEAN__STATUS_NAME = "name";

	
	
}