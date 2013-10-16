package org.baum.app.edu.config;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConfigMain {

	public static class ObjectBean implements BeanNameAware{
		private String name;
		
		
		
		@Override
		public String toString() {
			return "ObjectBean [name=" +name+ "]" ;
		}



		@Override
		public void setBeanName(String name) {
			this.name = name;
			
		}
	}
	
	public static class CollectionBean {
		Map<String, Integer> dataMap;
		List<ObjectBean> dataObjList;
		
		public Map<String, Integer> getDataMap() {
			return dataMap;
		}
		public void setDataMap(Map<String, Integer> dataMap) {
			this.dataMap = dataMap;
		}
		
		public List<ObjectBean> getDataObjList() {
			return dataObjList;
		}
		public void setDataObjList(List<ObjectBean> dataObjList) {
			this.dataObjList = dataObjList;
		}
	}
	
	
	
	public static void main(String[] args) {
		System.setProperty("spring.profiles.active", "REAL");
		
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:org/baum/app/edu/config/applicationContext.xml");
		CollectionBean bean = 
				context.getBean(CollectionBean.class);
		
		for (ObjectBean item : bean.getDataObjList()) {
			System.out.println(item.toString());
		}

		System.out.println("-----------------------------------------");
		
		for (Entry<String, Integer> entry : bean.getDataMap().entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}

}
