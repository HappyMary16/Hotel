package ua.nure.borodin.hotel.controller.command.user;

import org.apache.log4j.Logger;
import ua.nure.borodin.hotel.Path;
import ua.nure.borodin.hotel.controller.command.Command;
import ua.nure.borodin.hotel.model.entity.Order;
import ua.nure.borodin.hotel.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.List;

public class ListClientOrdersCommand extends Command {

    private static final Logger log = Logger.getLogger(ListClientOrdersCommand.class);

    private static final long serialVersionUID = -7915976676040440967L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Commands starts");

        List<Order> orders = daoFactory.getOrderDao().findOrders((User)request.getSession().getAttribute("user"));
        log.trace("Found in DB: orders --> " + orders);

        orders.sort(Comparator.comparingInt(e -> e.getStatus().getId()));

        request.setAttribute("orders", orders);
        log.trace("Set the request attribute: orders --> " + orders);

        log.debug("Commands finished");
        return Path.PAGE__LIST_CLIENT_ORDER;
    }
}
