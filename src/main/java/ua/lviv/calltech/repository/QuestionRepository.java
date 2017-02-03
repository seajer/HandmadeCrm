package ua.lviv.calltech.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ua.lviv.calltech.DTO.QuestionDTO;
import ua.lviv.calltech.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{

	@Query("SELECT q FROM Question q JOIN q.questionnaire quest JOIN FETCH q.type t WHERE quest.id = ?1")
	List<Question> findByQuestionnaireId(int id);

	@Query("SELECT new ua.lviv.calltech.DTO.QuestionDTO(q.id, q.text) FROM Question q JOIN q.questionnaire quest WHERE quest.id = ?1 AND q.isVisible = ?2")
	List<QuestionDTO> findDTOWithVisible(int id, boolean isVisible);

	@Query("SELECT q FROM Question q JOIN FETCH q.answers a WHERE q.id = ?1")
	Question findOneWithAnswers(int questionId);

	@Query("SELECT q FROM Question q JOIN FETCH q.answers a JOIN FETCH q.type t JOIN q.questionnaire seq JOIN seq.project p WHERE p.id = ?1 AND q.isVisible = true")
	Set<Question> findAllByProjectId(int projectId);

	@Query("SELECT q.id FROM Question q JOIN q.answers a JOIN a.result r WHERE r.id =?1 ")
	Set<Integer> findAnsweredByResultId(int resultId);
	
	@Query("SELECT q FROM Question q WHERE q.table.id = ?1")
	List<Question> findAllQuestionTable(int tableId);
	
}
