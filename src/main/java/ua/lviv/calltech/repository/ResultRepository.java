package ua.lviv.calltech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.lviv.calltech.entity.SingleResult;

@Repository
public interface ResultRepository extends JpaRepository<SingleResult, Integer>{
	
	
}