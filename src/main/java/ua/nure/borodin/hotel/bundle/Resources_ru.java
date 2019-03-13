package ua.nure.borodin.hotel.bundle;

import java.util.ListResourceBundle;

public class Resources_ru extends ListResourceBundle {

    public Object[][] getContents() {
        return contents;
    }

    static final Object[][] contents = {

            {"header_jspf.anchor.all_orders", "\u0417\u0430\u043A\u0430\u0437\u044B"},
            {"header_jspf.anchor.login", "\u0412\u043E\u0439\u0442\u0438"},
            {"header_jspf.anchor.logout", "\u0412\u044B\u0439\u0442\u0438"},
            {"header_jspf.anchor.order", "Заказать номер"},
            {"header_jspf.anchor.settings", "\u041D\u0430\u0441\u0442\u0440\u043E\u0439\u043A\u0438"},
            {"header_jspf.anchor.client_order", "Мои заказы"},

            {"room_jsp.button.make_an_order", "\u0421\u0434\u0435\u043B\u0430\u0442\u044C \u0437\u0430\u043A\u0430\u0437"},
            {"room_jsp.table.header.number", "Количество мест"},
            {"room_jsp.table.header.order", "\u0417\u0430\u043A\u0430\u0437\u0430\u0442\u044C"},
            {"room_jsp.table.header.price", "\u0426\u0435\u043D\u0430"},
            {"room_jsp.table.header.category", " Категория"},

            {"list_orders_jsp.table.header.bill", "\u0421\u0447\u0435\u0442"},
            {"list_orders_jsp.table.header.client", "\u041A\u043B\u0438\u0435\u043D\u0442"},
            {"list_orders_jsp.table.header.status", "\u0421\u0442\u0430\u0442\u0443\u0441"},

            {"login_jsp.button.login", "\u0412\u043E\u0439\u0442\u0438"},
            {"login_jsp.label.login", "Логин"},
            {"login_jsp.label.password", "\u041F\u0430\u0440\u043E\u043B\u044C"},

            {"settings_jsp.button.update", "\u041E\u0431\u043D\u043E\u0432\u0438\u0442\u044C"},
            {"settings_jsp.label.first_name", "Имя"},
            {"settings_jsp.label.last_name", " \u0424\u0430\u043C\u0438\u043B\u0438\u044F"},
            {"settings_jsp.label.localization", "\u041B\u043E\u043A\u0430\u043B\u0438\u0437\u0430\u0446\u0438\u044F"},

            {"room_jsp.order_made", "Заказ обрабатывается"},

            {"choose_dates_jsp.from_date", "С"},
            {"choose_dates_jsp.to_date", "По"},
            {"choose_dates_jsp.next", "Дале"},

            {"order_created_jsp.order_created", "Заказ отправлен на обработку"},

            {"list_client_order_jsp.room", "Номера"},
            {"list_client_order_jsp.pay", "Оплатить"}};

}
