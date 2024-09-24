package com.example.lab.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.lab.model.ChemicalLotInfo;

@Repository
public interface ChemicalLotInfoRepository extends JpaRepository<ChemicalLotInfo, Long> {

	@Query("select max(cast(lotNo as bigdecimal)) from ChemicalLotInfo")
	Optional<Integer> getMaxLot();

	@Query("select t1 from ChemicalLotInfo t1 where t1.chemicalId =?1 and t1.lotNo = ?2 and t1.isImport = false ")
	ChemicalLotInfo getChemicalLot(Long chemicalId, String lotCode);

	@Query("SELECT t1 FROM ChemicalLotInfo t1 "
			+ "INNER JOIN ChemicalInventory t2 "
			+ "ON t1.id = t2.lotId "
			+ "WHERE t1.chemicalId =?1 "
			+ "AND t1.lotNo = ?2 "
			+ "AND t1.isImport = true "
			+ "AND t2.quantity > 0")
	ChemicalLotInfo getUsingChemicalLot(Long chemicalId, String lotCode);

//	@Query("select t1 from ChemicalLotInfo t1 where t1.")
//	ChemicalLotInfo findByLotNo(String lotCode);
	
}
