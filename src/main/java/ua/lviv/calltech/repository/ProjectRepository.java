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

	@Query("SELECT p FROM Project p LEFT JOIN FETCH p.type t WHERE p.id = ?1")
	Project findOneWithType(int projectId);

	@Query("SELECT p.id FROM Project p JOIN p.results r WHERE ?1 IN (r.id)")
	Integer findIdByResultId(int resultId);

	@Query("SELECT p FROM Project p WHERE p.title = ?1 AND p.companyName = ?2")
	Project findByNameAndCompany(String name, String company);

}
