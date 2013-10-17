package org.baum.app.edu.eduweb.dbsupport;



import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public abstract class ParentDao extends JdbcDaoSupport{

	@Autowired
	@Qualifier("mainSource")
	DataSource dataSource;
	
	
	@PostConstruct
	protected void init() {
		super.setDataSource(dataSource);
	}
}
