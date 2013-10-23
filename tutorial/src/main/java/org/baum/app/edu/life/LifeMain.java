package org.baum.app.edu.life;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.management.DescriptorKey;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LifeMain {

	
	public static class LifeBean implements InitializingBean, DisposableBean{
//		@Autowired
//		private String name;
		
		@Resource(name="lifeName")
		private String name;
		
		
		/*
		 * <bean>의 init-method 또는 @PostConstruct 
		 * <bean>의 destroy-method 또는  @PostConstruct
		 */
		
		
		public LifeBean() {
			System.out.println("Bean Construct " + name);
		}
		
		@PostConstruct
		public void annoInit() {
			System.out.println("Annotation Init " + name);
		}
		
		@PreDestroy
		public void annoDestroy() {
			System.out.println("Annotation Destroy " + name);
		}

		@Override
		public void destroy() throws Exception {
			System.out.println("Interface Destroy " + name);
		}

		@Override
		public void afterPropertiesSet() throws Exception {
			System.out.println("Inteface Init " + name);
		}
		
		public void configInit() {
			System.out.println("Config Init " + name);
		}
		
		public void configDestory() {
			System.out.println("Config Destroy " + name);
		}
	}
	
	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:org/baum/app/edu/life/applicationContext.xml");
		
		context.registerShutdownHook(); // 종료될 때 destory  제대로 한라는 의미
		LifeBean bean = context.getBean(LifeBean.class); 
		
		System.out.println("#########################");
		bean = context.getBean(LifeBean.class);
		
		System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
		context.refresh();
		bean = context.getBean(LifeBean.class);
		System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
		context.close();

	}

}
