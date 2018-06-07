package com.socialnetworkingkata.demo.service;

import java.util.List;

import com.socialnetworkingkata.demo.domain.Followed;

public interface IFollowedService {

	void addFollowed(String user, String followedUser);

	List<Followed> findAllByUser(String user);

}
