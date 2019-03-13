package ua.nure.borodin.hotel.controller.command;

import org.apache.log4j.Logger;
import ua.nure.borodin.hotel.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Logout command.
 */
public class LogoutCommand extends Command {

	private static final long serialVersionUID = -2785976616686657267L;

	private static final Logger log = Logger.getLogger(LogoutCommand.class);

	@Override
	public String execute(HttpServletRequest request,
                          HttpServletResponse response) {
		log.debug("Command starts");
		HttpSession session = request.getSession(false);
		if (session != null)
			session.invalidate();

		log.debug("Command finished");
		return Path.PAGE__LOGIN;
	}

}