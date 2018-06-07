package com.socialnetworkingkata.demo;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.socialnetworkingkata.demo.service.ICommandExecuterService;
import com.socialnetworkingkata.demo.util.MessagePrinter;

@Component
@Profile("!test")
public class SocialNetworkingKataCommandLineRunner implements CommandLineRunner {

	private static final String EXIT_COMMAND = "EXIT";

	@Autowired
	private ICommandExecuterService commandExecuterService;
	private Scanner scanner;

	@Override
	public void run(String... args) throws Exception {
		scanner = new Scanner(System.in);
		String command = "";

		MessagePrinter.printWelcomeMessage();
		while (!EXIT_COMMAND.equalsIgnoreCase(command)) {
			commandExecuterService.executeCommand(command);

			System.out.print("> ");
			command = scanner.nextLine().trim();
		}

		System.out.println("Goodbye!");
	}

}
