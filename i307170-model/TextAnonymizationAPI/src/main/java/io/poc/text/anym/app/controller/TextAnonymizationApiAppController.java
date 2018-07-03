package io.poc.text.anym.app.controller;

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
		ArrayList<TextAnonym> textanonym = new ArrayList<TextAnonym>();
		//int strlen = text_input.length();
		int insertID = 0;
		int maxid = io.poc.text.anym.dbservices.HdbServices.getMaxId();
		insertID = maxid + 1;
		text_input = text_input.replace("'" , "");
		text_input = text_input.replace("‘" , "");
		//if(strlen <= 5000)
		//{
			textin.setId(insertID);
		 	textin.setText(text_input);
		 	textinput.add(textin);	 	
		//}
		
		int rows = io.poc.text.anym.dbservices.HdbServices.insertData(textinput);
		System.out.println("No or rows insertde " + rows );

		textanonym = io.poc.text.anym.dbservices.HdbServices.getData(insertID);
		
		int loop = 0;
	do{
		if (textanonym.isEmpty() ){
	try {		
	        Thread.sleep(2000);
			textanonym= io.poc.text.anym.dbservices.HdbServices.getData(insertID);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		loop++;
		}while(loop <=5 );
			
			return textanonym;
	  }
 
	@RequestMapping(value = "/gettextanonym"+"/{ID}", method = RequestMethod.GET)
	
	public List<TextAnonym > getText( @PathVariable(value="ID") Integer id ) throws SQLException
	  {
		ArrayList<TextAnonym> textanonym = new ArrayList<TextAnonym>();
		textanonym= io.poc.text.anym.dbservices.HdbServices.getData(id);
		int loop = 0;
	do{
		if (textanonym.isEmpty() ){
	try {		
	        Thread.sleep(2000);
			textanonym= io.poc.text.anym.dbservices.HdbServices.getData(id);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		loop++;
		}while(loop <=5 );
			
			
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

@RequestMapping(value = "/postdictionary"+"/{textDict}", method = RequestMethod.POST)

public String postDictionary(@PathVariable(value="textDict") String textDict ) throws SQLException
  {
	int sucess = 1;
	String result;
	sucess = io.poc.text.anym.dbservices.HdbServices.writeDictionary(textDict);
	if(sucess == 0)
	  result =  "Dictionary Changes are Sucessfull";
	else
      result =  "Dictionary Changes are not Sucessfull";
	
	return result;
  }

@RequestMapping(value = "/posttextrule"+"/{textRule}", method = RequestMethod.POST)

public String postTextRule(@PathVariable(value="textRule") String textRule ) throws SQLException
  {
	int sucess = 1;
	String result;
	String setRule = "<";
	int ruleStrLen = textRule.length();
	int i = 0;
	//char ruleaArray[]= textRule.toCharArray();
    for( ; i <= ruleStrLen; i++)
    {
    if ((Character.isDigit(textRule.charAt(i)) == true)) {
    	setRule = setRule + "[A-Z]|[a-z]";
    } else if ((Character.isAlphabetic(textRule.charAt(i)) == true)) {
    	setRule = setRule + "[0-9]";
    } else {
    	setRule = setRule + textRule.charAt(i) ;
    }
    };
    setRule = setRule + "{" +"ruleStrLen" + "," + "ruleStrLen"+ "}"+ ">";
	sucess = io.poc.text.anym.dbservices.HdbServices.writeTextRule(textRule);
	if(sucess == 0)
		result =  "Text Rule Changes are Sucessfull";
	else
	    result =  "Text Rule Changes are not Sucessfull";
		
		return result;
  }

}
