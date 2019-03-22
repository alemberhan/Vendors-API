/**
 * 	Mar 19, 2019
 *	Alemberhan Getahun
 */
package com.example.demo.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Brand;
import com.example.demo.domain.Vendor;
import com.example.demo.dto.VendorDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BrandRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorService;

@Service
public class VendorServiceImpl implements VendorService {

	@Autowired
	VendorRepository vendorRepository;
	
	@Autowired
	BrandRepository brandRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.demo.service.VendorService#createVendor(com.example.demo.domain.
	 * Vendor)
	 */
	@Override
	@Transactional
	public Vendor createVendor(@Valid VendorDto dto) {
		Vendor vendor = createVendorFromDto(dto);
		return vendorRepository.save(vendor);
	}

	/**
	 * @param vendorDto
	 * @return
	 */
	private Vendor createVendorFromDto(@Valid VendorDto dto) {
		
		Vendor vendor = new Vendor();
		vendor.setName(dto.getName());
		vendor.setPhone(dto.getPhone());
		vendor.setRegion(dto.getRegion());
		String[] brands = dto.getBrands().split(",");
		Set<Brand> newBrands = new HashSet<Brand>();
		for( String str : brands) {
			Brand brand = new Brand();
			brand.setName(str);
			newBrands.add(brand);
		}
		brandRepository.saveAll(newBrands);
		vendor.setBrands(newBrands);
		return vendor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.service.VendorService#listAllVendors()
	 */
	@Override
	public List<Vendor> listAllVendors() {
		return vendorRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.service.VendorService#listAllVendorsByBrand(java.lang.
	 * String)
	 */
	@Override
	public List<Vendor> listAllVendorsByBrand(String brand) throws ResourceNotFoundException {
		List<Vendor> result = vendorRepository.findByBrandsName(brand);
		if (result == null) {
			throw new ResourceNotFoundException("Vendor not found on brand: " + brand);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.service.VendorService#listAllVendorsByRegion(java.lang.
	 * String)
	 */
	@Override
	public List<Vendor> listAllVendorsByRegion(String region) throws ResourceNotFoundException {
		List<Vendor> result = vendorRepository.findByRegion(region);
		if (result == null) {
			throw new ResourceNotFoundException("Vendor not found on region: " + region);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.demo.service.VendorService#addBrandToVendor(java.lang.String,
	 * com.example.demo.domain.Brand)
	 */
	@Override
	public Vendor addBrandToVendor(Long id, @Valid Brand brand) throws ResourceNotFoundException {
		Vendor vendor = vendorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Vendor not found on  ::" + id));

		vendor.getBrands().add(brand);
		vendorRepository.save(vendor);
		return vendor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.service.VendorService#deleteById(java.lang.Long)
	 */
	@Override
	public void deleteById(Long id) {
		vendorRepository.deleteById(id);

	}

	/* (non-Javadoc)
	 * @see com.example.demo.service.VendorService#totalCount()
	 */
	@Override
	public long totalCount() {
		return vendorRepository.count();
	}

}
