package ua.lviv.calltech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.lviv.calltech.entity.Questionnaire;

@Repository
public interface QuestionnaireRepository  extends JpaRepository<Questionnaire, Integer>{

	@Query("SELECT q from Questionnaire q LEFT JOIN FETCH q.questions quest WHERE q.id = :id")
	Questionnaire findByIdWithQuestions(@Param("id")int id);

}
