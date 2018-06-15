package io.poc.text.anym.app.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.poc.text.anym.app.entity.TextAnonym;
import io.poc.text.anym.app.entity.TextInput;



@RestController
public class TextAnonymizationApiAppController {
	
	static String hanaHost = "localhost";
	static int hanaPort = 30015;
	static String hanaUser = "SBSS_49981159700204747010855902398847012696392672220285900501933237456";
	static String hanaPassword = "Wk7A8QQiB6cB3XLHDo_.C-agqyeI15HKdYtZm0tKOd55BI4fIel_iQgvpiTlUXVq7xDBlQwBqMgPcsb4CwO1NbCse-DBRPTEqmPARVlg8EKv2Uouid.nhk9OZlFrF0Fo";
	static String hanaSchema = "DLP";
	static int queryResult = 0;
	static DataSource ds = null;
	static ResultSet resultSetData = null;

	static int maxID = 0;
	
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
		int strlen = text_input.length();
		int maxid = io.poc.text.anym.dbservices.HdbServices.getMaxId();
		int id = maxid + 1;
		ResultSet resultSet1 = null;
		Connection connection = null;
		if(strlen <= 5000)
		{
			textin.setId(maxid + 1);
		 	textin.setText(text_input);
		 	textinput.add(textin);	 	
		}
		
		   io.poc.text.anym.dbservices.HdbServices.insertData(textinput);

		try
		{		       
		       if (ds == null){
		          ds = io.poc.text.anym.dbservices.HANADataSourceCreator.createHanaDataSource(hanaHost, hanaPort, hanaUser, hanaPassword, hanaSchema);
		          if (ds != null)
		          System.out.println("Data Source Created for CF DB connection.");
		          
		          else
		          System.out.println("Data Source not Created for CF DB connection.");
		        }
		       connection = ds.getConnection();
		       if(connection != null)
		         System.out.println("Connection to DB successful...");
		       else System.out.println("Connection to DB is not successful...");		 
		       Statement stmt = connection.createStatement();
			   String sqlquery = "SELECT * FROM \"DLP\".\"$TA_TestHana.HDBModule::EXT_Core.hdbfulltextindex\" where ID = " + id +"  AND TA_TYPE IN ( 'PERSON', 'COUNTRY', 'EMPLOYEE_ID','URI/EMAIL', 'URI/URL', 'ORGANIZATION', 'CURRENCY', 'PHONE' )  ";
			   System.out.println("Query that is fired "+sqlquery);
			   resultSet1 = stmt.executeQuery(sqlquery);
			   stmt.close();
			   
			}
			catch(Exception e) {
			 }finally {
		            if (connection != null) {
		                try {
		                	connection.close();
		                } catch (SQLException e) {}
		            }
		     }
		    try {
		    if (resultSet1 != null){
				while(resultSet1.next()){
				    TextAnonym txtanym = new TextAnonym();
				    txtanym.setTa_token(resultSet1.getNString("TA_TOKEN"));
				    txtanym.setTa_type(resultSet1.getString("TA_TYPE"));
				    textanonym.add(txtanym);
				}
				
					resultSet1.close();
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
		ResultSet resultSet1 = null;
		Connection connection = null;
		try
		{		       
		       if (ds == null){
		          ds = io.poc.text.anym.dbservices.HANADataSourceCreator.createHanaDataSource(hanaHost, hanaPort, hanaUser, hanaPassword, hanaSchema);
		          if (ds != null)
		          System.out.println("Data Source Created for CF DB connection.");
		          
		          else
		          System.out.println("Data Source not Created for CF DB connection.");
		        }
		       connection = ds.getConnection();
		       if(connection != null)
		         System.out.println("Connection to DB successful...");
		       else System.out.println("Connection to DB is not successful...");		 
		       Statement stmt = connection.createStatement();
			   String sqlquery = "SELECT * FROM \"DLP\".\"$TA_TestHana.HDBModule::EXT_Core.hdbfulltextindex\" where ID = " + id +"  AND TA_TYPE IN ( 'PERSON', 'COUNTRY', 'EMPLOYEE_ID','URI/EMAIL', 'URI/URL', 'ORGANIZATION', 'CURRENCY', 'PHONE' )  ";
			   System.out.println("Query that is fired "+sqlquery);
			   resultSet1 = stmt.executeQuery(sqlquery);
			   stmt.close();
			   
			}
			catch(Exception e) {
			 }finally {
		            if (connection != null) {
		                try {
		                	connection.close();
		                } catch (SQLException e) {}
		            }
		     }
		    try {
		    if (resultSet1 != null){
				while(resultSet1.next()){
				    TextAnonym txtanym = new TextAnonym();
				    txtanym.setTa_token(resultSet1.getNString("TA_TOKEN"));
				    txtanym.setTa_type(resultSet1.getString("TA_TYPE"));
				    textanonym.add(txtanym);
				}
				
					resultSet1.close();
		    }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
