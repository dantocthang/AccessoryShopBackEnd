package com.nhom9.springjwt.security.services;

import org.springframework.stereotype.Component;

import com.nhom9.springjwt.payload.response.DashbroadResponse;

@Component
public interface AdminService {
	DashbroadResponse getDashBroad();
}
