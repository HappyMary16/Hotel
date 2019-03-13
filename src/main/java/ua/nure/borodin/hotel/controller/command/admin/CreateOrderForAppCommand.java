package ua.nure.borodin.hotel.controller.command.admin;

import org.apache.log4j.Logger;
import ua.nure.borodin.hotel.controller.command.Command;
import ua.nure.borodin.hotel.model.entity.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class CreateOrderForAppCommand extends Command {

    private static final Logger log = Logger.getLogger(CreateOrderForAppCommand.class);

    private static final long serialVersionUID = 6082676698493260802L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Commands starts");
        if (!request.getParameterMap().containsKey("itemId")) {
            return new ListOrdersCommand().execute(request, response);
        } else {

            Application app = (Application) request.getSession().getAttribute("app");
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
            order.setUserId(app.getUserId());
            order.setFrom(app.getFrom());
            order.setTo(app.getTo());

            daoFactory.getApplicationDao().update(app.getId(), ApplicationStatus.CLOSED);
            daoFactory.getOrderDao().insert(order);

            // put user order beans list to request
            log.debug("Commands finished");
            return new ListOrdersCommand().execute(request, response);
        }
    }
}
