package ua.lviv.calltech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ua.lviv.calltech.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{
	
	@Query("SELECT p FROM Project p LEFT JOIN FETCH p.type t LEFT JOIN FETCH p.language l")
	List<Project> findAllWithLanguageAndType();
	
}
