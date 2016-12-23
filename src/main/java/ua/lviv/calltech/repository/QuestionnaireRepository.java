package ua.lviv.calltech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.lviv.calltech.entity.Questionnaire;

@Repository
public interface QuestionnaireRepository  extends JpaRepository<Questionnaire, Integer>{

}
