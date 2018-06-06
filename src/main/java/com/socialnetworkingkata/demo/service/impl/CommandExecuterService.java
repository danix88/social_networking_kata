package com.socialnetworkingkata.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.socialnetworkingkata.demo.service.ICommandExecuterService;

@Service
class CommandExecuterService implements ICommandExecuterService {

	private static final String POSTING_KEYWORD = "->";
	private static final String FOLLOWING_KEYWORD = "follows";
	private static final String WALL_KEYWORD = "wall";

	@Override
	public void executeCommand(String command) {
		if (!StringUtils.isEmpty(command)) {
			String[] tokens = command.split(" ");
			if (command.contains(POSTING_KEYWORD)) {
				addUserPost(tokens[0], command.substring(tokens[0].length() + tokens[1].length() + 1).trim());
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
		// TODO Auto-generated method stub
		
	}

	private void showUserWall(String user) {
		// TODO Auto-generated method stub

	}

	private void addUserFollower(String user, String userToFollow) {
		// TODO Auto-generated method stub

	}

	private void addUserPost(String user, String message) {
		// TODO Auto-generated method stub

	}

}
