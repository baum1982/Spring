package org.baum.app.edu.first;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FirstMain {
	
	public static class FirstBean {
		private String name;
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return "Hello " + name;
		}
	}
		
	public static class SecondBean {
		FirstBean firstBean;
		String name;
		@Override
		public String toString() {
			
			return "Second Bean [FirstBean=" + firstBean + "]";
		}
		
		public FirstBean getFirstBean() {
			return firstBean;
		}
		
		public void setFirstBean(FirstBean firstBean) {
			this.firstBean = firstBean;
		}
		
		
		
	}

	
	public static void main(String[] args) {
		// Classpath안의 컨텍스트 정보를 읽어 활용한다.
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:org/baum/app/edu/first/applicationContext.xml");
	
		for (String name : context.getBeanDefinitionNames()) {
			System.out.println(name);// 아이디나 클래스명
			System.out.println(context.getBean(name));
		}
	
	}

}
