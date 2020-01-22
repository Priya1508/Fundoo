package com.bridgelabz.fundoonotes.notes.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.notes.model.Notes;

@Repository
public interface NotesRepository extends MongoRepository<Notes, String>
{
	public Notes findByEmailId(String emailId);
	
	public Optional<Notes> findById(String id);
}