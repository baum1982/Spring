package org.baum.app.edu.config.common;

import org.baum.app.edu.config.datasource.OracleDataSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.baum.app.edu.config.common.CommonProcessor.ChildEvent;


@Component
public class CommonProcessor implements ApplicationListener<ChildEvent>, ApplicationContextAware{
	
	public static class ParentEvent extends ApplicationEvent{
		private static final long serialVersionUID = -127442838205842633L;

		public ParentEvent(Object source) {
			super(source);
		}
	}
	
	public static class ChildEvent  extends ApplicationEvent {
		private static final long serialVersionUID = 2924974563761692779L;

		public ChildEvent(Object source) {
			super(source);
		}
	}
	
	
	@Autowired
	OracleDataSource oracleDataSource;
	private ApplicationContext applicationContext;



	@Override
	public void onApplicationEvent(ChildEvent event) {
		System.out.println(event.getSource().toString() + " EVENT PROCESS FINISH");
		
	}




	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
		
	}
	
	public void publishEvent(String msg) {
		applicationContext.publishEvent(new ParentEvent(msg));
	}
}
