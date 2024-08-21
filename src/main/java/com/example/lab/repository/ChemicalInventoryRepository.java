package com.example.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.lab.model.ChemicalInventory;

public interface ChemicalInventoryRepository extends JpaRepository<ChemicalInventory, Long>{

	@Query("SELECT t1  FROM ChemicalInventory t1 \r\n"
			+ "where "
			+ "(?1 IS NULL OR t1.chemicalId=?1)"
			+ " ")
	ChemicalInventory findByChemicalId(Integer id);

}
