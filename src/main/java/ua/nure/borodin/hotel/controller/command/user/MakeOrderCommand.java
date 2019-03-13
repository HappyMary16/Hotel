package ua.nure.borodin.hotel.controller.command.user;

import org.apache.log4j.Logger;
import ua.nure.borodin.hotel.Path;
import ua.nure.borodin.hotel.controller.command.Command;
import ua.nure.borodin.hotel.model.entity.Order;
import ua.nure.borodin.hotel.model.entity.OrderStatus;
import ua.nure.borodin.hotel.model.entity.Room;
import ua.nure.borodin.hotel.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MakeOrderCommand extends Command {

    private static final Logger log = Logger.getLogger(MakeOrderCommand.class);

    private static final long serialVersionUID = -4402855515891413503L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Commands starts");
        if (!request.getParameterMap().containsKey("itemId")) {
            return Path.PAGE__FULL_DATA_ABOUT_ROOM;
        } else {

            Order order = new Order();

            List<Long> roomsId = new ArrayList<>();
            int bill = 0;

            for (Room r :
                    daoFactory.getRoomsDao().findRooms(request.getParameterValues("itemId"))) {
                roomsId.add(r.getId());
                bill += r.getPrice();
            }
            order.setRoomsId(roomsId);
            order.setBill(bill);

            order.setStatus(OrderStatus.OPENED);
            order.setUserId(((User) request.getSession().getAttribute("user")).getId());
            order.setFrom((Date) request.getSession().getAttribute("from"));
            order.setTo((Date) request.getSession().getAttribute("to"));

            daoFactory.getOrderDao().insert(order);

            // put user order beans list to request
            log.debug("Commands finished");
            return Path.PAGE__ORDER_CREATED;
        }
    }
}
