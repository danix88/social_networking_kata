package com.socialnetworkingkata.demo.util;

import java.time.ZoneId;
import java.util.function.Consumer;

import org.ocpsoft.prettytime.PrettyTime;

import com.socialnetworkingkata.demo.domain.Message;

public final class MessagePrinter {

	public static Consumer<? super Message> printUserMessageConsumer() {
		return m -> System.out.println(m.getText() +  " (" + new PrettyTime().format(java.util.Date.from(m.getAdded().atZone(ZoneId.systemDefault()).toInstant())) + ")");
	}

	public static Consumer<? super Message> printUserMessageWithLabelConsumer() {
		return m -> System.out.println(m.getUser() + " - " + m.getText() +  " (" + new PrettyTime().format(java.util.Date.from(m.getAdded().atZone(ZoneId.systemDefault()).toInstant())) + ")");
	}

	public static void printUnknownUserMessage(String user) {
		System.out.println("Unknown user: " + user);
	}

	public static void printUnknownCommandMessage(String command) {
		System.out.println("Unknown command: " + command);
	}

	public static void printWelcomeMessage() {
		System.out.println();
		System.out.println("#################################################");
		System.out.println("WELCOME TO SOCIAL NETWORKING KATA!");
		System.out.println("Allowed commands:");
		System.out.println(" - <user name> -> <message>");
		System.out.println(" - <user name>");
		System.out.println(" - <user name> follows <another user>");
		System.out.println(" - <user name> wall");
		System.out.println(" - exit");
		System.out.println("#################################################");
		System.out.println();
	}
}
