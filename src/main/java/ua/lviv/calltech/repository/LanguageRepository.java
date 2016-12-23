package ua.lviv.calltech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.lviv.calltech.entity.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer>{

}
