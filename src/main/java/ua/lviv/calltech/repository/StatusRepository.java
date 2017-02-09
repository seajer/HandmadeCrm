package ua.lviv.calltech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ua.lviv.calltech.entity.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer>{

	@Query("FROM Status s WHERE s.name = ?1")
	Status findByName(String string);

	@Query("FROM Status s JOIN s.objects r WHERE r.id = ?1")
	Status findByResultId(int resultId);

}
