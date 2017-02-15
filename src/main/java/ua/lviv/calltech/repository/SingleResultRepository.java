package ua.lviv.calltech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ua.lviv.calltech.entity.SingleResult;

@Repository
public interface SingleResultRepository extends JpaRepository<SingleResult, Integer>{

	@Query("FROM SingleResult sr JOIN sr.question q JOIN sr.totalResult r WHERE r.id = ?1 AND q.id = ?2")
	SingleResult findByResultIdAndQuestionId(int resultId, int questionId);

	@Query("SELECT DISTINCT sr FROM SingleResult sr LEFT JOIN FETCH sr.question q INNER JOIN FETCH q.type t LEFT JOIN FETCH sr.answers a JOIN sr.totalResult r WHERE r.id = ?1")
	List<SingleResult> findAllByResultId(int resultId);

}
