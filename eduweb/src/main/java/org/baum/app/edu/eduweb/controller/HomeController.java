package org.baum.app.edu.eduweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("comeController")
@RequestMapping(value="/")
public class HomeController {

	// http://localhost:8080/eduweb/index.html
	
	@RequestMapping(value={"index.html",""})
	public String hello() {
		System.out.println("Hello Home");
		
		// 이 값을 기준으로 /WEB-INF/servlet-context/applicationContext.xml 에 정의된
		// suffix, prefix가 적용되어 페이지 이동 한다.
		return "home";// WEB-INF/view/home.jsp
	}
}
