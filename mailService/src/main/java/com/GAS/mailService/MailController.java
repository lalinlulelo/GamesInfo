package com.GAS.mailService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MailController {
	// pagina principal del mail service
	@GetMapping("/")
	public String tablon(Model model) {
		return "mail";
	}

}
