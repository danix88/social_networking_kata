package com.socialnetworkingkata.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.socialnetworkingkata.demo.domain.Message;

public interface MessageRepository extends CrudRepository<Message, Long> {

	List<Message> findAllByUserOrderByAddedDesc(String user);

	List<Message> findAllByUserInOrderByAddedDesc(List<String> users);

}
