package com.sporty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sporty.dao.UserDAO;
import com.sporty.entity.User;

@Component
public class UserService {

	@Autowired
	private UserDAO userDAO;

	// Method to authenticate a user based on email and password
	@Transactional
	public User authenticate(String userId, String pwd) {
		return userDAO.authenticate(userId, pwd);
	}

	// Method to retrieve a user by ID
	@Transactional
	public User getUserById(long id) {
		return userDAO.getUserById(id);
	}

	// Method to retrieve a user by email ID
	@Transactional
	public User getUserByEmailId(String emailId) {
		return userDAO.getUserByEmailId(emailId);
	}

	// Method to update a user's information
	@Transactional
	public void updateUser(User user) {
		userDAO.updateUser(user);
	}

	// Method to retrieve a list of all users
	@Transactional
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}
}
