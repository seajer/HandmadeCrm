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

import ua.lviv.calltech.entity.Role;
import ua.lviv.calltech.entity.User;
import ua.lviv.calltech.repository.UserRepository;
import ua.lviv.calltech.service.RoleService;
import ua.lviv.calltech.service.UserService;

@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleService roleService;

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
	public void addUser(String name, String email, String phone, String password, int roleId) {
		User user = new User(name, email, phone);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(password));
		Role role = roleService.findOne(roleId);
		user.setRole(role);
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
	public void saveWithoutPass(int userId, String name, String email, String phone) {
		User u = userRepository.findOne(userId);
		if(u != null){
			u.setFullName(name);
			u.setEmail(email);
			u.setPhone(phone);
			userRepository.save(u);
		}
	}

	@Transactional
	public void saveWithPass(int userId, String name, String email, String phone, String newPass) {
		User u = userRepository.findOne(userId);
		if(u != null){
			u.setFullName(name);
			u.setEmail(email);
			u.setPhone(phone);
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			u.setPassword(encoder.encode(newPass));
			userRepository.save(u);
		}
	}

}
