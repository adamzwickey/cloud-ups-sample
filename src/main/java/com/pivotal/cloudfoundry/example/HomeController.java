package com.pivotal.cloudfoundry.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static Logger LOG = LoggerFactory.getLogger(HomeController.class);	
	
	@Autowired private JdbcTemplate _template;
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public Map<Integer,String> getAll() {
		LOG.info("Getting all values");
		final Map<Integer,String> result = new HashMap<Integer,String>();
		
		_template.query("SELECT * FROM Attendee A", new RowCallbackHandler() {			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				result.put(new Integer(rs.getInt("id")), rs.getString("emailAddress"));				
			}
		});		
		return result;
	}
	
}
