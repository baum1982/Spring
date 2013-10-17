package org.baum.app.edu.eduweb.controller;

import java.util.List;

import org.baum.app.edu.eduweb.dbsupport.ParentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/db")
public class DBAccessController {

	public static class UserData {
		private String key;
		private String name;
		private  Integer age;
		
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		@Override
		public String toString() {
			return "UserData [key=" + key + ", name=" + name + ", age=" + age
					+ "]";
		}
		
		
		
	}

	@Service
	public static class UserService {
		@Autowired
		UserDao userDao;
		
		public List<UserData> getUserList() {
			return userDao.getUserList();
		}
		

		public void insUser(UserData data) {
			userDao.insUserList(data);
		}
	}

	
	@Repository
	public static class UserDao extends ParentDao {
		
		public List<UserData> getUserList() {
			List<UserData> list = 
					getJdbcTemplate().query("select `key`,`name`,`age` from HIVE.new_table", new BeanPropertyRowMapper<UserData>(UserData.class));
			return list;
		}
		
		public void insUserList(UserData data) {
			
			String sql = 
					"insert into HIVE.new_table (`key`,`name`,`age`) values('%s', '%s', '%s')";
			
			sql = String.format(sql, data.getKey(), data.getName(), data.getAge()); 
			
			getJdbcTemplate().execute(sql);
		}
		
		
		
		
	}
	
	@Autowired
	UserService service;
	
	@RequestMapping("get")
	public String getUserList(Model model) {
		model.addAttribute("USERLIST", service.getUserList());
		
		
		return "user";
	}
	
	@RequestMapping("add")
	public String addUser(@ModelAttribute UserData data, Model model) {
		//http://localhost:8080/eduweb/db/add?key=baum&name=kimjh&age32

		service.insUser(data);
		model.addAttribute("USERLIST", service.getUserList());
		
		
		return "user";
	}

	@RequestMapping("addex")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public String addExUser(@ModelAttribute UserData data, Model model) throws Exception {
		// http://localhost:8080/eduweb/db/addex?key=baum&name=kimjh&age32
		service.insUser(data);
		model.addAttribute("USERLIST", service.getUserList());
		if(true){
			throw new Exception("고의 에러");
		}
		
		return "user";
	}




}
