package ua.lviv.calltech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.lviv.calltech.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{

}
