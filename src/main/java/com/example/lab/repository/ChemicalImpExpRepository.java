package com.example.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lab.model.ChemicalImpExp;

public interface ChemicalImpExpRepository extends JpaRepository<ChemicalImpExp, Long> {

}
