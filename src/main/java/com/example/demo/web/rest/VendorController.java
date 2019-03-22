/**
 * 	Mar 19, 2019
 *	Alemberhan Getahun
 */
package com.example.demo.web.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Brand;
import com.example.demo.domain.Vendor;
import com.example.demo.dto.VendorDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.VendorService;

@RestController
@RequestMapping("/api")
public class VendorController {

	@Autowired
	VendorService vendorService;

	/**
	 * Add a new vendor
	 * 
	 * @param vendor
	 * @return vendor
	 */
	@PostMapping(path="/vendors", consumes =MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = "application/json")
	@PreAuthorize("hasAuthority('USER')")
	public Vendor newVendor(VendorDto vendor) {
		return vendorService.createVendor(vendor);
	}

	/**
	 * List all vendors
	 * 
	 * @return list of Vendors
	 */
	@GetMapping("/vendors" )
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Vendor> getAllVendors() throws ResourceNotFoundException {
		return vendorService.listAllVendors();
	}

	/**
	 * List all vendors that have a specific brand
	 * 
	 * @param brand
	 * @return list of Vendors
	 */
	@GetMapping("/vendors/brand/{brand}")
	@PreAuthorize("hasAuthority('USER')")
	public List<Vendor> getAllVendorsByBrand(@PathVariable(value = "brand") String brand)
			throws ResourceNotFoundException {
		return vendorService.listAllVendorsByBrand(brand);
	}

	/**
	 * List all vendors for a specific region
	 * 
	 * @param region
	 * @return list of Vendors
	 */
	@GetMapping("/vendors/region/{region}")
	@PreAuthorize("hasAuthority('USER')")
	public List<Vendor> getAllVendorsByRegion(@PathVariable(value = "region") String region)
			throws ResourceNotFoundException {
		return vendorService.listAllVendorsByRegion(region);
	}

	/**
	 * Add a brand to a vendor
	 * 
	 * @param id, brand
	 * @return list of Vendors
	 */
	@PutMapping("/vendors/{id}/brand")
	@PreAuthorize("hasAuthority('USER')")
	public Vendor addBrandToVendor(@PathVariable(value = "id") Long id, @Valid @RequestBody Brand brand)
			throws ResourceNotFoundException {
		return vendorService.addBrandToVendor(id, brand);
	}

	/**
	 * delete Vendor
	 * 
	 * @param id
	 */
	@DeleteMapping("/vendors/{id}")
	@PreAuthorize("hasAuthority('USER')")
	void deleteVendor(@PathVariable Long id) {
		vendorService.deleteById(id);
	}
	
	@GetMapping("vendors/report/count")
	public long getVendorsCount() {
		return vendorService.totalCount();
	}

}
