package com.socialnetworkingkata.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@Profile("test")
@RunWith(SpringRunner.class)
public class SocialNetworkingKataApplicationTests {

	@Test
	public void contextLoads() {
	}

}
