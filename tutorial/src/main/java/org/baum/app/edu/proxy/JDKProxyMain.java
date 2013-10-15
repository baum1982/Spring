package org.baum.app.edu.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class JDKProxyMain {
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
	
	public static interface ISampleDao{
		public List<String> selectIds();
	}
	
	public static class SampleDao implements ISampleDao{
		@Override
		public List<String> selectIds() {
			System.out.println("QUERY");
			List<String> retData = new ArrayList<String>();
			retData.add("류현진");
			retData.add("커쇼");
			retData.add("그레인키");
			
			return retData;
		}
	}
	

	
	public static class JDKInvocationHandler implements InvocationHandler{

		Object targetObj;
		TXManager manager;
		public JDKInvocationHandler(Object targetObj, TXManager manager) {
			super();
			this.targetObj = targetObj;
			this.manager = manager;
		}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			
			if(method.getDeclaringClass() == Object.class){
				return method.invoke(targetObj, args);
			}else {
			
				try {
					manager.start();
					Object invoke = method.invoke(targetObj, args);
					manager.commit();
					return invoke;
				} catch (Exception e) {
					manager.rollback();
					throw new RuntimeException(e);
				}
			}
			
			
		}
		
	}
	
	public static void main(String[] args) {
		SampleDao sampleDao = new SampleDao();
		JDKInvocationHandler h = new JDKInvocationHandler(sampleDao, new TXManager());
		ISampleDao newProxyInstance = (ISampleDao) Proxy.newProxyInstance(JDKProxyMain.class.getClassLoader(), SampleDao.class.getInterfaces(), h);

		System.out.println(sampleDao.selectIds());
		System.out.println("+++++++++++++++++++++++");
		System.out.println(newProxyInstance.selectIds());
		
	}

}
