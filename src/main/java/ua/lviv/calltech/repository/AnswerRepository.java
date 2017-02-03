package ua.lviv.calltech.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ua.lviv.calltech.entity.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer>{

	@Query("FROM Answer a JOIN a.question q WHERE q.id = ?1")
	List<Answer> findAllByQuestionId(int questionId);


}
