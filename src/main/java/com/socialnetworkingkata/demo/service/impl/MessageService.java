package com.socialnetworkingkata.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialnetworkingkata.demo.domain.Message;
import com.socialnetworkingkata.demo.repository.MessageRepository;
import com.socialnetworkingkata.demo.service.IMessageService;

@Service
class MessageService implements IMessageService {

	@Autowired
	MessageRepository messageRepository;

	@Override
	public void addMessage(String user, String text) {
		Message message = new Message();
		message.setUser(user);
		message.setText(text);

		messageRepository.save(message);
	}

	@Override
	public List<Message> findAllByUser(String user) {
		return messageRepository.findAllByUserOrderByAddedDesc(user);
	}

	@Override
	public List<Message> findAllByUserIn(List<String> users) {
		return messageRepository.findAllByUserInOrderByAddedDesc(users);
	}

}
