package no.comp.finalproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminPagesController {

	@RequestMapping("")
	public ModelAndView root() {
		ModelAndView model = new ModelAndView("admin/root");
		return model;
	}
	
}
