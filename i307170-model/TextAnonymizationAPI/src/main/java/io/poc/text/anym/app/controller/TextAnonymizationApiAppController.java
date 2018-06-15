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
		ResultSet resulSetIndex = null;
		int strlen = text_input.length();
		int insertID = 0;
		int maxid = io.poc.text.anym.dbservices.HdbServices.getMaxId();
		insertID = maxid + 1;
		if(strlen <= 5000)
		{
			textin.setId(insertID);
		 	textin.setText(text_input);
		 	textinput.add(textin);	 	
		}
		
		int rows = io.poc.text.anym.dbservices.HdbServices.insertData(textinput);
		System.out.println("No or rows insertde " + rows );

		resulSetIndex = io.poc.text.anym.dbservices.HdbServices.getData(insertID);
	    try {
	    if (resulSetIndex != null){
			while(resulSetIndex.next()){
			    TextAnonym txtanym = new TextAnonym();
			    txtanym.setTa_token(resulSetIndex.getNString("TA_TOKEN"));
			    txtanym.setTa_type(resulSetIndex.getString("TA_TYPE"));
			    textanonym.add(txtanym);
			}
			
			io.poc.text.anym.dbservices.HdbServices.resultSetIndex.close();
	    }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			  }
	    return textanonym; 
	  }
 
	@RequestMapping(value = "/gettextanonym"+"/{ID}", method = RequestMethod.GET)
	
	public List<TextAnonym > getText( @PathVariable(value="ID") Integer id ) throws SQLException
	  {
		List<TextAnonym> textanonym = new ArrayList<TextAnonym>();
		ResultSet resulSetIndex = null;
		resulSetIndex = io.poc.text.anym.dbservices.HdbServices.getData(id);
	    try {
	    if (resulSetIndex != null){
			while(resulSetIndex.next()){
			    TextAnonym txtanym = new TextAnonym();
			    txtanym.setTa_token(resulSetIndex.getNString("TA_TOKEN"));
			    txtanym.setTa_type(resulSetIndex.getString("TA_TYPE"));
			    textanonym.add(txtanym);
			}
			
			io.poc.text.anym.dbservices.HdbServices.resultSetIndex.close();
	    }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return textanonym; 
	  }

	@RequestMapping(value = "/getinputtable", method = RequestMethod.GET)
	
	public int getInputTables( ) throws SQLException
	  {
		int tableno = 0;
		tableno = io.poc.text.anym.dbservices. HdbServices.getinputtables();
		return tableno; 
	  }
	
@RequestMapping(value = "/getindextable", method = RequestMethod.GET)
	
	public int getIndexTable( ) throws SQLException
	  {
		int tableno = 0;
		tableno = io.poc.text.anym.dbservices. HdbServices.getindextables();
		return tableno; 
	  }
@RequestMapping(value = "/getindextable"+"/{ID}", method = RequestMethod.GET)

public int getIndexTable(@PathVariable(value="ID") Integer id ) throws SQLException
  {
	int tableno = 0;
	tableno = io.poc.text.anym.dbservices. HdbServices.getindextables(id);
	return tableno; 
  }

}
