package ua.lviv.calltech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.lviv.calltech.entity.Result;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer>{
	
	@Query("SELECT r.id FROM Result r JOIN r.project p JOIN r.user u WHERE p.id = ?1 AND r.client IS null AND u.id = ?2")
	Integer finqClearOneByProjectId(int projectId, int userId);

	@Query("SELECT r FROM Result r LEFT JOIN FETCH r.answers a WHERE r.id = :id")
	Result findOneWithAnswers(@Param("id")int resultId);

	@Query("FROM Result r JOIN FETCH r.client c WHERE r.id = ?1")
	Result findOneWithClient(int resultId);

	@Query("FROM Result r LEFT JOIN FETCH r.answers a JOIN FETCH r.project p WHERE r.id = ?1")
	Result findOneWithAnswersAndProject(int resultId);
	
}