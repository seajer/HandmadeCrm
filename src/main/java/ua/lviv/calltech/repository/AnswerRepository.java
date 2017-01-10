package ua.lviv.calltech.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.lviv.calltech.entity.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer>{


}
