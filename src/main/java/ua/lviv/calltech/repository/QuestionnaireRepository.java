package ua.lviv.calltech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ua.lviv.calltech.DTO.QuestionnaireDTO;
import ua.lviv.calltech.entity.Questionnaire;

@Repository
public interface QuestionnaireRepository  extends JpaRepository<Questionnaire, Integer>{

	@Query("FROM Questionnaire q JOIN FETCH q.questions sub WHERE q.id = ?1")
	Questionnaire findByIdWithQuestions(int questionnaireUd);

	@Query("SELECT new ua.lviv.calltech.DTO.QuestionnaireDTO(q.id, q.description) FROM Questionnaire q WHERE q.id = ?1")
	QuestionnaireDTO findDtoByIdAndVisible(int id);

	@Query("SELECT q.id FROM Questionnaire q JOIN q.questions quest WHERE quest.id = ?1")
	int fibdIbByQuestionId(int questionId);

	@Query("SELECT new ua.lviv.calltech.DTO.QuestionnaireDTO(q.id, q.description) FROM Questionnaire q JOIN q.project p WHERE p.id = ?1")
	QuestionnaireDTO findDtoByProjectId(int projectId);

	//testing query
	@Query("SELECT q.id FROM Questionnaire q JOIN q.project p WHERE p.id = ?1")
	int findQuestionnaireIdByProjectId(int projectId);

	@Query("SELECT q.id FROM Questionnaire q JOIN q.project p WHERE q.description = ?1 AND p.id = ?2")
	Integer findByDescriptionAndProjectId(String description, int projectId);

}
