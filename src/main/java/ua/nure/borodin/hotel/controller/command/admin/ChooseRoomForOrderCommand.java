package ua.nure.borodin.hotel.controller.command.admin;

import org.apache.log4j.Logger;
import ua.nure.borodin.hotel.Path;
import ua.nure.borodin.hotel.controller.command.Command;
import ua.nure.borodin.hotel.model.entity.Application;
import ua.nure.borodin.hotel.model.entity.Room;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

public class ChooseRoomForOrderCommand extends Command {

    private static final long serialVersionUID = -8174856865192830277L;

    private static final Logger log = Logger.getLogger(ChooseRoomForOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Application application = daoFactory
                .getApplicationDao()
                .findApplications(Collections
                        .singletonList(request
                                .getParameter("appId"))).get(0);
        request.getSession().setAttribute("app", application);

        List<Room> rooms = daoFactory.getRoomsDao().findAvailableRooms(application.getFrom(), application.getTo());
        request.setAttribute("rooms", rooms);
        log.trace("Set the request attribute: rooms --> " + rooms);

        return Path.PAGE_LIST_ROOMS_ADMIN;
    }
}
