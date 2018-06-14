package io.poc.text.anym.dbservices;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import javax.sql.DataSource;

import io.poc.text.anym.app.entity.TextInput;

public  class HdbServices {
	
boolean connectingOk = false;

static String hanaHost = "10.253.92.211";
static int hanaPort = 30041;
static String hanaUser = "SBSS_16863260986036178563894128436529163757193751987775300413631998916";
static String hanaPassword = "Qq20iy9tT_ipeTOkny-b15LMk6L9qE-SnHlgAMS.gdMXw1TTtrvYiM-mYJAuLpfcxWKpu8d6uxyj2YF9BZOm6k6ri3h.Vam82Sf.Abuf9pTQOIpcjzKCPvgD3p1q3bML";
static String hanaSchema = "DLP";
static int queryResult = 0;
static DataSource ds = null;
static Connection connection = null;
static int maxID = 0;


public static int gettables()
{
	try
	 {	   
       if (ds == null){
           ds = io.poc.text.anym.dbservices.HANADataSourceCreator.createHanaDataSource(hanaHost, hanaPort, hanaUser, hanaPassword, hanaSchema);
           if (ds != null)
               System.out.println("Data Source Created for CF DB connection.");
               
           else
        	   System.out.println("Data Source not Created for CF DB connection.");
       }
       if (connection == null){
           connection = ds.getConnection();
           connection.setAutoCommit(true);
       }
       if(connection != null){
          System.out.println("Connection to DB successful...");
       }
	   Statement stmt = connection.createStatement();
	   String sqlquery = "SELECT * FROM \"DLP\".\"TestHana.HDBModule::inputTable\"";
	   System.out.println("Query that is fired "+sqlquery);
	   ResultSet resultSet1 = stmt.executeQuery(sqlquery);
	   int queryResult1 = 0; 
	   while(resultSet1.next()){
	    queryResult1 = queryResult1 + 1 ;
	   }
	   queryResult = queryResult1;
	   System.out.println("Records in input table  '"+queryResult+"'");
	   resultSet1.close();
	   stmt.close();
	 }
	 catch(Exception e) {
		 System.out.println("Printing stack trace : ");
		 e.printStackTrace();
     }
	return queryResult;
}

public static ResultSet getData( int id)
{
ResultSet resultSet = null;
ResultSet resultSet1 = null;
try
{
       
       if (ds == null){
          ds = io.poc.text.anym.dbservices.HANADataSourceCreator.createHanaDataSource(hanaHost, hanaPort, hanaUser, hanaPassword, hanaSchema);
          if (ds != null)
          System.out.println("Data Source Created for CF DB connection.");
          
          else
          System.out.println("Data Source not Created for CF DB connection.");
        }
       if (connection == null){
           connection = ds.getConnection();
        }
       if(connection != null){
          System.out.println("Connection to DB successful...");
       }
	 
       Statement stmt = connection.createStatement();
	   String sqlquery = "SELECT * FROM \"$TA_TestHana.HDBModule::EXT_Core.hdbfulltextindex\" where ID = " + id +"  AND TA_TYPE IN ( 'PERSON', 'COUNTRY', 'EMPLOYEE_ID','URI/EMAIL', 'URI/URL', 'ORGANIZATION', 'CURRENCY', 'PHONE' )  ";
	   System.out.println("Query that is fired "+sqlquery);
	   resultSet1 = stmt.executeQuery(sqlquery);
	   resultSet = resultSet1;
	   resultSet1.close();
	   stmt.close();
	   
	}
	catch(Exception e) {
	 }
	return resultSet;
}

public static int insertData( ArrayList<TextInput> textinput) {
int rows = 0;
try
{
    String sqlquery = new String();
    if (ds == null){
    ds = io.poc.text.anym.dbservices.HANADataSourceCreator.createHanaDataSource(hanaHost, hanaPort, hanaUser, hanaPassword, hanaSchema);
    if (ds != null)
        System.out.println("Data Source Created for CF DB connection.");
    
     else
        System.out.println("Data Source not Created for CF DB connection.");
    }
    if (connection == null){
    connection = ds.getConnection();
    connection.setAutoCommit(true);
    }
    if(connection != null){
      System.out.println("Connection to DB successful...");
    }
	   Statement stmt = connection.createStatement();
	   Iterator<TextInput> iterator = textinput.iterator();
	   int inputID = 0;
	   String inputString = new String();
	   TextInput newTextInput = new TextInput();
	   while(iterator.hasNext())
	   {
		   newTextInput = iterator.next(); 
		   inputString = newTextInput.getText();
		   inputID = (int) newTextInput.getId();    
		   sqlquery = "insert into \"TestHana.HDBModule::inputTable\" values (" + inputID +","+ "'"+inputString+"'" +");" ;
		   System.out.println("Query that is fired "+sqlquery);
		   rows = stmt.executeUpdate(sqlquery);
	     
		  }
}		catch(Exception e) {
 
}
return rows ;
}

public static int getMaxId() {
// TODO Auto-generated method stub
try
{
    String sqlquery = new String();
if (ds == null){
    ds = io.poc.text.anym.dbservices.HANADataSourceCreator.createHanaDataSource(hanaHost, hanaPort, hanaUser, hanaPassword, hanaSchema);
    if (ds != null)
        System.out.println("Data Source Created for CF DB connection.");
    
     else
        System.out.println("Data Source not Created for CF DB connection.");
  }
 if (connection == null){
     connection = ds.getConnection();
     connection.setAutoCommit(true);
  }
 if(connection != null){
    System.out.println("Connection to DB successful...");
 }
 Statement stmt = connection.createStatement();
 sqlquery = "SELECT MAX(ID) FROM \"DLP\".\"TestHana.HDBModule::inputTable\" " ;
   System.out.println("Query that is fired "+sqlquery);
   ResultSet rs=stmt.executeQuery(sqlquery);          
   //Extact result from ResultSet rs
   while(rs.next()){
       System.out.println("MAX(ID)="+rs.getInt("MAX(ID)")); 
       maxID = rs.getInt("MAX(ID)");
     }
   rs.close();
  }catch(Exception e) {
	 
 }
return maxID;
}
}


