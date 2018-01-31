package com.takefour.themoment.controller;

		import org.springframework.stereotype.Controller;
		import org.springframework.web.bind.annotation.GetMapping;
		import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/moments")
public class MomentController {
	@GetMapping("/post")
	public String index() {
		return "/html/moments/post.html";
	}
}
