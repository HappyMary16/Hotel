package ua.nure.borodin.hotel.controller.command.user;

import org.apache.log4j.Logger;
import ua.nure.borodin.hotel.Path;
import ua.nure.borodin.hotel.controller.command.Command;
import ua.nure.borodin.hotel.model.entity.Room;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Lists menu items.
 */
public class ListRoomsCommand extends Command {

	private static final long serialVersionUID = 7732286214029478505L;

	private static final Logger log = Logger.getLogger(ListRoomsCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		log.debug("Command starts");

		Date from = null;
		Date to = null;
        try {
            from = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("from"));
			to = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("to"));
		} catch (ParseException e) {
            e.printStackTrace();
        }

		List<Room> rooms = daoFactory.getRoomsDao().findAvailableRooms(from, to);
		log.trace("Found in DB: RoomsList --> " + rooms);

		rooms.sort(Comparator.comparingInt(o -> o.getCategory().getId()));

        request.getSession().setAttribute("from", from);
        request.getSession().setAttribute("to", to);
		request.setAttribute("rooms", rooms);
		log.trace("Set the request attribute: rooms --> " + rooms);
		
		log.debug("Command finished");
		return Path.PAGE__LIST_ROOMS_CLIENT;
	}

}