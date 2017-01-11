package ua.lviv.calltech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ua.lviv.calltech.DTO.UserDTO;
import ua.lviv.calltech.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("FROM User u LEFT JOIN FETCH u.role r WHERE u.email like ?1")
	User findByEmail(String email);

	@Query("SELECT new ua.lviv.calltech.DTO.UserDTO(u.id, u.fullName) FROM User u JOIN u.projects p WHERE p.id = ?1")
	List<UserDTO> findAllDtoFromProject(int projectId);
	
}
