package io.poc.text.anym.dbservices;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
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
       
       connection = ds.getConnection();
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

public static ArrayList<TextAnonym> getData( int id){
	// TODO Method to get data from $TA Index table	
 Connection connection = null;
 ArrayList<TextAnonym> textanonym = new ArrayList<TextAnonym>();
 String queryLabel = null ;

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
       String getQueryLabel = "SELECT * FROM \"DLP\".\"TestHana.HDBModule::queryTable\"";
       ResultSet resultSetQuery = stmt.executeQuery(getQueryLabel);
       if (resultSetQuery != null){
    	   while(resultSetQuery.next()){
    		 String label =  resultSetQuery.getNString("LABEL"); 
    		 if (label != null) {
    		 queryLabel = queryLabel + "," + "'" + label + "'";
    		 }
    	   }
    	   resultSetQuery.close();
       }
       queryLabel = queryLabel.replaceAll("null", "");
	   String sqlquery = "SELECT * FROM \"DLP\".\"$TA_TestHana.HDBModule::EXT_Core.hdbfulltextindex\" where ID = " + id +"  AND TA_TYPE IN ( 'PERSON', 'COUNTRY', 'EMPLOYEEID','URI/EMAIL', 'URI/URL', 'ORGANIZATION/COMMERCIAL', 'CURRENCY',  'EMPLOYEE_ID' , 'CREDIT_CARD/AMERICAN_EXPRESS' , 'CREDIT_CARD/MASTER_CARD' , 'CREDIT_CARD/VISA_CARD' , 'PAN_NO' , 'YEAR' , 'DATE'  "+queryLabel+" )  ";
	   System.out.println("Query that is fired "+sqlquery);
	   ResultSet resultSetIndex = stmt.executeQuery(sqlquery);
	   if (resultSetIndex != null){
			while(resultSetIndex.next()){
			    TextAnonym txtanym = new TextAnonym();
			    txtanym.setId(resultSetIndex.getInt("ID")); 
			    txtanym.setTa_token(resultSetIndex.getNString("TA_TOKEN"));
			    txtanym.setTa_type(resultSetIndex.getString("TA_TYPE"));
			    textanonym.add(txtanym);
		}
			resultSetIndex.close();
			stmt.close();
	   }
}
	catch(Exception e) {
	 }finally {
		    if (connection != null) {
		        try {
		        	connection.close();
		        } catch (SQLException e) {}
		    }
		}
	return  textanonym;
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

public static int getMaxId(String tableName) {
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
 sqlquery = "SELECT MAX(ID) FROM \"DLP\".\"TestHana.HDBModule::"+tableName+"\" " ;
   System.out.println("Query that is fired "+sqlquery);
   ResultSet rs=stmt.executeQuery(sqlquery);          
   //Extract result from ResultSet rs
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

public static int writeDictionary(String textDict) {
	// TODO Write value in HANA DB Dictionary 
	int result = 0;
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
	CallableStatement cStmt = connection.prepareCall("{CALL TEXT_CONFIGURATION_CREATE(?, ?, 'hdbtextdict', ?)}");
	cStmt.executeUpdate();
	cStmt = connection.prepareCall("{CALL TEXT_CONFIGURATION_CLEAR( )}");
	result = cStmt.executeUpdate();
	cStmt.close();
	}
	catch(Exception e) {
	}finally {
	     if (connection != null) {
	         try {
	         	connection.close();
	         } catch (SQLException e) {}
	     }
	}
	
	
	return result;
}

