package com.nhom9.springjwt.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

@Controller
//@AllArgsConstructor
@SessionAttributes("User")
public class LogoutController {
	@ModelAttribute("User")
	public User userModel( ) {
		return new User();
	}
	@GetMapping("/logout")
	public String Logout(@ModelAttribute("User") User user, WebRequest request, SessionStatus status ) {
		//Xóa session user ra khỏi vị trí
		status.setComplete();//đã hoàn thành
		request.removeAttribute("user", WebRequest.SCOPE_SESSION);//Thực hiện xóa user ra session
		return "redirect:/login";
	}
}
