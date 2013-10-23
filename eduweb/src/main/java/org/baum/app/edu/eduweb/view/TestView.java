package org.baum.app.edu.eduweb.view;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.AbstractView;


// 4개중 하나 택일
//@Service("name")
//@Controller("name")
//@Repository("name")
@Component("testView")
public class TestView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ServletOutputStream outputStream = response.getOutputStream();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
	
		System.out.println("들어왔다");
		bw.write("Test View Result\n");
		
		for (Entry<String, Object> entry : model.entrySet()) {
			bw.write(entry.getKey() + " : " + entry.getValue() + "\n");
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		bw.close();
	}

}
