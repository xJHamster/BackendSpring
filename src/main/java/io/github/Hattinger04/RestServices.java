package io.github.Hattinger04;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.Hattinger04.hamster.model.Hamster;
import io.github.Hattinger04.user.model.User;

@Service
public class RestServices {

	private ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * Convert JSON-String to User object
	 * 
	 * @param json
	 * @return
	 */
	public User deserializeUser(String json) {
		try {
			return objectMapper.readValue(json, User.class);
		} catch (JsonProcessingException e) {
			return null;
		}
	}
	
	/**
	 * Convert JSON-String to Hamster object
	 * 
	 * @param json
	 * @return
	 */
	public Hamster deserializeHamster(String json) {
		try {
			return objectMapper.readValue(json, Hamster.class);
		} catch (JsonProcessingException e) {
			return null;
		}
	}
	
	/**
	 * Convert JSON-String to UserRole object
	 * 
	 * @param json
	 * @return
	 */
	public UserRole deserializeUserRole(String json) {
		try {
			return objectMapper.readValue(json, UserRole.class);
		} catch (JsonProcessingException e) {
			return null;
		}
	}

}
