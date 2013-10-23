package org.baum.app.edu.aop;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPMain {
	
	public static class BizBean{

		public void resisterUser(String userName) {
			System.out.println("USER REGISTERED");
		}
		
		public void banUser(String userName) {
			System.out.println("USER BANNED");
		}
		
		
	}
	
	@Aspect
	public static class OpMonitor{
		// 스프링 AOP 표현식 참조
		private static final String EXP = "execution(* *(..))";
		
		@Before(EXP)
		public void OpBefore(JoinPoint point) {
			System.out.println(point.getSignature().toShortString() + "[" + Arrays.deepToString(point.getArgs()) +  "] START");
		}
		@After(EXP)
		public void OpAfter(JoinPoint point) {
			System.out.println(point.getSignature().toShortString() + "[" + Arrays.deepToString(point.getArgs()) +  "] END");
		}
		
		@Around(EXP)
		public Object OpAround(ProceedingJoinPoint point) {
			
			System.out.println("AROUND Before");
			
			Object[] args = point.getArgs();
			
			
			
			List<Object> asList =  Arrays.asList(args);
			
			for (int i = 0; i < args.length; i++) {
				if(args[i] instanceof String){
					asList.set(i, args[i].toString() + " TOUCH");
				}
			}
			
			
			try {
				return point.proceed(asList.toArray());
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return 0;
		}
	}

	public static void main(String[] args) {
		
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("classpath:org/baum/app/edu/aop/applicationContext.xml");
		
//		for (String name : context.getBeanDefinitionNames()) {
//			System.out.println(name);// 아이디나 클래스명
//			System.out.println(context.getBean(name));
//			
//		}
		
		BizBean bean = context.getBean(BizBean.class);
//		bean.resisterUser("재환");
		bean.banUser("재환");
		bean.toString();
	}

}
