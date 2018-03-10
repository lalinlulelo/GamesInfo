package com.GAS.mailService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MailController {


	@GetMapping("/")
	public String tablon(Model model) {

		return "mail";
	}

}
