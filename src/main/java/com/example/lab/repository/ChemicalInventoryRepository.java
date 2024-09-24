package com.example.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.lab.model.ChemicalInventory;

public interface ChemicalInventoryRepository extends JpaRepository<ChemicalInventory, Long>{

	@Query("SELECT t1  FROM ChemicalInventory t1 "
			+ "INNER JOIN ChemicalLotInfo t2 "
			+ "ON t2.id = t1.lotId "
			+ "WHERE "
			+ "(?1 IS NULL OR t1.chemicalId=?1) "
			+ "AND (?2 IS NULL OR t2.lotNo=?2) ")
	ChemicalInventory findByChemicalId(Long id,String lotCode);

}
