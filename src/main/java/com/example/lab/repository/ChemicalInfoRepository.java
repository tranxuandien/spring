package com.example.lab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.lab.model.ChemicalInfo;

@Repository
public interface ChemicalInfoRepository extends JpaRepository<ChemicalInfo,Long > {

	@Query("SELECT t1  FROM ChemicalInfo t1 \r\n"
			+ "left join ChemicalImpExp t2 on t2.chemicalId = t1.id \r\n"
			+ "left join UserInfo t3 on t2.impUser = t3.user.id where "
			+ "(?1 IS NULL OR t1.code=?1) "
			+ "and (?2 IS NULL OR t1.chemicalType=?2) "
			+ "and (?3 IS NULL OR t2.type=?3)"
			+ "and (?4 IS NULL OR t3.user.id=?4)")
	List<ChemicalInfo> findAll(String code, String chemicalType, String impExpType, String registerUser);
	
	@Query("SELECT t1  FROM ChemicalInfo t1 \r\n"
			+ "left join ChemicalInventory t2 on t2.chemicalId = t1.id \r\n"
			+ "where "
			+ "(?1 IS NULL OR t1.code=?1)"
			+ "AND t2.quantity > 0 ")
	ChemicalInfo findByCode(String code);

}
