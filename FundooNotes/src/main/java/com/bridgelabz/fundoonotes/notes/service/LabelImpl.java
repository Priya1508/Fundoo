package com.bridgelabz.fundoonotes.notes.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.label.dto.LabelDto;
import com.bridgelabz.fundoonotes.label.model.NoteLabel;
import com.bridgelabz.fundoonotes.label.model.UserLabel;
import com.bridgelabz.fundoonotes.label.repository.NoteLabelRepository;
import com.bridgelabz.fundoonotes.label.repository.UserLabelRepository;
import com.bridgelabz.fundoonotes.label.response.LabelResponse;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.notes.dto.NoteDto;
import com.bridgelabz.fundoonotes.notes.model.Notes;
import com.bridgelabz.fundoonotes.notes.repository.NotesRepository;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.utility.Jwt;

@Service
@PropertySource("classpath:message.properties")
public class LabelImpl implements LabelService
{
	@Autowired
	private Jwt jwt;
	
	@Autowired
	private UserLabelRepository userLabelRepo;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private NotesRepository noteRepository;
	
	@Autowired
	private NoteLabelRepository noteLabelRepo;
	
	@Autowired
	private Environment environment;

	@Override
	public LabelResponse createLabel(LabelDto labelDto, String token)
	{
		String email = jwt.getUserToken(token);
		User user = userRepository.findByEmailId(email);
		if(user != null)
		{
			ModelMapper mapper = new ModelMapper();
			UserLabel userLabel = mapper.map(labelDto, UserLabel.class);
			userLabelRepo.save(userLabel);
			return new LabelResponse(200, environment.getProperty("LABEL_CREATED"), HttpStatus.OK);
		}
		return new LabelResponse(400, environment.getProperty("INVALID_CREDENTIALS"), HttpStatus.BAD_REQUEST);
	}

	@Override
	public LabelResponse readLabel(String id) 
	{
		UserLabel user = userLabelRepo.findById(id).get();
		if(user != null)
		{
			return new LabelResponse(200, environment.getProperty("LABEL_READ"), user);
		}
		return new LabelResponse(400, environment.getProperty("INVALID_CREDENTIALS"), HttpStatus.BAD_REQUEST);
	}

	@Override
	public LabelResponse updateLabel(LabelDto labelDto, String id)
	{
		UserLabel user = userLabelRepo.findById(id).get();
		ModelMapper mapper = new ModelMapper();
	    user = mapper.map(labelDto, UserLabel.class);
		if(user != null)
		{
			userLabelRepo.save(user);
			return new LabelResponse(200, environment.getProperty("LABEL_UPDATE"), HttpStatus.OK);
		}
		return new LabelResponse(400, environment.getProperty("INVALID_CREDENTIALS"), HttpStatus.BAD_REQUEST);
	}

	@Override
	public LabelResponse deleteLabel(LabelDto labelDto, String id)
	{
		UserLabel user = userLabelRepo.findById(id).get();
		ModelMapper mapper = new ModelMapper();
		user = mapper.map(labelDto, UserLabel.class);
		if(user != null)
		{
			userLabelRepo.delete(user);
			return new LabelResponse(200, environment.getProperty("LABEL_DELETE"), HttpStatus.OK);
		}
		return new LabelResponse(400, environment.getProperty("INVALID_CREDENTIALS"), HttpStatus.BAD_REQUEST);
	}

	@Override
	public LabelResponse createNoteLabel(NoteDto noteDto, String noteId)
	{
		Notes note = noteRepository.findById(noteId).get(); 
		if(note != null)
		{
			ModelMapper mapper = new ModelMapper();
			NoteLabel noteLabel = mapper.map(note, NoteLabel.class);
			noteLabelRepo.save(noteLabel);
			return new LabelResponse(200, environment.getProperty("NOTE_LABEL_CREATE"), HttpStatus.OK);
		}
		return new LabelResponse(400, environment.getProperty("INVALID_CREDENTIALS"), HttpStatus.BAD_REQUEST);
	}
}
