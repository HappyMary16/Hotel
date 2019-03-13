package ua.nure.borodin.hotel.controller.command.admin;

import org.apache.log4j.Logger;
import ua.nure.borodin.hotel.Path;
import ua.nure.borodin.hotel.controller.command.Command;
import ua.nure.borodin.hotel.model.bean.UserOrderBean;
import ua.nure.borodin.hotel.model.entity.Application;
import ua.nure.borodin.hotel.model.entity.ApplicationStatus;
import ua.nure.borodin.hotel.model.entity.Entity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.List;

/**
 * Lists orders.
 */
public class ListOrdersCommand extends Command {

	private static final long serialVersionUID = 1863978254689586513L;
	
	private static final Logger log = Logger.getLogger(ListOrdersCommand.class);
			
	@Override
	public String execute(HttpServletRequest request,
                          HttpServletResponse response) {
		log.debug("Commands starts");

		List<Application> applications = daoFactory.getApplicationDao().findApplications(ApplicationStatus.OPENED);
		request.setAttribute("applications", applications);
        for (Application s :
                applications) {
            System.out.println(s.getStatus().getId());
        }

		List<UserOrderBean> userOrderBeanList = daoFactory.getOrderDao().getUserOrderBeans();
		log.trace("Found in DB: userOrderBeanList --> " + userOrderBeanList);
		
		userOrderBeanList.sort(Comparator.comparingLong(Entity::getId));

		request.setAttribute("userOrderBeanList", userOrderBeanList);		
		log.trace("Set the request attribute: userOrderBeanList --> " + userOrderBeanList);
		
		log.debug("Commands finished");
		return Path.PAGE__LIST_ORDERS;
	}

}