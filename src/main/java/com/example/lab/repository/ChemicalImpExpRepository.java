package com.example.lab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.lab.dto.response.ChemicalImpExpChartResponseDto;
import com.example.lab.dto.response.ChemicalImpExpResponseDto;
import com.example.lab.model.ChemicalImpExp;

public interface ChemicalImpExpRepository extends JpaRepository<ChemicalImpExp, Long> {

	@Query("SELECT new com.example.lab.dto.response.ChemicalImpExpResponseDto(t2.name,t6.name,t1.quantity,t1.createAt,"
			+ "t2.chemicalType,t2.chemicalTypeInfo,t2.chemicalClass,t2.chemicalClassInfo,t7.positionInfo,t1.type,t5.firstName,t5.lastName,t2.id,t3.lotNo) "
			+ "FROM ChemicalImpExp t1 "
			+ "INNER JOIN ChemicalInfo t2 "
			+ "ON t1.chemicalId = t2.id "
			+ "INNER JOIN ChemicalLotInfo t3 "
			+ "ON t1.chemicalId = t3.chemicalId "
			+ "INNER JOIN ChemicalInventory t4 "
			+ "ON t1.chemicalId = t4.chemicalId "
			+ "INNER JOIN UserInfo t5 "
			+ "ON (t5.user.id = t1.impUser OR t5.user.id = t1.expUser) "
			+ "LEFT JOIN Brand t6 "
			+ "ON t6.id = t2.brandId "
			+ "LEFT JOIN PositionInfo t7 "
			+ "ON t7.id = t4.positionId "
			+ "WHERE t3.isImport= true "
			+ "AND t4.lotId = t3.id "
			+ "AND t1.lotId = t3.id "
			+ "AND ((?1 IS NULL OR t1.impUser = ?1) "
			+ "OR (?1 IS NULL OR t1.expUser = ?1)) "
			+ "AND (?2 IS NULL OR t2.name LIKE %?2%) "
			+ "AND (?3 IS NULL OR t2.brandId = ?3) "
			+ "AND (?4 IS NULL OR t2.chemicalType =?4) "
			+ "AND (?5 IS NULL OR t2.chemicalClass=?5) "
			+ "AND (?6 IS NULL OR t4.positionId=?6) "
			+ "ORDER BY t1.createAt DESC")
	List<ChemicalImpExpResponseDto> getAllHistory(Long user,String name, String brand, String type, String chemicalClass, String position);

	
	@Query("SELECT new com.example.lab.dto.response.ChemicalImpExpChartResponseDto(t5.user.id,CONCAT(t5.firstName,t5.lastName),SUM(t1.quantity)) "
			+ "FROM ChemicalImpExp t1 "
			+ "INNER JOIN ChemicalInfo t2 "
			+ "ON t1.chemicalId = t2.id "
			+ "INNER JOIN ChemicalLotInfo t3 "
			+ "ON t1.chemicalId = t3.chemicalId "
			+ "INNER JOIN ChemicalInventory t4 "
			+ "ON t1.chemicalId = t4.chemicalId "
			+ "INNER JOIN UserInfo t5 "
			+ "ON (t5.user.id = t1.impUser OR t5.user.id = t1.expUser)"
			+ "WHERE t3.isImport= true "
			+ "AND t4.lotId = t3.id "
			+ "AND t1.lotId = t3.id "
			+ "AND ((?1 IS NULL OR t1.impUser = ?1) "
			+ "OR (?1 IS NULL OR t1.expUser = ?1)) "
			+ "AND (?2 IS NULL OR t2.name LIKE %?2%) "
			+ "AND (?3 IS NULL OR t2.brandId = ?3) "
			+ "AND (?4 IS NULL OR t2.chemicalType =?4) "
			+ "AND (?5 IS NULL OR t2.chemicalClass=?5) "
			+ "AND (?6 IS NULL OR t4.positionId=?6) "
			+ "AND t1.type = 'Xuất' "
			+ "GROUP BY t5.user.id")
	List<ChemicalImpExpChartResponseDto> getAllHistoryForChart(Long id, String chemicalName, String brand,
			String chemicalType, String chemicalClass, String position);

}