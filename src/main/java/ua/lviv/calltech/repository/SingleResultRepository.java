package ua.lviv.calltech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ua.lviv.calltech.entity.SingleResult;

@Repository
public interface SingleResultRepository extends JpaRepository<SingleResult, Integer>{

	@Query("FROM SingleResult sr JOIN sr.question q JOIN sr.totalResult r WHERE r.id = ?1 AND q.id = ?2")
	SingleResult findByResultIdAndQuestionId(int resultId, int questionId);

}
