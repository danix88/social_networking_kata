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

import com.socialnetworkingkata.demo.domain.Followed;

@SpringBootTest
@Profile("test")
@RunWith(SpringRunner.class)
public class FollowedServiceTest {

	@Autowired
	private FollowedService followedService;

	@Test
	public void findAllByUserTest() {
		followedService.addFollowed("Alice", "Bob");
		followedService.addFollowed("Alice", "Charlie");

		List<Followed> followedByAlice = followedService.findAllByUser("Alice");
		assertThat(followedByAlice.size(), equalTo(2));
		assertTrue(followedByAlice.stream().filter(f -> f.getFollowedUser().equals("Bob")).findFirst().isPresent());
		assertTrue(followedByAlice.stream().filter(f -> f.getFollowedUser().equals("Charlie")).findFirst().isPresent());

		List<Followed> followedByBob = followedService.findAllByUser("Bob");
		assertThat(followedByBob.size(), equalTo(0));
	}

}
