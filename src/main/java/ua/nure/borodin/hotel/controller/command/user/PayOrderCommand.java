package ua.nure.borodin.hotel.controller.command.user;

import org.apache.log4j.Logger;
import ua.nure.borodin.hotel.Path;
import ua.nure.borodin.hotel.controller.command.Command;
import ua.nure.borodin.hotel.dao.OrderDao;
import ua.nure.borodin.hotel.model.entity.Order;
import ua.nure.borodin.hotel.model.entity.OrderStatus;
import ua.nure.borodin.hotel.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PayOrderCommand extends Command {

    private static final Logger log = Logger.getLogger(PayOrderCommand.class);

    private static final long serialVersionUID = 8007737115214478590L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Commands starts");

        OrderDao orderDao = daoFactory.getOrderDao();
        List<Order> orderForChange = orderDao
                .findOrders(Collections
                        .singletonList(request.getParameter("roomId")));
        for (Order o :
                orderForChange) {
            o.setStatus(OrderStatus.PAID);
            orderDao.update(o.getId(), o.getSetBill(), o.getStatus());
        }
        List<Order> orders = orderDao.findOrders((User)request.getSession().getAttribute("user"));
        log.trace("Found in DB: orders --> " + orders);

        orders.sort(Comparator.comparingInt(e -> e.getStatus().getId()));

        request.setAttribute("orders", orders);
        log.trace("Set the request attribute: orders --> " + orders);

        log.debug("Commands finished");
        return Path.PAGE__LIST_CLIENT_ORDER;
    }
}
