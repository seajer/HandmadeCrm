package ua.lviv.calltech.service.implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.lviv.calltech.DTO.UserDTO;
import ua.lviv.calltech.entity.Language;
import ua.lviv.calltech.entity.Role;
import ua.lviv.calltech.entity.User;
import ua.lviv.calltech.repository.LanguageRepository;
import ua.lviv.calltech.repository.UserRepository;
import ua.lviv.calltech.service.RoleService;
import ua.lviv.calltech.service.UserService;

@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private LanguageRepository languageRepository;

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = null;
		try {
			user = findByEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		Collection<SimpleGrantedAuthority> authority = new ArrayList<SimpleGrantedAuthority>();
		authority.add(new SimpleGrantedAuthority(user.getRole().getName()));
			return new org.springframework.security.core.userdetails.User(String.valueOf(user.getId()),
					user.getPassword(), authority);
	}

	@Transactional
	public void addUser(String name, String email, String phone, String password, int roleId, int langId) {
		User user = new User(name, email, phone);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(password));
		Role role = roleService.findOne(roleId);
		Language lang = languageRepository.findOne(langId);
		List<Language> langs = new ArrayList<Language>();
		langs.add(lang);
		user.setRole(role);
		user.setLanguage(langs);
		userRepository.save(user);
	}

	@Transactional
	public void deleteUser(int id) {
		User user = userRepository.findOne(id);
		if(user != null) {
			userRepository.delete(id);
		}
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(int userId) {
		return userRepository.findOne(userId);
	}

	public boolean validatePassword(String oldPass, int userId) {
		User user = userRepository.findOne(userId);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if(encoder.matches(oldPass, user.getPassword()))return true;
		return false;
	}

	public boolean passPresenting(String oldPass) {
		return oldPass.trim().length() != 0;
	}
	@Transactional
	public void saveWithoutPass(int userId, String name, String email, String phone, int[] langId) {
		User u = userRepository.findOne(userId);
		if(u != null){
			fillUserWithParams(u, name, email, phone, langId);
			userRepository.save(u);
		}
	}

	@Transactional
	public void saveWithPass(int userId, String name, String email, String phone, String newPass, int[] langId) {
		User u = userRepository.findOne(userId);
		if(u != null){
			fillUserWithParams(u, name, email, phone, langId);
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			u.setPassword(encoder.encode(newPass));
			userRepository.save(u);
		}
	}

	public List<UserDTO> findAllUsersDtoOnProject(int projectId) {
		List<UserDTO> users = userRepository.findAllDtoFromProject(projectId);
		return users;
	}

	public List<UserDTO> findAllUsersDtoOutOfProject(List<UserDTO> usersOn, int languageId) {
		List<UserDTO> usersOut = userRepository.findAllDtoWithLanguage(languageId);
		usersOut.removeAll(usersOn);
		return usersOut;
	}
	
	private User fillUserWithParams(User user, String name, String email, String phone, int[] langId){
		user.setFullName(name);
		user.setEmail(email);
		user.setPhone(phone);
		List<Language> langs = new ArrayList<Language>();
		for (int i : langId) {
			Language lang = languageRepository.findOne(i);
			langs.add(lang);
		}
		user.setLanguage(langs);
		return user;
	}

}
