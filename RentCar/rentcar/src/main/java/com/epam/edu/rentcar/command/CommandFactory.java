package com.epam.edu.rentcar.command;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.edu.rentcar.command.impl.CarBookingCommand;
import com.epam.edu.rentcar.command.impl.ClearCurrentCarCommand;
import com.epam.edu.rentcar.command.impl.CommitDamageOrderCommand;
import com.epam.edu.rentcar.command.impl.CommitOrderCommand;
import com.epam.edu.rentcar.command.impl.DamageOrderCommand;
import com.epam.edu.rentcar.command.impl.ExecuteOrderCommand;
import com.epam.edu.rentcar.command.impl.LanguageCommand;
import com.epam.edu.rentcar.command.impl.LoginCommand;
import com.epam.edu.rentcar.command.impl.LogoutCommand;
import com.epam.edu.rentcar.command.impl.PaidOrderCommand;
import com.epam.edu.rentcar.command.impl.PrepareOrderCommand;
import com.epam.edu.rentcar.command.impl.RegistrationCommand;
import com.epam.edu.rentcar.command.impl.RejectOrderCommand;
import com.epam.edu.rentcar.command.impl.SearchCarCommand;


public class CommandFactory {
	
	private static CommandFactory instance;
	private Map<String, Command> commands = new HashMap<String, Command>();

	public static CommandFactory getInstance() {
		if (instance==null){
			instance = new CommandFactory();
		}
		return instance;
	}

	public CommandFactory() {
		commands.put("login", new LoginCommand());
		commands.put("registration", new RegistrationCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("searchcar", new SearchCarCommand());
		commands.put("language", new LanguageCommand());
		commands.put("carbooking", new CarBookingCommand());
		commands.put("clearcurrentcar", new ClearCurrentCarCommand());
		commands.put("prepareorder", new PrepareOrderCommand());
		commands.put("commitorder", new CommitOrderCommand());
		commands.put("paidorder", new PaidOrderCommand());
		commands.put("rejectorder", new RejectOrderCommand());
		commands.put("executeorder", new ExecuteOrderCommand());
		commands.put("damageorder", new DamageOrderCommand());
		commands.put("commitdamageorder", new CommitDamageOrderCommand());
	}
	
	public String invoke(String commandName, HttpServletRequest request, HttpServletResponse response){
		return commands.get(commandName).execute(request, response);
	}
}
