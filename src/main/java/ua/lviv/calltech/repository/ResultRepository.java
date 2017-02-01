package ua.lviv.calltech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.lviv.calltech.entity.Result;
import ua.lviv.calltech.entity.SingleResult;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer>{

	@Query("SELECT r FROM Result r JOIN r.client c JOIN r.project p WHERE p.id = :id AND"
			+ " (c.phone LIKE %:phone% OR c.companyName LIKE %:comp%)")
	List<Result> findByPhoneAndCompany(@Param("id")int projectId, @Param("phone")String phone, @Param("comp")String company);
	
}