package com.socialnetworkingkata.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.socialnetworkingkata.demo.domain.Followed;

public interface FollowedRepository extends CrudRepository<Followed, Long> {

	List<Followed> findAllByUser(String user);

}
