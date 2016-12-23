package ua.lviv.calltech.service;

import ua.lviv.calltech.entity.User;

public interface UserService {
	
	User findByEmail(String email);

}
