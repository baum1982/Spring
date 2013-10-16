package org.baum.app.edu.eduweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("userController")
@RequestMapping(value="/user")
public class UserController {

	
	
	
	
	@RequestMapping(value={"get/{user}"})
	public String getUser(Model model, @PathVariable("user") String userId) {
		// http://localhost:8080/eduweb/user/get/kimjh
		
		System.out.println("return user " + userId);
		System.out.println();
		model.addAttribute("OUTPUT", userId);
		
		// 이 값을 기준으로 /WEB-INF/servlet-context/applicationContext.xml 에 정의된
		// suffix, prefix가 적용되어 페이지 이동 한다.
		return "user";
	}
	
	@RequestMapping(value={"getDetail/{user}"})
	public String getUserDetail(Model model, @PathVariable("user") String userId, @RequestParam("stat") String stat) {
		// http://localhost:8080/eduweb/user/get/kimjh?stat=fire
		
		System.out.println("return user " + userId);
		System.out.println("stat " + stat);
		model.addAttribute("OUTPUT", userId);
		
		// 이 값을 기준으로 /WEB-INF/servlet-context/applicationContext.xml 에 정의된
		// suffix, prefix가 적용되어 페이지 이동 한다.
		return "user";
	}
	
	
	
	@RequestMapping(value={"del/{user}"})
	public String delUser(Model model, @PathVariable("user") String userId) {
		// http://localhost:8080/eduweb/user/del/kimjh
		System.out.println("delete user " + userId);
		model.addAttribute("OUTPUT", userId);
		
		// 이 값을 기준으로 /WEB-INF/servlet-context/applicationContext.xml 에 정의된
		// suffix, prefix가 적용되어 페이지 이동 한다.
		return "user";
	}
	
}
