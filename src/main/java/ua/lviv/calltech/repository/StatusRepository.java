package ua.lviv.calltech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.lviv.calltech.entity.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer>{

}
