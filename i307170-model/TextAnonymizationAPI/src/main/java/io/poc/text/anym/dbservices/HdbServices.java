package io.poc.text.anym.dbservices;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import io.poc.text.anym.app.entity.TextAnonym;
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
static int maxID = 0;


public static int getinputtables()
{
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
       
       connection = ds.getConnection();        connection = ds.getConnection();
        if(connection != null)
            System.out.println("Connection to DB successful...");
            else System.out.println("Connection to DB is not successful...");
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
	 }catch(Exception e) {
		 System.out.println("Printing stack trace : ");
		 e.printStackTrace();
     }finally {
            if (connection != null) {
                try {
                	connection.close();
                } catch (SQLException e) {}
            }
     }
	return queryResult;
}

public static int getindextables()
{
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
       
       connection = ds.getConnection();        connection = ds.getConnection();
        if(connection != null)
            System.out.println("Connection to DB successful...");
            else System.out.println("Connection to DB is not successful...");
	   Statement stmt = connection.createStatement();
	   String sqlquery = "SELECT * FROM \"DLP\".\"$TA_TestHana.HDBModule::EXT_Core.hdbfulltextindex\"";
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
	 }catch(Exception e) {
		 System.out.println("Printing stack trace : ");
		 e.printStackTrace();
     }finally {
            if (connection != null) {
                try {
                	connection.close();
                } catch (SQLException e) {}
            }
     }
	return queryResult;
}

public static int getindextables(int id)
{
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
       
       connection = ds.getConnection();        connection = ds.getConnection();
        if(connection != null)
            System.out.println("Connection to DB successful...");
            else System.out.println("Connection to DB is not successful...");
	   Statement stmt = connection.createStatement();
	   String sqlquery = "SELECT * FROM \"DLP\".\"$TA_TestHana.HDBModule::EXT_Core.hdbfulltextindex\" where ID = " + id +"";
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
	 }catch(Exception e) {
		 System.out.println("Printing stack trace : ");
		 e.printStackTrace();
     }finally {
            if (connection != null) {
                try {
                	connection.close();
                } catch (SQLException e) {}
            }
     }
	return queryResult;
}
// Method to get data from $TA Index table
public static List<TextAnonym > getData( int id)
{
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
    List<TextAnonym> textanonym = new ArrayList<TextAnonym>();
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


//Method to insert data in Input table
public static int insertData( ArrayList<TextInput> textinput) {
int rows = 0;
Connection connection = null;
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
    connection = ds.getConnection();
    if(connection != null)
      System.out.println("Connection to DB successful...");
      else System.out.println("Connection to DB is not successful...");

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
		   if(rows != 0) {
			   connection.commit();  
		   }
	     
		  }
}		catch(Exception e) {
 
}finally {
    if (connection != null) {
        try {
        	connection.close();
        } catch (SQLException e) {}
    }
}
return rows ;
}

public static int getMaxId() {
Connection connection = null;
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
connection = ds.getConnection();
if(connection != null)
  System.out.println("Connection to DB successful...");
  else System.out.println("Connection to DB is not successful...");
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
	 
 }finally {
     if (connection != null) {
         try {
         	connection.close();
         } catch (SQLException e) {}
     }
}
return maxID;
}
}


