package net.javaguides.springbootsecurity.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserHomeController {

	@GetMapping("/userHome")
	public String home(Model model, HttpSession session)
	{
		
		return "adminhome";
	}
}
