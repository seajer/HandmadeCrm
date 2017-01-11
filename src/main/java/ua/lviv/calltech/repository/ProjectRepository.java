package ua.lviv.calltech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ua.lviv.calltech.DTO.ProjectDTO;
import ua.lviv.calltech.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{
	
	@Query("FROM Project p LEFT JOIN FETCH p.type t LEFT JOIN FETCH p.language l")
	List<Project> findAllWithLanguageAndType();

	@Query("SELECT new ua.lviv.calltech.DTO.ProjectDTO(p.id, p.companyName, p.title) FROM Project p WHERE p.id = ?1")
	ProjectDTO findDtoById(int projectId);
	
}
