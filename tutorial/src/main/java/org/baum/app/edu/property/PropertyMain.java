package org.baum.app.edu.property;

import org.apache.commons.configuration.XMLConfiguration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PropertyMain {

	public static void main(String[] args) {
		System.setProperty("System.Prop", "킹콩");
		
		AbstractApplicationContext context = 
				new ClassPathXmlApplicationContext("classpath:org/baum/app/edu/property/applicationContext.xml");
		
		// 다른부분 소멸자 호출
		context.registerShutdownHook();

		PropertyUsingBean bean = context.getBean(PropertyUsingBean.class);
		System.out.println(bean.toString());
		
		for (String beanName : context.getBeanDefinitionNames()) {
			if(!beanName.startsWith("org.spring"))
				System.out.println("BEAN NAME : " + beanName + " TYPE : " + context.getBean(beanName).getClass());
		}
		
		
		
	}

}
