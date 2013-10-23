package org.baum.app.edu.life;

import javax.inject.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ScopeMain {

	
	public static abstract class ABean {
		private int cnt = 0;
		
		public int getCnt() {
			return ++cnt;
		}

	}
	
	public static  class SingetonBean extends ABean{
		@Autowired
		ChildPrototypeBean bean;
		
		@Autowired
		Provider<ChildPrototypeBean> provider;
		
		@Override
		public int getCnt() {
			System.out.println("Single's Proto : " + bean.getCnt());
			System.out.println("Provided Single's Proto : " + provider.get().getCnt());
			return super.getCnt();
		}
	}
	public static  class PrototypeBean extends ABean{
		@Autowired
		ChildSingetonBean bean;
		
		@Override
		public int getCnt() {
			System.out.println("Proto's Single : " + bean.getCnt());
			return super.getCnt();
		}
	}
	
	
	public static  class ChildSingetonBean extends ABean{}
	public static  class ChildPrototypeBean extends ABean{}
	
	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:org/baum/app/edu/life/applicationContext.xml");
		
		for (int i = 0; i < 10; i++) {
			SingetonBean bean = context.getBean(SingetonBean.class);
			System.out.println(bean.getCnt());
		}
		
		
		for (int i = 0; i < 10; i++) {
			PrototypeBean bean = context.getBean(PrototypeBean.class);
			System.out.println(bean.getCnt());
		}
	}

}
