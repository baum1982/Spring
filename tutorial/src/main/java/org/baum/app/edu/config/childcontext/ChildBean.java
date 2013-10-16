package org.baum.app.edu.config.childcontext;

import org.baum.app.edu.config.common.CommonProcessor.ParentEvent;
import org.baum.app.edu.config.common.CommonProcessor.ChildEvent;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ChildBean implements ApplicationListener<ParentEvent>, 
								ApplicationContextAware, 
								BeanNameAware{

	private String name;
	private ApplicationContext applicationContext;
	
	
	@Override
	public void setBeanName(String name) {
		this.name = name;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public void onApplicationEvent(ParentEvent event) {
		System.out.println("RECEIVE PARENT EVENT " + name);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("EVENT PROCESS FINISH " + name);
		this.applicationContext.publishEvent(new ChildEvent(name));
	}

}
