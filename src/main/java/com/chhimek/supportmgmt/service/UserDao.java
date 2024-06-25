package com.chhimek.supportmgmt.service;

import com.chhimek.supportmgmt.model.User;

public interface UserDao {
	
	User login(String username, String password);
	void saveUser(User u);
	User showUser(int id);
	User getUserBySupportPersonId(int id);
	void updateUser(User user);
	User getUserBySupportRegPersonId(int id);
	User getUserById(int id);
	void deleteUser(int id);

}
