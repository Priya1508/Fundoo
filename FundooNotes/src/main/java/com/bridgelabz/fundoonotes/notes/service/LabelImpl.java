package com.bridgelabz.fundoonotes.notes.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.label.dto.LabelDto;
import com.bridgelabz.fundoonotes.label.model.NoteLabel;
import com.bridgelabz.fundoonotes.label.repository.NoteLabelRepository;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.utility.Jwt;

@Service
@PropertySource("classpath:message.properties")
public class LabelImpl implements LabelService
{	
	@Autowired
	private NoteLabelRepository noteLabelRepo;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private Jwt jwt;
	
	@Autowired
	private UserRepository userRepo;
	
	ModelMapper mapper = new ModelMapper();

	/**
	 *Purpose : To create a note label
	 *@return : Given the response if the not label is created successfully or not
	 */
	@Override
	public Response createLabel(LabelDto labelDto, String token)
	{
		String email = jwt.getUserToken(token);
		User user = userRepo.findByEmailId(email);
		if(user != null)
		{
			NoteLabel label = mapper.map(labelDto, NoteLabel.class);
			noteLabelRepo.save(label);
			return new Response(200, environment.getProperty("NOTE_LABEL_CREATE"), HttpStatus.OK);
		}
		return new Response(400, environment.getProperty("INVALID_CREDENTIALS"), HttpStatus.BAD_REQUEST);
	}

	/**
	 *Purpose : To read the details of the note label
	 *@return : Given the response if the note label is successfully read or not
	 */
	@Override
	public Response readLabel(String labelId) 
	{
		jwt.checkByLabelId(labelId);
		NoteLabel label = noteLabelRepo.findById(labelId).get();
		if(label != null)
		{
			return new Response(200, environment.getProperty("NOTE_LABEL_READ"), label);
		}
		return new Response(400, environment.getProperty("INVALID_CREDENTIALS"), HttpStatus.BAD_REQUEST);
	}

	/**
	 *Purpose : To update the label note
	 *@return : Given the response if the note label is successfully updated or not
	 */
	@Override
	public Response updateLabel(LabelDto labelDto, String labelId, String token) 
	{
		jwt.checkByLabelId(labelId);
		String email = jwt.getUserToken(token);
		User user = userRepo.findByEmailId(email);
		if(user != null)
		{
			NoteLabel label = noteLabelRepo.findById(labelId).get();
			label.setLabelName(labelDto.getLabelName());
			noteLabelRepo.save(label);
			return new Response(200, environment.getProperty("NOTE_LABEL_UPDATE"), HttpStatus.OK);
		}
		return new Response(400, environment.getProperty("INVALID_CREDENTIALS"), HttpStatus.BAD_REQUEST);
	}

	/**
	 *Purpose : To delete the note label
	 *@return : Given the response if the note label is successfully deleted or not
	 */
	@Override
	public Response deleteLabel(String labelId, String token) 
	{
		jwt.checkByLabelId(labelId);
		String email = jwt.getUserToken(token);
		User user = userRepo.findByEmailId(email);
		if(user != null)
		{
			NoteLabel label = noteLabelRepo.findById(labelId).get();
			noteLabelRepo.delete(label);
			return new Response(200, environment.getProperty("NOTE_LABEL_DELETE"), HttpStatus.OK);
		}
		return new Response(400, environment.getProperty("INVALID_CREDENTIALS"), HttpStatus.BAD_REQUEST);
	}
	
	/**
	 *Purpose : To all the list of labels
	 *@return : Returns the list of labels
	 */
	public List<NoteLabel> getAll()
	{
		List<NoteLabel> label = noteLabelRepo.findAll();
		if(label != null)
		{
			System.out.println(label);
			return label;
		}
		return null;
	}
}
