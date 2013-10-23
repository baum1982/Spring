package org.baum.app.edu.config;

import org.baum.app.edu.config.common.CommonProcessor;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MultiConfigMain2 {
	public static void main(String[] args) {
		AbstractApplicationContext context = 
				new ClassPathXmlApplicationContext(
						new String[]{"classpath:org/baum/app/edu/config/subconfig/*-context.xml", 
								"classpath:org/baum/app/edu/config/childcontext/*-context.xml"});
		
		ClassPathXmlApplicationContext childContext =
				new ClassPathXmlApplicationContext();
		
		childContext.setParent(context);
		childContext.setConfigLocation("classpath:org/baum/app/edu/config/childcontext/child-context.xml");
		childContext.refresh();
		
		for (String name : context.getBeanDefinitionNames()) {
			System.out.println(name);
		}
		
		System.out.println("===================================================");
		CommonProcessor bean = context.getBean(CommonProcessor.class);
		bean.publishEvent("Hello Child");
		System.out.println("===================================================");
		
	}
}
