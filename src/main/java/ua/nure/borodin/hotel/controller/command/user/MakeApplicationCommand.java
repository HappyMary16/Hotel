package ua.nure.borodin.hotel.controller.command.user;

import ua.nure.borodin.hotel.Path;
import ua.nure.borodin.hotel.controller.command.Command;
import ua.nure.borodin.hotel.model.entity.Application;
import ua.nure.borodin.hotel.model.entity.ApplicationStatus;
import ua.nure.borodin.hotel.model.entity.Category;
import ua.nure.borodin.hotel.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class MakeApplicationCommand extends Command {

    private static final long serialVersionUID = -2354934442949490030L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Application application = new Application();

        application.setStatus(ApplicationStatus.OPENED);
        application.setUserId(((User) request.getSession().getAttribute("user")).getId());
        application.setFrom((Date) request.getSession().getAttribute("from"));
        application.setTo((Date) request.getSession().getAttribute("to"));
        application.setNumberPlaces(Integer.parseInt(request.getParameter("numberPlaces")));
        application.setCategory(Category.getInstance(Integer.parseInt(request.getParameter("category"))));

        daoFactory.getApplicationDao().insert(application);

        return Path.PAGE__ORDER_CREATED;
    }
}
