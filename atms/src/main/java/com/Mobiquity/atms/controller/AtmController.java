package com.Mobiquity.atms.controller;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.Mobiquity.atms.service.AtmService;

@RestController
public class AtmController {

	@Autowired
	AtmService atmService;
	
	@GetMapping("/checkHealth")
	public String checkHealth() {
		return "ATM apllication is running fine";
	}
	
	@GetMapping("/allAtms")
	public String allAtms() {
		String res=atmService.getAllAtm();
		
		return res;
	}
	@GetMapping("/atmByCity/{city}")
	public Object atmByCity(@PathVariable("city") String city) throws JSONException {
		Object res= atmService.getAtmByCity(city);
		
		return res;
	}
}
