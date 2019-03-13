package ua.nure.borodin.hotel.bundle;

import java.util.ListResourceBundle;

public class Resources extends ListResourceBundle {

    public Object[][] getContents() {
        return contents;
    }

    static final Object[][] contents = {

            {"header_jspf.anchor.all_orders", "All orders"},
            {"header_jspf.anchor.login", "Login"},
            {"header_jspf.anchor.logout", "Logout"},
            {"header_jspf.anchor.order", "Order a room"},
            {"header_jspf.anchor.settings", "Settings"},
            {"header_jspf.anchor.client_order", "My orders"},

            {"room_jsp.button.make_an_order", "Make an order"},
            {"room_jsp.table.header.number", "Number places"},
            {"room_jsp.table.header.order", "Order"},
            {"room_jsp.table.header.price", "Price"},
            {"room_jsp.table.header.category", "Category"},

            {"list_orders_jsp.table.header.bill", "Bill"},
            {"list_orders_jsp.table.header.client", "Client"},
            {"list_orders_jsp.table.header.status", "OrderStatus"},

            {"login_jsp.button.login", "Login"},
            {"login_jsp.label.login", "Login"},
            {"login_jsp.label.password", "Password"},

            {"settings_jsp.button.update", "Update"},
            {"settings_jsp.label.first_name", "First name"},
            {"settings_jsp.label.last_name", "Last name"},
            {"settings_jsp.label.localization", "Localization"},

            {"room_jsp.order_made", "Order is processed"},

            {"choose_dates_jsp.from_date", "From"},
            {"choose_dates_jsp.to_date", "To"},
            {"choose_dates_jsp.next", "Next"},

            {"order_created_jsp.order_created", "Order send for processing"},

            {"list_client_order_jsp.room", "Rooms"},
            {"list_client_order_jsp.pay", "Pay"}};

}