@SuppressWarnings("resource")
public static int writeTextRule(String textRule,String textLabel) {
	// TODO Insert new rule in Rule Set of HANA DB 
	Connection connection = null;
	int success = 1, ruleExist = 0;
	CallableStatement cStmt;
	String includeData = null , oldRuleSet = null, newRuleSet = null;
	String includeRule = "#include \"TestHana.HDBModule::add_rule.hdbtextinclude\"";
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
	//CallableStatement cStmt = connection.prepareCall("{CALL TEXT_CONFIGURATION_CREATE('DLP', 'TestHana.HDBModule::Word_Rules', 'hdbtextrule', "+textRule+")}");
	//success = cStmt.executeUpdate();
	// Get Data from SYS table for all the value in include file
	try {
		Statement stmt = connection.createStatement();
	    String setQueryInclude = "select * from \"SYS\".\"TEXT_CONFIGURATIONS_\"; " ;
	    ResultSet rsInclude=stmt.executeQuery(setQueryInclude);          
	    //Extract result from ResultSet rsInclude
	    while(rsInclude.next()){
//	        String name = rsInclude.getString("name");// == "TestHana.HDBModule::add_rules"); 
	        if (rsInclude.getString("name").equals("TestHana.HDBModule::add_rule"))
	        {	
	        includeData = rsInclude.getString("DATA");
	        }
	      }
	    rsInclude.close();
	    stmt.close();
	}catch(Exception e) {
	}
	//Preparing for procedure call for update of include and Rule set
	String[] arrayRuleSet = includeData.split("\n"); 
	for (int i=0; i < arrayRuleSet.length; i++)
    {
		if ( i == 0  ) 
		oldRuleSet = arrayRuleSet[i] ;
		else
			oldRuleSet = oldRuleSet +"\n"+ arrayRuleSet[i] ;
		if (arrayRuleSet[i].equals(textRule))
		{
			ruleExist = 1;
		}
    }
	if (ruleExist == 0)
	{

	newRuleSet = oldRuleSet + "\n"+textRule;
//Start of insertion of new Rule to DB	
	try {
	   success = 1;
	   cStmt = connection.prepareCall("{CALL TEXT_CONFIGURATION_CREATE('DLP', 'TestHana.HDBModule::add_rule', 'hdbtextinclude', '"+newRuleSet+"')}");//"+textRule+"
	   cStmt.executeUpdate();
	   success = 0;
	    }catch(Exception e) {
	  } 
	if(success == 0){
		try {
			success = 1;
			cStmt = connection.prepareCall("{CALL TEXT_CONFIGURATION_CREATE('DLP', 'TestHana.HDBModule::Word_Rules', 'hdbtextrule', '"+includeRule+"')}");
			cStmt.executeUpdate();
			success = 0;
			}catch(Exception e) {
			} 
	}
	
	if (success == 0){
		try {
			success = 1;
			cStmt = connection.prepareCall("{CALL TEXT_CONFIGURATION_CLEAR('DLP', 'TestHana.HDBModule::add_rule','hdbtextinclude' ) }");
			cStmt.executeUpdate();
			cStmt.close();
			success = 0;
			}catch(Exception e) {
			} 
	}
	
		if (success == 0){
		 int maxid = io.poc.text.anym.dbservices.HdbServices.getMaxId("queryTable");
		 int inputID = maxid+1;
		 Statement stmt = connection.createStatement();
	     String setQueryLabel = "insert into \"TestHana.HDBModule::queryTable\" values (" + inputID +","+ "'"+textLabel+"'" +");" ;
	     System.out.println("Query that is fired "+setQueryLabel);
		   int rows = stmt.executeUpdate(setQueryLabel);
		   if(rows != 0) {
			   connection.commit();  
		   }else {
			    cStmt = connection.prepareCall("{CALL TEXT_CONFIGURATION_CREATE('DLP', 'TestHana.HDBModule::add_rule', 'hdbtextinclude', '"+oldRuleSet+"')}");//"+textRule+"
				 cStmt.executeUpdate(); 
				 cStmt = connection.prepareCall("{CALL TEXT_CONFIGURATION_CREATE('DLP', 'TestHana.HDBModule::Word_Rules', 'hdbtextrule', '"+includeRule+"')}");
				 cStmt.executeUpdate();
				 cStmt = connection.prepareCall("{CALL TEXT_CONFIGURATION_CLEAR('DLP', 'TestHana.HDBModule::add_rule','hdbtextinclude' ) }");
			     cStmt.executeUpdate();
				 cStmt.close();
		   }
	 }else {
		 cStmt = connection.prepareCall("{CALL TEXT_CONFIGURATION_CREATE('DLP', 'TestHana.HDBModule::add_rule', 'hdbtextinclude', '"+oldRuleSet+"')}");//"+textRule+"
		 cStmt.executeUpdate(); 
		 cStmt = connection.prepareCall("{CALL TEXT_CONFIGURATION_CREATE('DLP', 'TestHana.HDBModule::Word_Rules', 'hdbtextrule', '"+includeRule+"')}");
		 cStmt.executeUpdate();
		 cStmt = connection.prepareCall("{CALL TEXT_CONFIGURATION_CLEAR('DLP', 'TestHana.HDBModule::add_rule','hdbtextinclude' ) }");
	     cStmt.executeUpdate();
		 cStmt.close();
	 }
	
	} else success = 2;  
	
	}
	catch(Exception e) {
	}finally {
		 if (success == 1) {
			 try {
			 cStmt = connection.prepareCall("{CALL TEXT_CONFIGURATION_CREATE('DLP', 'TestHana.HDBModule::add_rule', 'hdbtextinclude', '"+oldRuleSet+"')}");//"+textRule+"
			 cStmt.executeUpdate();  
			 cStmt = connection.prepareCall("{CALL TEXT_CONFIGURATION_CREATE('DLP', 'TestHana.HDBModule::Word_Rules', 'hdbtextrule', '"+includeRule+"')}");
			 cStmt.executeUpdate();
			 cStmt = connection.prepareCall("{CALL TEXT_CONFIGURATION_CLEAR('DLP', 'TestHana.HDBModule::add_rule','hdbtexinclude' ) }");
		     cStmt.executeUpdate();
		     cStmt.close();
		 }catch (SQLException e) {}
			 
		 }
		 
	     if (connection != null) {
	         try {	
	        	 connection.close();
	         } catch (SQLException e) {}
	     }
	}
	return success;
}

//Get Labels from queryTable

public static ArrayList<String> getLabels(){
	// TODO Method to get data from $TA Index table	
 Connection connection = null;
 ArrayList<String> Label = new ArrayList<String>();
 
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
       String getQueryLabel = "SELECT * FROM \"DLP\".\"TestHana.HDBModule::queryTable\"";
       ResultSet resultSetQuery = stmt.executeQuery(getQueryLabel);
       if (resultSetQuery != null){
    	   while(resultSetQuery.next()){
    		 String label =  resultSetQuery.getNString("LABEL"); 
    		 Label.add(label);
    	   }
    	   resultSetQuery.close();
       }
	  
}
	catch(Exception e) {
	 }finally {
		    if (connection != null) {
		        try {
		        	connection.close();
		        } catch (SQLException e) {}
		    }
		}
	return  Label;
}

}


