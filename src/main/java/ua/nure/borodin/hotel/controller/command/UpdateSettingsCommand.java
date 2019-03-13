package ua.nure.borodin.hotel.controller.command;

import org.apache.log4j.Logger;
import ua.nure.borodin.hotel.Path;
import ua.nure.borodin.hotel.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;

/**
 * Update settings items.
 * 
 */
public class UpdateSettingsCommand extends Command {

	private static final long serialVersionUID = 7732286214029478505L;

	private static final Logger log = Logger.getLogger(UpdateSettingsCommand.class);

	@Override
	public String execute(HttpServletRequest request,
                          HttpServletResponse response) {
		
		log.debug("Command starts");
		System.out.println("command  update");
		// UPDATE USER ////////////////////////////////////////////////////////

        Config.set(request.getSession(),
                Config.FMT_LOCALE,
                new java.util.Locale(request.getParameter("localeToSet")));

        User user = (User)request.getSession().getAttribute("user");
		boolean updateUser = false;
		
		// update first name
		String firstName = request.getParameter("firstName");
		if (firstName != null && !firstName.isEmpty()) {
			user.setFirstName(firstName);
			updateUser = true;
		}

		// update last name
		String lastName = request.getParameter("lastName");
		if (lastName != null && !lastName.isEmpty()) {
			user.setLastName(lastName);
			updateUser = true;
		}
		
		if (updateUser) {
			daoFactory.getUserDao().updateUser(user);
		}

		log.debug("Command finished");
		return Path.PAGE__SETTINGS;
	}

}