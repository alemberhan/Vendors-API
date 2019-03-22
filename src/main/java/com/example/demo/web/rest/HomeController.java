/**
 * 	Mar 19, 2019
 *	Alemberhan Getahun
 */
package com.example.demo.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.VendorDto;
import com.example.demo.service.VendorService;

@Controller
public class HomeController {

	@Autowired
	VendorService vendorService;

	@RequestMapping("/")
	public String welcome(Model model) {
		return "index";
	}

	@RequestMapping("/addVendor")
	public String addVendor(Model model) {
		model.addAttribute("vendor", new VendorDto());
		return "add";
	}
}