package org.baum.app.edu.di;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.MethodReplacer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DIMain {

	
	
	public static class FirstBean {
		
		String name;
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "Hello " + name;
		}
	}
		
	public static class SecondBean {
		@Autowired
		@Qualifier("F2")
		FirstBean firstBean;
		
		
		@Override
		public String toString() {
			
			return "Second Bean [FirstBean=" + firstBean + "]";
		}
		
		
		
//		public FirstBean getFirstBean() {
//			return firstBean;
//		}
//		
//		public void setFirstBean(FirstBean firstBean) {
//			this.firstBean = firstBean;
//		}
		
		public FirstBean getMyFirst() {
			return firstBean;
		}
		
		
		public String getReplaceMethod(String name) {
			return "hello world " + name;

		}
		
	}
	
	
	
	public static class Replacer implements MethodReplacer {

		/**
		 * @param arg0 오브젝트
		 * @param arg1 메소드
		 * @param arg2 파라미터
		 */
		@Override
		public Object reimplement(Object arg0, Method arg1, Object[] arg2)
				throws Throwable {
			
			return "건백";
		}
		
	}

	
	public static void main(String[] args) {
		// Classpath안의 컨텍스트 정보를 읽어 활용한다.
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:org/baum/app/edu/di/applicationContext.xml");
	
		for (String name : context.getBeanDefinitionNames()) {
			System.out.println(name);// 아이디나 클래스명
			System.out.println(context.getBean(name));
			
		}
	
		
		
		//look-up  id, name, type  세가지 방
		SecondBean bean = context.getBean(SecondBean.class);
		System.out.println(bean);
		System.out.println(bean.getMyFirst());
		
		System.out.println(bean.getReplaceMethod("준태"));
		
		
		
		
	}

}
