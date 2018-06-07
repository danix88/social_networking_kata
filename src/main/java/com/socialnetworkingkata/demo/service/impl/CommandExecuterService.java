package com.socialnetworkingkata.demo.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.socialnetworkingkata.demo.domain.Message;
import com.socialnetworkingkata.demo.service.ICommandExecuterService;
import com.socialnetworkingkata.demo.service.IFollowedService;
import com.socialnetworkingkata.demo.service.IMessageService;
import com.socialnetworkingkata.demo.util.MessagePrinter;

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
				MessagePrinter.printUnknownCommandMessage(command);
			}
		}
	}

	private void readUserMessages(String user) {
		List<Message> messages = messageService.findAllByUser(user);
		messages.stream().forEach(MessagePrinter.printUserMessageConsumer());

		if(CollectionUtils.isEmpty(messages)) {
			MessagePrinter.printUnknownUserMessage(user);
		}
	}

	private void showUserWall(String user) {
		List<String> users = followerService.findAllByUser(user).stream().map(fu -> fu.getFollowedUser()).collect(Collectors.toList());
		users.add(user);

		List<Message> messages = messageService.findAllByUserIn(users);
		Collections.sort(messages, Comparator.comparing(Message::getAdded).reversed());

		messages.stream().forEach(MessagePrinter.printUserMessageWithLabelConsumer());
	}

	private void addUserFollower(String user, String userToFollow) {
		followerService.addFollowed(user, userToFollow);
	}

	private void addUserMessage(String user, String text) {
		messageService.addMessage(user, text);
	}

}
