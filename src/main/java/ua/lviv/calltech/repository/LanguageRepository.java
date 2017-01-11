package ua.lviv.calltech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ua.lviv.calltech.entity.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer>{

	@Query("FROM Language l JOIN l.projects p WHERE p.id = ?1")
	Language projectsLanguage(int projectId);

	@Query("SELECT l FROM Language l JOIN l.users u WHERE u.id = ?1")
	List<Language> findAllByUserId(int userId);

}
