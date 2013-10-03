package com.epam.edu.rentcar.command;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.edu.rentcar.command.impl.LoginCommand;
import com.epam.edu.rentcar.command.impl.LogoutCommand;
import com.epam.edu.rentcar.command.impl.RegistrationCommand;


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
	}
	
	public void invoke(String commandName, HttpServletRequest request, HttpServletResponse response){
		commands.get(commandName).execute(request, response);
	}
}
