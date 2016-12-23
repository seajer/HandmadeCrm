package ua.lviv.calltech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.lviv.calltech.entity.ProjectType;

@Repository
public interface ProjectTypeRepository  extends JpaRepository<ProjectType, Integer>{

}
