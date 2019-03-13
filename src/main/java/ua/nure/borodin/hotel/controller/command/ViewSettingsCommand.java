package ua.nure.borodin.hotel.controller.command;

import ua.nure.borodin.hotel.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * View settings command.
 */
public class ViewSettingsCommand extends Command {
	
	private static final long serialVersionUID = -3071536593627692473L;
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		return Path.PAGE__SETTINGS;
	}

}