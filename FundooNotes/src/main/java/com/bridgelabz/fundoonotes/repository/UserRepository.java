package com.bridgelabz.fundoonotes.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>
{
	public User findByEmailId(String emailId);
}
