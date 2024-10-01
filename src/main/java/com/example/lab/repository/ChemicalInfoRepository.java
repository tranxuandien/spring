package com.example.lab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.lab.dto.masterdata.ChemicalMasterDataDto;
import com.example.lab.dto.response.ChemicalInfoResponseDto;
import com.example.lab.model.ChemicalInfo;

@Repository
public interface ChemicalInfoRepository extends JpaRepository<ChemicalInfo,Long > {

//	@Query("SELECT new com.example.lab.dto.ChemicalInfoDto(t1.id,t1.name,t4.name,t1.chemicalType,t1.chemicalTypeInfo,t1.manufactoryQuantity,"
//			+ "t1.expiredDate,t1.chemicalClass,t1.chemicalClassInfo,t1.otherInfo,t3.lastName,t5.positionInfo,t2.type,null,t1.chemicalStatus,t1.purchaseSrc,t1.createAt,t1.updateAt,t6.quantity)  FROM ChemicalInfo t1 \r\n"
//			+ "inner join ChemicalImpExp t2 on t2.chemicalId = t1.id \r\n"
//			+ "inner join UserInfo t3 on t2.impUser = t3.user.id "
//			+ "left join Brand t4 on t1.brand.id=t4.id "
//			+ "left join PositionInfo t5 on t1.position.id = t5.id "
//			+ "left join ChemicalInventory t6 on t6.chemicalId = t1.id"
//			+ " where "
//			+ "(?1 IS NULL OR t1.id = ?1) "
//			+ "and (?2 IS NULL OR t1.chemicalType=?2) "
//			+ "and (?4 IS NULL OR t6.quantity > ?4) "
//			+ "and (?5 IS NULL OR t6.quantity <= ?5) "
//			+ "and (?3 IS NULL OR t1.name like %?3%) "
//			
//			+ "and (?6 IS NULL OR t4.id = ?6) "
//			+ "and (?7 IS NULL OR t1.chemicalClass like %?7%) "
//			+ "and (?8 IS NULL OR t5.id = ?8) "
//			+ "and t1.isDelete ='0' ")
//	List<ChemicalInfoRequestDto> findAll(Long id, String chemicalType, String name,BigDecimal range1,BigDecimal range2, String brand, String chemicalClass, String position);
	
	@Query("SELECT t1  FROM ChemicalInfo t1 \r\n"
			+ "left join ChemicalInventory t2 on t2.chemicalId = t1.id \r\n"
			+ "where "
			+ "(?1 IS NULL OR t1.id=?1)"
			+ "AND t2.quantity > 0 AND t1.isDelete ='0' ORDER BY t1.id DESC LIMIT 1")
	ChemicalInfo getById(Long id);

	@Query("SELECT t1  FROM ChemicalInfo t1 \r\n"
			+ "where "
			+ "(?1 IS NULL OR t1.id=?1)"
			+ "AND t1.isDelete ='0' ORDER BY t1.id DESC LIMIT 1")
	ChemicalInfo findByCodeWithoutInventory(String id);

	@Query("Select new com.example.lab.dto.masterdata.ChemicalMasterDataDto(t1.id,t1.name) from ChemicalInfo t1 where t1.isDelete ='0' ")
	List<ChemicalMasterDataDto> getLstMaster();
	
	
	@Query("SELECT new com.example.lab.dto.response.ChemicalInfoResponseDto(t1,t2,t3)  FROM ChemicalInfo t1 "
			+ "INNER JOIN UserInfo t2 on t2.user.id = t1.registerUser.id "
			+ "LEFT JOIN Brand t3 on t1.brandId=t3.id "
			+ " WHERE "
			+ "(?1 IS NULL OR t1.name LIKE %?1%) "
			+ "AND (?2 IS NULL OR t1.chemicalType=?2) "
			+ "AND (?3 IS NULL OR t3.id = ?3) "
			+ "AND (?4 IS NULL OR t1.chemicalClass LIKE %?4%) "
			+ "AND t1.isDelete ='0' ")
	List<ChemicalInfoResponseDto> findAll( String name, String chemicalType, Long brand, String chemicalClass);
	
	@Query("SELECT t1  FROM ChemicalInfo t1 "
			+ "INNER JOIN UserInfo t2 on t2.id = t1.registerUser.id "
			+ "LEFT JOIN Brand t3 on t1.brandId=t3.id "
			+ " WHERE "
			+ "(?1 IS NULL OR t1.name = ?1) "
			+ "AND (?2 IS NULL OR t1.chemicalType=?2) "
			+ "AND (?3 IS NULL OR t3.id = ?3) "
			+ "AND (?4 IS NULL OR t1.chemicalClass = ?4) "
//			+ "AND (?5 IS NULL OR t1.chemicalClassInfo = ?5) "
			+ "AND t1.isDelete ='0' ")
	List<ChemicalInfo> findExist( String name, String chemicalType, Long brand, String chemicalClass);
}
