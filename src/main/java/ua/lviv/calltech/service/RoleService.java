package ua.lviv.calltech.service;

import java.util.List;

import ua.lviv.calltech.entity.Role;

public interface RoleService {

	List<Role> findAll();

	Role findOne(int roleId);
}
