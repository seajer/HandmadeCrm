package ua.lviv.calltech.service;

import java.util.List;

import ua.lviv.calltech.entity.User;

public interface UserService {
	
	User findByEmail(String email);

	void addUser(String name, String email, String phone, String password, int roleId);

	void deleteUser(int id);

	List<User> findAll();

	User findById(int userId);

	boolean validatePassword(String oldPass, int userId);

	boolean passPresenting(String oldPass);

	void saveWithoutPass(int userId, String name, String email, String phone);

	void saveWithPass(int userId, String name, String email, String phone, String newPass);

}
