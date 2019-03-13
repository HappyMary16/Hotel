package ua.nure.borodin.hotel;

/**
 * Path holder (jsp pages, controller commands).
 */
public final class Path {

    // pages
    public static final String PAGE__LOGIN = "/index.jsp";
    public static final String PAGE__ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
    public static final String PAGE__LIST_ROOMS_CLIENT = "/WEB-INF/jsp/client/list_rooms.jsp";
    public static final String PAGE__ORDER_CREATED = "/WEB-INF/jsp/client/order_created.jsp";
    public static final String PAGE__LIST_ORDERS = "/WEB-INF/jsp/admin/list_orders.jsp";
    public static final String PAGE__SETTINGS = "/WEB-INF/jsp/settings.jsp";
    public static final String PAGE__FULL_DATA_ABOUT_ROOM = "/WEB-INF/jsp/client/room_data.jsp";
    public static final String PAGE__SET_DATES = "/WEB-INF/jsp/client/choose_dates.jsp";
    public static final String PAGE__LIST_CLIENT_ORDER = "/WEB-INF/jsp/client/list_client_order.jsp";
    public static final String PAGE_LIST_ROOMS_ADMIN = "/WEB-INF/jsp/admin/list_rooms.jsp";

    // commands
    public static final String COMMAND__LIST_ORDERS = "/controller?command=listOrders";
    public static final String COMMAND__LIST_CLIENT_ORDERS = "/controller?command=listClientOrders";

}
