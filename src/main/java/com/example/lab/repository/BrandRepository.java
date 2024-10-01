package com.example.lab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lab.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

	List<Brand> findByName(String name);

}
