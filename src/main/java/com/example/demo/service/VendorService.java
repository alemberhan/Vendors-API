/**
 * 	Mar 19, 2019
 *	Alemberhan Getahun
 */
package com.example.demo.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Brand;
import com.example.demo.domain.Vendor;
import com.example.demo.dto.VendorDto;
import com.example.demo.exception.ResourceNotFoundException;


@Service
public interface VendorService {

	/**
	 * create a new vendor
	 * @param vendor
	 * @return vendor
	 */
	Vendor createVendor(@Valid VendorDto vendor);

	/**
	 * List all vendors
	 * @return list of Vendors
	 */
	List<Vendor> listAllVendors() throws ResourceNotFoundException;

	/**
	 * List all vendors that have a specific brand
	 * @param brand
	 * @return list of Vendors
	 */
	List<Vendor> listAllVendorsByBrand(String brand) throws ResourceNotFoundException;

	/**
	 * List all vendors for a specific region
	 * @param region
	 * @return list of Vendors
	 */
	List<Vendor> listAllVendorsByRegion(String region) throws ResourceNotFoundException;

	/**
	 * @param id
	 * @param brand
	 * @return
	 */
	Vendor addBrandToVendor(Long id, @Valid Brand brand);

	/**
	 * @param id
	 */
	void deleteById(Long id);

	/**
	 * @return
	 */
	long totalCount();

}
