package org.baum.app.edu.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class PropertyUsingBean {

	private String sysVal;
	
	
	private String temp;
	private String placeHolderVal;
	
	private String utilPropVal;
	
	@Value("#{myProp['TY']}")
	private String annoUtilPropVal;
	
	@Value("${datasource.classname}")
	private String xmlclassname;
	
	
	private Integer numTest;
	
	public String getSysVal() {
		return sysVal;
	}
	public void setSysVal(String sysVal) {
		this.sysVal = sysVal;
	}
	
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getPlaceHolderVal() {
		return placeHolderVal;
	}
	public void setPlaceHolderVal(String placeHolderVal) {
		this.placeHolderVal = placeHolderVal;
	}
	public String getUtilPropVal() {
		return utilPropVal;
	}
	public void setUtilPropVal(String utilPropVal) {
		this.utilPropVal = utilPropVal;
	}
	public Integer getNumTest() {
		return numTest;
	}
	public void setNumTest(Integer numTest) {
		this.numTest = numTest;
	}
	
	
	private void xml() {
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
	
	
	@Override
	public String toString() {
		return "PropertyUsingBean [sysVal=" + sysVal + ", temp=" + temp
				+ ", placeHolderVal=" + placeHolderVal + ", utilPropVal="
				+ utilPropVal + ", annoUtilPropVal=" + annoUtilPropVal
				+ ", xmlclassname=" + xmlclassname + ", numTest=" + numTest
				+ "]";
	}
	
	
	
	
	
	
}
