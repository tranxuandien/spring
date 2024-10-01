package com.example.lab.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.lab.dto.response.ChemicalInfoResponseDto;
import com.example.lab.dto.response.ChemicalUsingResponseDto;
import com.example.lab.model.ChemicalLotInfo;

@Repository
public interface ChemicalLotInfoRepository extends JpaRepository<ChemicalLotInfo, Long> {

	@Query("select max(cast(lotNo as bigdecimal)) from ChemicalLotInfo")
	Optional<Integer> getMaxLot();

	@Query("select t1 from ChemicalLotInfo t1 where t1.chemicalId =?1 and t1.lotNo = ?2 and t1.isImport = false ")
	ChemicalLotInfo getChemicalLot(Long chemicalId, String lotCode);

	@Query("SELECT new com.example.lab.dto.response.ChemicalUsingResponseDto(t3.name,t6.name,t2.quantity,t3.chemicalType,t3.chemicalTypeInfo,t3.chemicalClass,t3.chemicalClassInfo,t4.positionInfo,t2.chemicalStatus,t3.otherInfo,t5) "
			+ "FROM ChemicalLotInfo t1 "
			+ "INNER JOIN ChemicalInventory t2 "
			+ "ON t1.id = t2.lotId "
			+ "INNER JOIN ChemicalInfo t3 "
			+ "ON t1.chemicalId = t3.id "
			+ "INNER JOIN PositionInfo t4 "
			+ "ON t2.positionId = t4.id "
			+ "INNER JOIN UserInfo t5 "
			+ "ON t3.registerUser.id = t5.user.id "
			+ "LEFT JOIN Brand t6 "
			+ "ON t6.id = t3.brandId "
			+ "WHERE t1.chemicalId =?1 "
			+ "AND t1.lotNo = ?2 "
			+ "AND t1.isImport = true "
			+ "AND t2.quantity > 0 "
			+ "AND t3.isDelete !='1' "
			+ "AND t2.isDelete !='1' ")
	ChemicalUsingResponseDto getUsingChemicalLot(Long chemicalId, String lotCode);

	@Query(" SELECT new com.example.lab.dto.response.ChemicalInfoResponseDto(t2,t3,t4) "
			+ "FROM ChemicalLotInfo t1 "
			+ "INNER JOIN ChemicalInfo t2 "
			+ "ON t1.chemicalId = t2.id "
			+ "INNER JOIN UserInfo t3 "
			+ "ON t2.registerUser.id = t3.user.id "
			+ "LEFT JOIN Brand t4 "
			+ "ON t4.id = t2.brandId "
			+ "WHERE t1.chemicalId =?1 "
			+ "AND t1.lotNo = ?2 "
			+ "AND t1.isImport = false "
			+ "AND t2.isDelete != '1' ")
	ChemicalInfoResponseDto getChemicalInfo(Long chemicalId, String lotCode);

}