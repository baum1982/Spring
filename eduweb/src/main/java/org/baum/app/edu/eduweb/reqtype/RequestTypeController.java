package org.baum.app.edu.eduweb.reqtype;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class RequestTypeController {

	
	// http://localhost:8080/eduweb/test/basic/jsp?param=pppp
	@RequestMapping("basic/{type}")
	public ModelAndView basicModelAndView(@PathVariable("type") String type, HttpServletRequest req, HttpServletResponse resp) {
		Map<Object, Object> paramMap = req.getParameterMap();


		ModelAndView modelAndView = new ModelAndView();
		
		for (Entry<Object, Object> entry : paramMap.entrySet()) {
			System.out.println("KEY : " + entry.getKey() + ", VALUE : " + entry.getValue());
			modelAndView.addObject("CHG_" + entry.getKey(), entry.getValue());
		}

		
		if("jsp".equals(type)){
			modelAndView.setViewName("home");
		} else {
			modelAndView.setViewName("testView");
		}
		return null;
	}
	
	// http://localhost:8080/eduweb/test/basic/model/jsp?param=pppp,ooo,ooo
	@RequestMapping("basic/model/{type}")
	public String basicModelAndView(@PathVariable("type") String type, @RequestParam("param") String param, Model model) {
		String retView = "";

		model.addAttribute("CHG_", param);
				
		if("jsp".equals(type)){
			retView = "home";
		} else {
			retView = "testView";
		}
		
		return retView;
	}
	
	@XmlRootElement(name="RESULT")
	public static class AttrInfo{
		private String name;
		private int age;
		
		public String getName() {
			return name;
		}
		@XmlElement
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		@XmlElement
		public void setAge(int age) {
			this.age = age;
		}
		@Override
		public String toString() {
			return "AttrInfo [name=" + name + ", age=" + age + "]";
		}
		
		
	}
	
	// http://localhost:8080/eduweb/test/basic/attr/ppp?name=kimjh&age32
	@RequestMapping("basic/attr/{type}")
	public String basicModelAndView(@PathVariable("type") String type, 
			@ModelAttribute AttrInfo param, BindingResult result, Model model) {
		
//		System.out.println(result);
		
		String retView = "";

		model.addAttribute("CHG_", param);
				
		if("jsp".equals(type)){
			retView = "home";
		} else {
			retView = "testView";
		}
		
		return retView;
	}
	
	
	
	
	
	// http://localhost:8080/eduweb/test/type.json?name=kimjh&age=32
	// '.json'은 미디어 타입의 키값
	
	// http://localhost:8080/eduweb/test/type.xml?name=kimjh&age=32
	//
	@RequestMapping("type")
	public String returnMultiType(@ModelAttribute AttrInfo param, BindingResult result, Model model) {
		
		model.addAttribute("RESULT",  param);
		
		String retView = "";
		retView = "home";
		
		return retView;
	}
	
	// http://localhost:8080/eduweb/test/basic/attr/jsp.xml?name=kimjh&age32
	// http://localhost:8080/eduweb/test/basic/attr/jsp.json?name=kimjh&age32
	//
	// http://localhost:8080/eduweb/test/convertor.xml
	// http://localhost:8080/eduweb/test/convertor.json
	// {"name": "kimjh", "age": "32"}
	// <RESULT>
	//	<age>32</age>
	//	<name>kimjh</name>
	// </RESULT>
	// chrome-extension://hgmloofddffdnphfgcellkdfbfbjeloo/RestClient.html
	@RequestMapping("convertor")
	@ResponseBody
	public AttrInfo userMessage(@RequestBody AttrInfo param) {
		
		return param;
	}
	
	
	
	
	
	
	
	
	
	
}
