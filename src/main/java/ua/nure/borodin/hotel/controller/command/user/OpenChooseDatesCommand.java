package ua.nure.borodin.hotel.controller.command.user;

import ua.nure.borodin.hotel.Path;
import ua.nure.borodin.hotel.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OpenChooseDatesCommand extends Command {

    private static final long serialVersionUID = 1821360502094213579L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Path.PAGE__SET_DATES;
    }
}
