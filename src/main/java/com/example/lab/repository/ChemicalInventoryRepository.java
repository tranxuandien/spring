package com.example.lab.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.lab.dto.response.ChemicalInventoryResponseDto;
import com.example.lab.model.ChemicalInventory;

public interface ChemicalInventoryRepository extends JpaRepository<ChemicalInventory, Long>{

	@Query("SELECT t1  FROM ChemicalInventory t1 "
			+ "INNER JOIN ChemicalLotInfo t2 "
			+ "ON t2.id = t1.lotId "
			+ "WHERE "
			+ "(?1 IS NULL OR t1.chemicalId=?1) "
			+ "AND (?2 IS NULL OR t2.lotNo=?2) ")
	ChemicalInventory findByChemicalId(Long id,String lotCode);

	@Query("SELECT new com.example.lab.dto.response.ChemicalInventoryResponseDto(t1.id,t2.name, t4.name, t2.chemicalType, t2.chemicalTypeInfo, "
			+ "t2.chemicalClass, t2.chemicalClassInfo, t1.quantity, t5.positionInfo, t1.expiredDate, CASE "
			+ "WHEN t1.quantity=0 THEN 'Hết' "
			+ "WHEN CONVERT(t1.quantity,DECIMAL(10,2))<= CONVERT(t2.alertQuantity,DECIMAL(10,2)) THEN 'Sắp Hết' "
			+ "ELSE 'Mới' END, t1.chemicalStatus, t1.purchaseSrc,t2.id,t3.lotNo) "
			+ "FROM ChemicalInventory t1 "
			+ "INNER JOIN ChemicalInfo t2 "
			+ "ON t1.chemicalId = t2.id "
			+ "INNER JOIN ChemicalLotInfo t3 "
			+ "ON t3.id = t1.lotId "
			+ "LEFT JOIN Brand t4 "
			+ "ON t4.id = t2.brandId "
			+ "LEFT JOIN PositionInfo t5 "
			+ "ON t5.id = t1.positionId "
			+ "WHERE t1.isDelete = '0' "
			+ "AND (?1 IS NULL OR t2.name LIKE %?1%) "
			+ "AND (?2 IS NULL OR t2.brandId = ?2) "
			+ "AND (?3 IS NULL OR t2.chemicalType =?3) "
			+ "AND (?4 IS NULL OR t2.chemicalClass=?4) "
			+ "AND (?5 IS NULL OR t1.positionId=?5) "
			+ "AND (?6 IS NULL OR ((?7 IS NULL AND (CONVERT(t1.quantity,DECIMAL(10,2)) > CONVERT(t2.alertQuantity,DECIMAL(10,2)))) OR ((?7 IS NOT NULL AND (CONVERT(t1.quantity,DECIMAL(10,2)) > CONVERT(0,DECIMAL(10,2)) ))))) "
			+ "AND (?7 IS NULL OR ((?6 IS NULL AND (CONVERT(t1.quantity,DECIMAL(10,2)) <= CONVERT(0,DECIMAL(10,2)) )) OR ((?6 IS NOT NULL AND (CONVERT(t1.quantity,DECIMAL(10,2)) <= CONVERT(t2.alertQuantity,DECIMAL(10,2))))))) "
			+ "ORDER BY t1.createAt DESC ")
	List<ChemicalInventoryResponseDto> getAllInfo(String name, String brand, String type, String chemicalClass, String position,BigDecimal range1,BigDecimal range2);

	@Query("SELECT t1 FROM ChemicalInventory t1 WHERE t1.id = ?1 AND t1.isDelete ='0' ")
	Optional<ChemicalInventory> byId(Long id);

}
