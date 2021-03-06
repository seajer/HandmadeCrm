package ua.lviv.calltech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.lviv.calltech.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
