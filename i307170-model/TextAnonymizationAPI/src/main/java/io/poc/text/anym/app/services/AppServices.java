package io.poc.text.anym.app.services;

public class AppServices {
	public static String convertTextToRule(String textLabel, String textRule){
		 String setRule = "#group "+ textLabel + ": <";
		 int ruleStrLen = textRule.length();
			int i = 0;
		 int digit_counter = 0 , char_counter = 0;	
		 String is_char = null , is_digit = null;
			//char ruleaArray[]= textRule.toCharArray();
		 for( ; i < ruleStrLen; i++)
		 {
		 if ((Character.isDigit(textRule.charAt(i)) == true)) {
		 // setRule = setRule + "[0-9]";
		 	if ( is_char != "X" )  {
		 			if (digit_counter == 0)
		 			{
		 		    setRule = setRule + "[0-9]";
		 		   digit_counter = 1;
		 			}else
		 			digit_counter += 1;
		 			is_digit = "X";
		 	}
		 			else{
		 			setRule = setRule +"{"+char_counter+"}" +"[0-9]";  
		 			is_digit = "X";
		 			is_char = null;
		 			digit_counter = 1;
		 			char_counter = 0;
		 			}
		 } else if ((Character.isAlphabetic(textRule.charAt(i)) == true)) {
		 //	setRule = setRule + "[A-Z]|[a-z]";
		 	if ( is_digit != "X")  {
		 		if (char_counter == 0){	
		 		setRule = setRule + "[A-Za-z]" ;
		 		char_counter = 1;
		 		}else
		 			char_counter += 1;
		 			is_char = "X";
		 	}
		 			else{
		 				setRule = setRule +"{" +digit_counter+"}"+ "[A-Za-z]";
		 			is_char = "X";
		 			is_digit = null;
		 			char_counter = 1;
		 			digit_counter = 0;
		 			}
		 }else if ((Character.isWhitespace(textRule.charAt(i)) == true)) {
			 //	setRule = setRule + "[A-Z]|[a-z]";
			 if ( is_digit == "X" ){
			 		setRule = setRule  +"{" +digit_counter+"}"+ ">"+ " " + "<" ;
			       is_digit = null;
			       digit_counter = 0;
			 }
			 else{
				 setRule = setRule  +"{" +char_counter+"}"+ ">"+ " " + "<" ;
				 is_char = null;
				 char_counter = 0;
			 }
			 }
		 else {
			 if ( is_digit == "X" ){
			 		setRule = setRule +"{" +digit_counter+"}"+ ">"+ "<"+ "\\" + textRule.charAt(i)+ ">"+ "<" ;
			       is_digit = null;
			       digit_counter = 0;
			 }
			 else{
				 setRule = setRule  +"{" +char_counter+"}"+ ">"+ "<"+ "\\" + textRule.charAt(i)+ ">"+ "<" ;
				 is_char = null;
				 char_counter = 0;
			 }
		 }
		 };
		 if ( digit_counter !=0)
		    setRule = setRule + "{" + digit_counter + "}"+ ">";
		 else
			 setRule = setRule + "{" + char_counter + "}"+ ">"; 
		 setRule = "'"+setRule+"'";
		 return setRule;
		 }
}
