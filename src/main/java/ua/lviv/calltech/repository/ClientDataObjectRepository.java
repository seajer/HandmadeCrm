package ua.lviv.calltech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.lviv.calltech.entity.ClientDataObject;

@Repository
public interface ClientDataObjectRepository extends JpaRepository<ClientDataObject, Integer>{

}