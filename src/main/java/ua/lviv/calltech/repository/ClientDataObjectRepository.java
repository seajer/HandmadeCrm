package ua.lviv.calltech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.lviv.calltech.DTO.SimpleClientObjectDTO;
import ua.lviv.calltech.entity.ClientDataObject;

@Repository
public interface ClientDataObjectRepository extends JpaRepository<ClientDataObject, Integer>{

	@Query("SELECT c FROM ClientDataObject c LEFT JOIN FETCH c.results r WHERE c.id = ?1")
	ClientDataObject findOneWithResults(int clientId);

	@Query("SELECT c FROM ClientDataObject c WHERE c.phone = ?1 AND c.companyName = ?2")
	ClientDataObject findByPhoneAndCompany(String phone, String company);

//	@Query("SELECT new ua.lviv.calltech.DTO.SimpleClientObjectDTO(c.id, r.id, c.phone, c.name, c.surname, s.name )"
//			+ " FROM ClientDataObject c LEFT JOIN c.result r LEFT JOIN c.status s LEFT JOIN c.project p WHERE p.id = ?1")
//	List<SimpleClientObjectDTO> findSimpleClientsByProjectId(int projectId);

}
