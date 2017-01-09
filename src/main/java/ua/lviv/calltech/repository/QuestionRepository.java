package ua.lviv.calltech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ua.lviv.calltech.DTO.QuestionDTO;
import ua.lviv.calltech.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{

	@Query("FROM Question q JOIN q.questionnaire quest WHERE quest.id = ?1")
	List<Question> findByQuestionnaireId(int id);

	@Query("SELECT new ua.lviv.calltech.DTO.QuestionDTO(q.id, q.text) FROM Question q JOIN q.questionnaire quest WHERE quest.id = ?1 AND q.isVisible = ?2")
	List<QuestionDTO> findDTOWithVisible(int id, boolean isVisible);

}
