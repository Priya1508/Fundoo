package com.bridgelabz.fundoonotes.label.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bridgelabz.fundoonotes.label.model.NoteLabel;

public interface NoteLabelRepository extends MongoRepository<NoteLabel, String>
{
	public Optional<NoteLabel> findById(String noteId);
}
