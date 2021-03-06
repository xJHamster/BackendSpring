package io.github.Hattinger04.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.Hattinger04.RestServices;
import io.github.Hattinger04.UserRole;
import io.github.Hattinger04.user.model.User;
import io.github.Hattinger04.user.model.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RestServices restServices; 
	
	/**
	 * Get all user from database
	 * 
	 * @return
	 */
	@PreAuthorize("hasAuthority('DEV')")
	@GetMapping("/getAllUsers")
	public ResponseEntity<?> getAllUsers() {
		return new ResponseEntity<>(userService.selectMany(), HttpStatus.OK); 
	}
	
	/**
	 * Creating new user if not exist
	 * Needs as RequestBody user object
	 * 
	 * @param json
	 * @return
	 */
	@PreAuthorize("hasAuthority('DEV')")
	@PostMapping("/createUser")
	@ResponseBody
	public ResponseEntity<?> createUser(@RequestBody String json) {
		User user = restServices.deserializeUser(json);
		if(user == null) {
			return new ResponseEntity<>("Could not save user -> wrong data", HttpStatus.BAD_REQUEST);
		}
		if(!userService.saveUser(user)) {
			return new ResponseEntity<>("Could not save user -> error in database", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	/**
	 * Updating user
	 * Needs as RequestBody user object
	 * 
	 * @param json
	 * @return
	 */
	@PostMapping("/updateUser")
	@PreAuthorize("hasAuthority('DEV')")
	public ResponseEntity<?> updateUser(@RequestBody String json) {
		User user = restServices.deserializeUser(json); 
		if(userService.updateUser(user)) {
			return new ResponseEntity<>("Could not update user!", HttpStatus.NOT_FOUND); 
		}
		return new ResponseEntity<>(HttpStatus.OK); 
	}
	
	/**
	 * Deleting user
	 * Needs as RequestBody id
	 * 
	 * @param json
	 * @return
	 */
	@PostMapping("/deleteUser")
	@PreAuthorize("hasAuthority('DEV')")
	public ResponseEntity<?> deleteUser(@RequestBody String json) {
		UserRole user = restServices.deserializeUserRole(json); 
		if(!userService.deleteUser(user.getUser_id())) {
			return new ResponseEntity<>("Could not delete user!", HttpStatus.NOT_FOUND); 
		}
		return new ResponseEntity<>(HttpStatus.OK); 
	}
	
	/**
	 * 
	 * Adding role to user.
	 * Needs as RequestBody role_id and user_id
	 * 
	 * @param json
	 * @return
	 */
	@PostMapping("/addRole")
	@PreAuthorize("hasAuthority('DEV')")
	public ResponseEntity<?> addRole(@RequestBody String json) {
		UserRole userRole = restServices.deserializeUserRole(json);
		if(!userService.insertUserRole(userRole.getUser_id(), userRole.getRole_id())) {
			return new ResponseEntity<>("Could not insert new Role!", HttpStatus.NOT_FOUND); 
		}
		return new ResponseEntity<>(HttpStatus.OK); 
	}
	/**
	 * 
	 * Removing role from user.
	 * Needs as RequestBody role_id and user_id
	 * 
	 * @param json
	 * @return
	 */
	@PostMapping("/removeRole")
	@PreAuthorize("hasAuthority('DEV')")
	public ResponseEntity<?> removeRole(@RequestBody String json) {
		UserRole userRole = restServices.deserializeUserRole(json);
		if(!userService.removeUserRole(userRole.getUser_id(), userRole.getRole_id())) {
			return new ResponseEntity<>("Could not remove Role!", HttpStatus.NOT_FOUND); 
		}
		return new ResponseEntity<>(HttpStatus.OK); 
	}
	
	/**
	 * 
	 * Get user by username.
	 * Needs as RequestBody username
	 * 
	 * @param json
	 * @return
	 */
	@PostMapping("/getUser")
	@PreAuthorize("hasAuthority('DEV')")
	public ResponseEntity<?> getUser(@RequestBody String json) {
		User user= userService.findUserByUsername(restServices.deserializeUser(json).getUsername());
		if(user == null) {
			return new ResponseEntity<>("Couldn't find user!", HttpStatus.NOT_FOUND); 
		}
		return new ResponseEntity<>(user, HttpStatus.OK); 
	}
	
	// TODO: Delete if not needed anymore
	@GetMapping("/home")
	public ResponseEntity<?>  successHome() {
		return new ResponseEntity<>((SecurityContextHolder.getContext().getAuthentication().getName()), HttpStatus.OK);
	}
	
	@GetMapping("/logout")
	@PreAuthorize("isAuthenticated()") // not really needed
	public ResponseEntity<?> logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
