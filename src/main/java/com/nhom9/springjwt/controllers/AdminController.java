package com.nhom9.springjwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nhom9.springjwt.payload.response.DashbroadResponse;
import com.nhom9.springjwt.security.services.AdminService;

@CrossOrigin(origins = "http://127.0.0.1:5173", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminController {
	@Autowired
	AdminService adminService;
	
	/*get dash broad data*/
	@GetMapping("/dashbroad")
	public DashbroadResponse dashBroad()
	{
		return adminService.getDashBroad();
	}
}
