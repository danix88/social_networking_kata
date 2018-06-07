package com.socialnetworkingkata.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialnetworkingkata.demo.domain.Followed;
import com.socialnetworkingkata.demo.repository.FollowedRepository;
import com.socialnetworkingkata.demo.service.IFollowedService;

@Service
class FollowedService implements IFollowedService {

	@Autowired
	FollowedRepository followedRepository;

	@Override
	public void addFollowed(String user, String followedUser) {
		Followed followed = new Followed();
		followed.setUser(user);
		followed.setFollowedUser(followedUser);

		followedRepository.save(followed);
	}

	public List<Followed> findAllByUser(String user) {
		return followedRepository.findAllByUser(user);
	}

}
