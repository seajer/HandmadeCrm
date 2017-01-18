package ua.lviv.calltech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ua.lviv.calltech.DTO.SimpleClientObjectDTO;
import ua.lviv.calltech.entity.ClientDataObject;

@Repository
public interface ClientDataObjectRepository extends JpaRepository<ClientDataObject, Integer>{

	@Query("SELECT new ua.lviv.calltech.DTO.SimpleClientObjectDTO(c.id, r.id, c.phone, c.name, c.surname, s.name )"
			+ " FROM ClientDataObject c LEFT JOIN c.result r LEFT JOIN c.status s LEFT JOIN c.project p WHERE p.id = ?1")
	List<SimpleClientObjectDTO> findSimpleClientsByProjectId(int projectId);

	//LEFT JOIN FETCH c.status s 
	@Query("FROM ClientDataObject c WHERE c.id = ?1")
	ClientDataObject findOneWithStatus(int clientId);

	@Query("SELECT c FROM ClientDataObject c LEFT JOIN FETCH c.status JOIN FETCH c.project WHERE c.id = ?1")
	ClientDataObject findOneWithStatusAndProject(int clientId);
	
}
