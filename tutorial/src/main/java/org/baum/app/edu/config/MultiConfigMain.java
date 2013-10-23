package org.baum.app.edu.config;

import org.baum.app.edu.config.common.CommonProcessor;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MultiConfigMain {
	public static void main(String[] args) {
		AbstractApplicationContext context = 
				new ClassPathXmlApplicationContext("classpath:org/baum/app/edu/config/parent-Context.xml");
		
		for (String str : context.getBeanDefinitionNames()) {
			if(context.isSingleton(str)){
				System.out.println("Single: " + str);
			} else {
				System.out.println("Not Single: " + str);
			}
		}
		
		System.out.println("===================================================");
		
		FileSystemXmlApplicationContext fx = new FileSystemXmlApplicationContext();
		fx.setParent(context);
		fx.refresh();
		
		// 자기거만 보여준다.
		for (String str : fx.getBeanDefinitionNames()) {
			if(fx.isSingleton(str)){
				System.out.println("FX Single: " + str);
			} else {
				System.out.println("FX Not Single: " + str);
			}
		}
		
		System.out.println("===================================================");
		
		// 부모거만 보여준다.
		for (String str : fx.getParent().getBeanDefinitionNames()) {
			if(context.isSingleton(str)){
				System.out.println("Parent Single: " + str);
			} else {
				System.out.println("Parent Not Single: " + str);
			}
		}
				
		
		
		System.out.println("===================================================");
		CommonProcessor bean = fx.getBean(CommonProcessor.class);
		System.out.println(bean.getClass());
		
		
		
		
	}
}
