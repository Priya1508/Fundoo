package com.bridgelabz.fundoonotes.label.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bridgelabz.fundoonotes.label.model.UserLabel;

public interface UserLabelRepository extends MongoRepository<UserLabel, String>
{
	public Optional<UserLabel> findById(String userId);
}
