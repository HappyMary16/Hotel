package ua.nure.borodin.hotel.controller.command;

import org.apache.log4j.Logger;
import ua.nure.borodin.hotel.Path;
import ua.nure.borodin.hotel.model.entity.Role;
import ua.nure.borodin.hotel.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Login command.
 */
public class LoginCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;
	
	private static final Logger log = Logger.getLogger(LoginCommand.class);
	
	@Override
	public String execute(HttpServletRequest request,
                          HttpServletResponse response) {

		log.debug("Command starts");
		
		HttpSession session = request.getSession();
		
		// obtain login and password from the request
		String login = request.getParameter("login");
		log.trace("Request parameter: loging --> " + login);
		
		String password = request.getParameter("password");
		
		// error handler
		String errorMessage = null;		
		String forward = Path.PAGE__ERROR_PAGE;
		
		if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
			errorMessage = "Login/password cannot be empty";
			request.setAttribute("errorMessage", errorMessage);
			log.error("errorMessage --> " + errorMessage);
			return forward;
		}
		
		User user = daoFactory.getUserDao().findUserByLogin(login);
		log.trace("Found in DB: user --> " + user);
			
		if (user == null || !password.equals(user.getPassword())) {
			errorMessage = "Cannot find user with such login/password";
			request.setAttribute("errorMessage", errorMessage);
			log.error("errorMessage --> " + errorMessage);
			return forward;
		} else {
			Role userRole = user.getRole();
			log.trace("userRole --> " + userRole);
				
			if (userRole == Role.ADMIN)
				forward = Path.COMMAND__LIST_ORDERS;
		
			if (userRole == Role.USER)
				forward = Path.COMMAND__LIST_CLIENT_ORDERS;
			
			session.setAttribute("user", user);
			log.trace("Set the session attribute: user --> " + user);
			session.setAttribute("userRole", userRole);
			log.trace("Set the session attribute: userRole --> " + userRole);
				
			log.info("User " + user + " logged as " + userRole.toString().toLowerCase());
		}
		
		log.debug("Command finished");
		return forward;
	}

}