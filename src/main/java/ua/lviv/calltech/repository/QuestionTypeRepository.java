package ua.lviv.calltech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ua.lviv.calltech.entity.QuestionType;

@Repository
public interface QuestionTypeRepository extends JpaRepository<QuestionType, Integer>{

	@Query("FROM QuestionType qt JOIN qt.questions q WHERE q.id = ?1")
	QuestionType findByQuestionId(int questionId);

}
