package ua.lviv.calltech.service.implementation;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.lviv.calltech.entity.User;
import ua.lviv.calltech.repository.UserRepository;
import ua.lviv.calltech.service.UserService;

@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	public User findByEmail(String email) {
		return userRepository.findByEmail();
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

}
