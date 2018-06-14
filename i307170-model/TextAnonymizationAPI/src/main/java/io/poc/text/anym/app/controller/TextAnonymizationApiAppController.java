package io.poc.text.anym.app.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.poc.text.anym.app.entity.TextAnonym;
import io.poc.text.anym.app.entity.TextInput;



@RestController
public class TextAnonymizationApiAppController {
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	
	  public TextInput sayHello(TextInput textinput)
	  {
		TextInput ti = new TextInput();
		ti.setId(119);
		ti.setText("Hi from TextAnonymization CF app");
		return ti;
	  }
	
	@RequestMapping(value = "/processtext", method = RequestMethod.POST)
	
	  public List<TextAnonym> sendText(@RequestBody String text_input) throws SQLException
	  {
		TextInput textin = new TextInput();
		ArrayList<TextInput> textinput = new ArrayList<TextInput>();
		List<TextAnonym> textanonym = new ArrayList<TextAnonym>();
		ResultSet resultSet = null;
		int strlen = text_input.length();
		int maxid = io.poc.text.anym.dbservices.HdbServices.getMaxId();
		if(strlen <= 5000)
		{
			textin.setId(maxid + 1);
		 	textin.setText(text_input);
		 	textinput.add(textin);	 	
		}
		
		int rows = io.poc.text.anym.dbservices.HdbServices.insertData(textinput);
		if (rows != 0)
		resultSet = io.poc.text.anym.dbservices.HdbServices.getData(maxid + 1);
		
		if (resultSet != null){
			while(resultSet.next()){
		    TextAnonym txtanym = new TextAnonym();
		    txtanym.setTa_token(resultSet.getNString("TA_TOKEN"));
		    txtanym.setTa_type(resultSet.getString("TA_TYPE"));
		    textanonym.add(txtanym);
		}
		resultSet.close();
	  }
		return textanonym; 
	  }
 
	@RequestMapping(value = "/gettextanonym"+"/{ID}", method = RequestMethod.GET)
	
	public List<TextAnonym > getText( @PathVariable(value="ID") Integer id ) throws SQLException
	  {
		List<TextAnonym> textanonym = new ArrayList<TextAnonym>();
		ResultSet resultSet = null;
		resultSet = io.poc.text.anym.dbservices.HdbServices.getData(id);
		if (resultSet != null){
			while(resultSet.next()){
			    TextAnonym txtanym = new TextAnonym();
			    txtanym.setTa_token(resultSet.getNString("TA_TOKEN"));
			    txtanym.setTa_type(resultSet.getString("TA_TYPE"));
			    textanonym.add(txtanym);
			}
			resultSet.close();
		  }
			return textanonym; 
	  }

	@RequestMapping(value = "/gettables", method = RequestMethod.GET)
	
	public int getTables( ) throws SQLException
	  {
		int tableno = 0;
		tableno = io.poc.text.anym.dbservices. HdbServices.gettables();
		return tableno; 
	  }

}
