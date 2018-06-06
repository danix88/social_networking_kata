package com.socialnetworkingkata.demo.service.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import com.socialnetworkingkata.demo.domain.Message;

@SpringBootTest
@Profile("test")
@RunWith(SpringRunner.class)
public class MessageServiceTest {

	@Autowired
	private MessageService messageService;

	@Test
	public void findAllByUserTest() {
		messageService.addMessage("Alice", "My first message");
		messageService.addMessage("Alice", "My second message");

		List<Message> messages = messageService.findAllByUser("Alice");
		assertThat(messages.size(), equalTo(2));
		assertTrue(messages.stream().filter(f -> f.getText().equals("My first message")).findFirst().isPresent());
		assertTrue(messages.stream().filter(f -> f.getText().equals("My second message")).findFirst().isPresent());

		messages = messageService.findAllByUser("Bob");
		assertThat(messages.size(), equalTo(0));
	}

}
