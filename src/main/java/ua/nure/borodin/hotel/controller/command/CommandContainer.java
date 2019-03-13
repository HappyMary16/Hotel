package ua.nure.borodin.hotel.controller.command;

import org.apache.log4j.Logger;
import ua.nure.borodin.hotel.controller.command.admin.ConfirmOrderCommand;
import ua.nure.borodin.hotel.controller.command.admin.ChooseRoomForOrderCommand;
import ua.nure.borodin.hotel.controller.command.admin.CreateOrderForAppCommand;
import ua.nure.borodin.hotel.controller.command.admin.ListOrdersCommand;
import ua.nure.borodin.hotel.controller.command.user.*;

import java.util.Map;
import java.util.TreeMap;

/**
 * Holder for all commands.
 */
public class CommandContainer {
	
	private static final Logger log = Logger.getLogger(CommandContainer.class);
	
	private static Map<String, Command> commands = new TreeMap<>();
	
	static {
		// common commands
		commands.put("login", new LoginCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("noCommand", new NoCommand());
		commands.put("viewSettings", new ViewSettingsCommand());
		commands.put("updateSettings", new UpdateSettingsCommand());
		
		// client commands
		commands.put("listRooms", new ListRoomsCommand());
		commands.put("makeOrder", new MakeOrderCommand());
		commands.put("openChooseDates", new OpenChooseDatesCommand());
		commands.put("makeApplication", new MakeApplicationCommand());
		commands.put("listClientOrders", new ListClientOrdersCommand());
		commands.put("payForOrder", new PayOrderCommand());
		
		// admin commands
		commands.put("listOrders", new ListOrdersCommand());
		commands.put("confirmOrder", new ConfirmOrderCommand());
        commands.put("createOrder", new ChooseRoomForOrderCommand());
        commands.put("makeOrderForApp", new CreateOrderForAppCommand());
		
		log.debug("Command container was successfully initialized");
		log.trace("Number of commands --> " + commands.size());
	}

	/**
	 * Returns command object with the given name.
	 * 
	 * @param commandName
	 *            Name of the command.
	 * @return Command object.
	 */
	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			log.trace("Command not found, name --> " + commandName);
			return commands.get("noCommand"); 
		}
		
		return commands.get(commandName);
	}
	
}