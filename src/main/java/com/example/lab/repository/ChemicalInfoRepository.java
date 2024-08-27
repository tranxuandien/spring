package com.example.lab.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.lab.dto.ChemicalInfoDto;
import com.example.lab.model.ChemicalInfo;

@Repository
public interface ChemicalInfoRepository extends JpaRepository<ChemicalInfo,Long > {

	@Query("SELECT new com.example.lab.dto.ChemicalInfoDto(t1.id,t1.name,t4.name,t1.code,t1.chemicalType,t2.quantity,t1.chemicalTypeInfo,t1.description,"
			+ "t1.expiredDate,t1.type1,t1.type2,t1.chemicalShpt,t1.otherInfo,t3.name,t5.positionInfo,t2.type,null,t1.chemicalStatus,t1.purchaseSrc,t1.createAt,t1.updateAt,t6.quantity)  FROM ChemicalInfo t1 \r\n"
			+ "inner join ChemicalImpExp t2 on t2.chemicalId = t1.id \r\n"
			+ "inner join UserInfo t3 on t2.impUser = t3.user.id "
			+ "left join Brand t4 on t1.brand.id=t4.id "
			+ "left join PositionInfo t5 on t1.position.id = t5.id "
			+ "left join ChemicalInventory t6 on t6.chemicalId = t1.id"
			+ " where "
			+ "(?1 IS NULL OR t1.code like %?1%) "
			+ "and (?2 IS NULL OR t1.chemicalType=?2) "
			+ "and (?4 IS NULL OR t6.quantity > ?4) "
			+ "and (?5 IS NULL OR t6.quantity <= ?5) "
			+ "and (?3 IS NULL OR t1.name like %?3%) AND t1.isDelete ='0' ")
	List<ChemicalInfoDto> findAll(String code, String chemicalType, String name,BigDecimal range1,BigDecimal range2);
	
	@Query("SELECT t1  FROM ChemicalInfo t1 \r\n"
			+ "left join ChemicalInventory t2 on t2.chemicalId = t1.id \r\n"
			+ "where "
			+ "(?1 IS NULL OR t1.code=?1)"
			+ "AND t2.quantity > 0 AND t1.isDelete ='0' ORDER BY t1.id DESC LIMIT 1")
	ChemicalInfo findByCode(String code);

	@Query("SELECT t1  FROM ChemicalInfo t1 \r\n"
			+ "where "
			+ "(?1 IS NULL OR t1.code=?1)"
			+ "AND t1.isDelete ='0' ORDER BY t1.id DESC LIMIT 1")
	ChemicalInfo findByCodeWithoutInventory(String code);

}
