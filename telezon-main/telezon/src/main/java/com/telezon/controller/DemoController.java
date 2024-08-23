package com.telezon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	
	@GetMapping("/welcome")
	public String getMEssage() {
		
		return "Welcome to SpringBoot";
	}
	@GetMapping("/get/{pname}")
	 public String getProductDetails(@PathVariable("pname") String pname) {
		
		return "My product is CompactMobile"+pname;
	}
}
