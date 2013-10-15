package org.baum.app.edu.proxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CGLibProxyMain {

	
	public static class TXManager {
		public void start(){
			System.out.println("START");
		}
		public void commit(){
			System.out.println("COMMIT");
		}
		public void rollback(){
			System.out.println("ROLLBACK");
		}
		
	}
	
	public static class SampleDao{
		public List<String> selectIds() {
			System.out.println("QUERY");
			List<String> retData = new ArrayList<String>();
			retData.add("류현진");
			retData.add("커쇼");
			retData.add("그레인키");
			
			return retData;
		}
	}
	
	public static class GCLibMethodInterceptor implements MethodInterceptor {

		private TXManager manager;
		
		
		public GCLibMethodInterceptor(TXManager manager) {
			this.manager = manager;
		}
		/**
		 * @param target 오브젝트
		 * @param method 메소드
		 * @param params 파라미터
		 * @param proxy
		 * 
		 * 
		 */
		@Override
		public Object intercept(Object target, Method method, Object[] params,
				MethodProxy proxy) throws Throwable {
			
			if(method.getDeclaringClass() == Object.class){
				method.invoke(target, params);
			}
			
			try {
				manager.start();
				Object invoke = proxy.invokeSuper(target, params);
				manager.commit();
				return invoke;
			} catch (Exception e) {
				manager.rollback();
				throw new RuntimeException(e);
			}
		}
	}
	
	public static void main(String[] args) {
		// 트랜젝션 관리
		TXManager manager = new TXManager();
		
		// Code Generation Lib
		Enhancer enhancer= new Enhancer();
		GCLibMethodInterceptor interceptor = new GCLibMethodInterceptor(manager);
		enhancer.setSuperclass(SampleDao.class);
		enhancer.setCallback(interceptor);
		
		SampleDao create = (SampleDao) enhancer.create();
		List<String> selectIds = create.selectIds();
		
		System.out.println(selectIds);
		
	}

}
