package com.socialnetworkingkata.demo.service;

import java.util.List;

import com.socialnetworkingkata.demo.domain.Message;

public interface IMessageService {

	void addMessage(String user, String text);

	List<Message> findAllByUser(String user);

	List<Message> findAllByUserIn(List<String> users);

}
