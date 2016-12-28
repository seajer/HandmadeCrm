package ua.lviv.calltech.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.calltech.entity.Role;
import ua.lviv.calltech.repository.RoleRepository;
import ua.lviv.calltech.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRep;
	
	public List<Role> findAll() {
		return roleRep.findAll();
	}

	public Role findOne(int roleId) {
		return roleRep.findOne(roleId);
	}

}
