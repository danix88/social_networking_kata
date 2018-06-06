package com.socialnetworkingkata.demo;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.socialnetworkingkata.demo.service.ICommandExecuterService;

@Component
@Profile("!test")
public class SocialNetworkingKataCommandLineRunner implements CommandLineRunner {

	private static final String EXIT_COMMAND = "EXIT";

	@Autowired
	private ICommandExecuterService commandExecuterService;
	private Scanner scanner;

	/*
	 * ASSUMPTIONS: 
	 *  - Usernames cannot contain spaces;
	 *  - Commands must be written in lower cases;
	 * 
	 */

	@Override
	public void run(String... args) throws Exception {
		scanner = new Scanner(System.in);
		String command = "";

		while (!EXIT_COMMAND.equalsIgnoreCase(command)) {
			commandExecuterService.executeCommand(command);

			System.out.print(">");
			command = scanner.nextLine();
		}

		System.out.println("Goodbye!");
	}

}
