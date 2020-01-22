package com.bridgelabz.fundoonotes.notes.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.notes.dto.NoteDto;
import com.bridgelabz.fundoonotes.notes.model.Notes;
import com.bridgelabz.fundoonotes.notes.repository.NotesRepository;
import com.bridgelabz.fundoonotes.notes.response.NotesResponse;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.utility.Jwt;

@Service
@PropertySource("classpath:message.properties")
public class ServiceImpl implements ServiceNote
{
	@Autowired
	private NotesRepository repository;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Jwt jwt;

	/**
	 *Purpose : To create a note
	 *@return : Given the the response if the note is successfully created or not
	 */
	@Override
	public NotesResponse createNote(NoteDto noteDto, String token) 
	{
		String email = jwt.getUserToken(token);
		User user=userRepository.findByEmailId(email);
		if (user != null)
		{
			ModelMapper mapper = new ModelMapper();
			Notes note = mapper.map(noteDto, Notes.class);
			System.out.println("data"+note.toString());
			repository.save(note);
			return new NotesResponse(200, environment.getProperty("NOTE_CREATED"),HttpStatus.OK);
		}
		return new NotesResponse(400, environment.getProperty("INVALID_CREDENTIALS"),HttpStatus.BAD_REQUEST); 
	}

	/**
	 *Purpose : To read the note from the passed token
	 *@return : Given the response if the note is read successfully or not
	 */
	@Override
	public NotesResponse readNote(String token)
	{
		String email=jwt.getUserToken(token);	
		System.out.println("email"+email);
		User user = userRepository.findByEmailId(email);
		System.out.println("data in user "+user);
		if(user != null)
		{
			ModelMapper mapper=new ModelMapper();
			Notes note=mapper.map(token,Notes.class);
			note.getDescription();
			return new NotesResponse(200, environment.getProperty("NOTE_READ"), HttpStatus.OK);
		}
		return new NotesResponse(400, environment.getProperty("INVALID_CREDENTIALS"), HttpStatus.BAD_REQUEST);
	}

	/**
	 *Purpose : To update the note
	 *@return : Given the response if the note is successfully updated or not
	 */
	@Override
	public NotesResponse updateNote(NoteDto noteDto, String token,String id) 
	{
		String email=jwt.getUserToken(token);
		User user=userRepository.findByEmailId(email);
		if(user != null)
		{
			Notes notes = repository.findById(id).get();
			notes.setTitle(noteDto.getTitle());
			notes.setDescription(noteDto.getDescription());
			repository.save(notes);
			return new NotesResponse(200, environment.getProperty("NOTE_UPDATE"), HttpStatus.OK);
		}
		return new NotesResponse(400, environment.getProperty("INVALID_CREDENTIALS"), HttpStatus.BAD_REQUEST);
	}

	/**
	 *Purpose : To delete the note
	 *@return : Given the response if the note is successfully deleted or not
	 */
	@Override
	public NotesResponse deleteNote(String id) 
	{
		Optional<Notes> note = repository.findById(id);
		if(note.isPresent())
		{
			repository.deleteById(id);
			return new NotesResponse(200, environment.getProperty("NOTE_DELETE"), HttpStatus.OK);
		}
		return new NotesResponse(400, environment.getProperty("INVALID_CREDENTIALS"), HttpStatus.BAD_REQUEST);
	}		
}
