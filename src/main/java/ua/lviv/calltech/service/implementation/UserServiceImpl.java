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
					user.getPassword(), true, true, true, true, authority);
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

}
