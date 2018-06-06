package com.socialnetworkingkata.demo.service.impl;

import java.time.ZoneId;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.socialnetworkingkata.demo.domain.Message;
import com.socialnetworkingkata.demo.service.ICommandExecuterService;
import com.socialnetworkingkata.demo.service.IFollowedService;
import com.socialnetworkingkata.demo.service.IMessageService;

@Service
class CommandExecuterService implements ICommandExecuterService {

	private static final String POSTING_KEYWORD = "->";
	private static final String FOLLOWING_KEYWORD = "follows";
	private static final String WALL_KEYWORD = "wall";

	@Autowired
	IMessageService messageService;
	@Autowired
	IFollowedService followerService;

	@Override
	public void executeCommand(String command) {
		if (!StringUtils.isEmpty(command)) {
			String[] tokens = command.split(" ");
			if (command.contains(POSTING_KEYWORD)) {
				addUserMessage(tokens[0], command.substring(tokens[0].length() + tokens[1].length() + 1).trim());
			} else if (command.contains(FOLLOWING_KEYWORD)) {
				addUserFollower(tokens[0], tokens[2]);
			} else if (command.contains(WALL_KEYWORD)) {
				showUserWall(tokens[0]);
			} else if(tokens.length == 1) {
				readUserMessages(tokens[0]);
			} else {	
				System.out.println("Unknown command: " + command);
			}
		}
	}

	private void readUserMessages(String user) {
		messageService.findAllByUser(user).stream().forEach(printUserMessage());
	}

	private void showUserWall(String user) {
		List<String> users = followerService.findAllByUser(user).stream().map(fu -> fu.getFollowedUser()).collect(Collectors.toList());
		users.add(user);

		List<Message> messages = messageService.findAllByUserIn(users);
		Collections.sort(messages, Comparator.comparing(Message::getAdded).reversed());

		messages.stream().forEach(printUserMessageWithLabel());
	}

	private void addUserFollower(String user, String userToFollow) {
		followerService.addFollowed(user, userToFollow);
	}

	private void addUserMessage(String user, String text) {
		messageService.addMessage(user, text);
	}

	private Consumer<? super Message> printUserMessage() {
		return m -> System.out.println(m.getText() +  " (" + new PrettyTime().format(java.util.Date.from(m.getAdded().atZone(ZoneId.systemDefault()).toInstant())) + ")");
	}

	private Consumer<? super Message> printUserMessageWithLabel() {
		return m -> System.out.println(m.getUser() + " - " + m.getText() +  " (" + new PrettyTime().format(java.util.Date.from(m.getAdded().atZone(ZoneId.systemDefault()).toInstant())) + ")");
	}
}
