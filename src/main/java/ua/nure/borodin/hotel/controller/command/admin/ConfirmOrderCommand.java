package ua.nure.borodin.hotel.controller.command.admin;

import org.apache.log4j.Logger;
import ua.nure.borodin.hotel.Path;
import ua.nure.borodin.hotel.controller.command.Command;
import ua.nure.borodin.hotel.dao.OrderDao;
import ua.nure.borodin.hotel.model.bean.UserOrderBean;
import ua.nure.borodin.hotel.model.entity.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ConfirmOrderCommand extends Command {

    private static final Logger log = Logger.getLogger(ConfirmOrderCommand.class);

    private static final long serialVersionUID = -5531144788630831806L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Commands starts");

        OrderDao orderDao = daoFactory.getOrderDao();
        Order order = orderDao
                .findOrder(Long.parseLong(request.getParameter("orderId")));

        order.setSetBill(new Date());
        orderDao.update(order.getId(), order.getSetBill(), OrderStatus.CONFIRMED);

        List<Application> applications = daoFactory.getApplicationDao().findApplications(ApplicationStatus.OPENED);
        request.setAttribute("applications", applications);

        List<UserOrderBean> userOrderBeanList = orderDao.getUserOrderBeans();
        log.trace("Found in DB: userOrderBeanList --> " + userOrderBeanList);

        userOrderBeanList.sort(Comparator.comparingLong(Entity::getId));

        request.setAttribute("userOrderBeanList", userOrderBeanList);
        log.trace("Set the request attribute: userOrderBeanList --> " + userOrderBeanList);

        log.debug("Commands finished");
        return Path.PAGE__LIST_ORDERS;
    }
}
