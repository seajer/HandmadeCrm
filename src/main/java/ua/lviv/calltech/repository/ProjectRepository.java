package ua.lviv.calltech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.lviv.calltech.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{

}
