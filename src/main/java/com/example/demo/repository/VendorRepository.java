/**
 * 	Mar 19, 2019
 *	Alemberhan Getahun
 */
package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

	List<Vendor> findByBrandsName(String brand);
	List<Vendor> findByRegion(String region);

}
