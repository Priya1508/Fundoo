package com.bridgelabz.fundoonotes.notes.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.response.Response;
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
	public Response createNote(NoteDto noteDto, String token)
	{
		String email = jwt.getUserToken(token);
		User user = userRepository.findByEmailId(email);
		if (user != null)
		{
			ModelMapper mapper = new ModelMapper();
			Notes note = mapper.map(noteDto, Notes.class);
			note.setLocalDate(LocalDate.now());
			repository.save(note);
			return new Response(200, environment.getProperty("NOTE_CREATED"),HttpStatus.OK);
		}
		return new Response(400, environment.getProperty("INVALID_CREDENTIALS"),HttpStatus.BAD_REQUEST); 
	}

	/**
	 *Purpose : To read the note from the passed token
	 *@return : Given the response if the note is read successfully or not
	 */
	@Override
	public Response readNote(String id)
	{
		jwt.checkById(id);
		Notes note = repository.findById(id).get();
		if (note != null) 
		{
			System.out.println("note " + note);
			return new Response(200, environment.getProperty("NOTE_READ"), note);
		}
		return new Response(400, environment.getProperty("INVALID_CREDENTIALS"), HttpStatus.BAD_REQUEST);
	}

	/**
	 *Purpose : To update the note
	 *@return : Given the response if the note is successfully updated or not
	 */
	@Override
	public Response updateNote(NoteDto noteDto, String token,String id) 
	{
		jwt.checkById(id);
		String email = jwt.getUserToken(token);
		User user = userRepository.findByEmailId(email);
		if(user != null)
		{
			Notes notes = repository.findById(id).get();
			notes.setTitle(noteDto.getTitle());
			notes.setDescription(noteDto.getDescription());
			notes.setLocalDate(LocalDate.now());
			repository.save(notes);
			return new Response(200, environment.getProperty("NOTE_UPDATE"), HttpStatus.OK);
		}
		return new Response(400, environment.getProperty("INVALID_CREDENTIALS"), HttpStatus.BAD_REQUEST);
	}

	/**
	 *Purpose : To delete the note
	 *@return : Given the response if the note is successfully deleted or not
	 */
	@Override
	public Response deleteNote(String id)
	{
		jwt.checkById(id);
		Optional<Notes> note = repository.findById(id);
		if(note.isPresent())
		{
			repository.deleteById(id);
			return new Response(200, environment.getProperty("NOTE_DELETE"), HttpStatus.OK);
		}
		return new Response(400, environment.getProperty("INVALID_CREDENTIALS"), HttpStatus.BAD_REQUEST);
	}

	/**
	 *Purpose : To pin and unpin the note
	 *@return : Given the response if the note is successfully pinned and unpinned
	 */
	@Override
	public Response pinNote(String token, String id)
	{
		jwt.checkById(id);
		String email = jwt.getUserToken(token);
		User user = userRepository.findByEmailId(email);
		if(user != null)
		{
			Optional<Notes> note = repository.findById(id);
			if(note.isPresent())
			{
				note.get().setPin(!note.get().isPin());
				repository.save(note.get());
				return new Response(200, "NOTE_PIN", HttpStatus.OK);
			}
			return new Response(400, environment.getProperty("NOTE_UNPIN"), HttpStatus.BAD_REQUEST);
		}
		return new Response(400, environment.getProperty("INVALID_CREDENTIALS"), HttpStatus.BAD_REQUEST);
	}

	/**
	 *Purpose : To archive and unarchive the note
	 *@return : Given the response if the note is successfully archived or not
	 */
	@Override
	public Response archiveNote(String token, String id)
	{
		jwt.checkById(id);
		String email = jwt.getUserToken(token);
		User user = userRepository.findByEmailId(email);
		if(user != null)
		{
			Optional<Notes> note = repository.findById(id);
			if(note.isPresent())
			{
				note.get().setArchive(!note.get().isArchive());
				repository.save(note.get());
				return new Response(200, environment.getProperty("NOTE_ARCHIVE"), HttpStatus.OK);
			}
			return new Response(400, environment.getProperty("NOTE_UNARCHIVE"), HttpStatus.BAD_REQUEST);
		} 
		return new Response(400, environment.getProperty("INVALID_CREDENTIALS"), HttpStatus.BAD_REQUEST);
	}

	/**
	 *Purpose : To trash and untrash the note
	 *@return : Given the response if the note is successfully trashed or not
	 */
	@Override
	public Response trashNote(String token, String id) 
	{
		jwt.checkById(id);
		String email = jwt.getUserToken(token);
		User user = userRepository.findByEmailId(email);
		if(user != null)
		{
			Optional<Notes> note = repository.findById(id);
			if(note.isPresent())
			{
				note.get().setTrash(!note.get().isTrash());
				repository.save(note.get());
				return new Response(200, environment.getProperty("NOTE_TRASH"), HttpStatus.OK);
			}
			return new Response(400, environment.getProperty("NOTE_UNTRASH"), HttpStatus.BAD_REQUEST);
		}
		return new Response(400, environment.getProperty("INVALID_CREDENTIALS"), HttpStatus.BAD_REQUEST);
	}
	
	/**
	 *Purpose : To get the list of all notes
	 *@return : Returns the list of notes
	 */
	public List<Notes> getAll()
	{
		List<Notes> note = repository.findAll();
		if(note != null)
		{
			System.out.println(note);
			return note;
		}
		return null;
	}
	
	/**
	 *Purpose : To sort the list of notes by title
	 *@return : Returns the sorted list of notes
	 */
	public List<Notes> sortByName()
	{
		List<Notes> note = repository.findAll();
		List<Notes> sortedNote = new ArrayList<>();
		sortedNote = note.stream().sorted((list1,list2)->list1.getTitle().compareToIgnoreCase(list2.getTitle())).collect(Collectors.toList());

		return sortedNote;
	}
	
	/**
	 *Purpose : To sort the list of notes by date
	 *@return : Returns the sorted list of notes
	 */
	public List<Notes> sortByDate()
	{
		List<Notes> note = repository.findAll();
		List<Notes> sortedNote = new ArrayList<>();
		sortedNote = note.stream().sorted((list1, list2)->list1.getLocalDate().compareTo(list2.getLocalDate())).collect(Collectors.toList());
		return sortedNote;
	}

	/**
	 *Purpose : To add the remainder for the specific note
	 *@return : Given the response if the remainder is successfully added or not
	 */
	@Override
	public Response addRemainder(String noteId, String token, String date) 
	{
		String email = jwt.getUserToken(token);
		User user = userRepository.findByEmailId(email);
		if(user != null)
		{
			Notes note = repository.findById(noteId).get();
			note.setRemainder(date);
			repository.save(note);
			return new Response(200, environment.getProperty("ADD_REMAINDER"), HttpStatus.OK);
		}	
		return new Response(400, environment.getProperty("INVALID_CREDENTIALS"), HttpStatus.BAD_REQUEST);
	}

	/**
	 *Purpose : To edit the remainder for the specific note
	 *@return : Given the response if the remainder is edited successfully or not
	 */
	@Override
	public Response editRemainder(String noteId, String token, String date) 
	{
		String email = jwt.getUserToken(token);
		User user = userRepository.findByEmailId(email);
		if(user != null)
		{
			Notes note = repository.findById(noteId).get();
			note.setRemainder(date);
			repository.save(note);
			return new Response(200, environment.getProperty("EDIT_REMAINDER"), HttpStatus.OK);
		}
		return new Response(400, environment.getProperty("INVALID_CREDENTIALS"), HttpStatus.BAD_REQUEST);
	}

	/**
	 *Purpose : To delete the remainder for the specific note
	 *@return : Given the response if the remainder is deleted successfully or not
	 */
	@Override
	public Response deleteRemainder(String noteId, String date) 
	{
		Notes note = repository.findById(noteId).get();
		if(note != null)
		{
			note.setRemainder(date);
			repository.save(note);
			return new Response(200, environment.getProperty("DELETE_REMAINDER"), HttpStatus.OK);
		}
		return new Response(400, environment.getProperty("INVALID_CREDENTIALS"), HttpStatus.BAD_REQUEST);
	}
}
