package com.socialnetworkingkata.demo.service.impl;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@Profile("test")
@RunWith(SpringRunner.class)
public class CommandExecuterServiceTest {

	@Autowired
	private CommandExecuterService commandExecuterService;

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void restoreStreams() {
	    System.setOut(System.out);
	    System.setErr(System.err);
	}

	@Test
	public void executeCommandTest() {
		commandExecuterService.executeCommand("Alice -> I love the weather today");
		assertEquals("", outContent.toString());

		commandExecuterService.executeCommand("Alice");
		assertEquals("", outContent.toString());

		commandExecuterService.executeCommand("Charlie follows Alice");
		assertEquals("", outContent.toString());

		commandExecuterService.executeCommand("Other command");
		assertEquals("Unknown command: Other command", outContent.toString().trim());
	}
}
