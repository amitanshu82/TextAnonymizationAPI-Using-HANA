package io.poc.text.anym.app.component;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class TextAnonymizationApiComponent {



	    @Value("classpath:static/query.txt")
	    private static Resource res;

	    @Autowired
	    private static ResourceLoader resourceLoader;

	    public static String getClause(String... args) throws Exception {

	       Resource fileResource = resourceLoader.getResource("classpath:static/query.txt");        
            String whereClause = "" ;
	        List<String> lines = Files.readAllLines(Paths.get(fileResource.getURI()), StandardCharsets.UTF_8);

	        for (String line : lines) {


	        	whereClause = whereClause + line;

	        }
			return whereClause;
	    }
	    
	    public static void setClause(String... args) throws Exception {

		       // Resource fileResource = resourceLoader.getResource("classpath:thermopylae.txt");        
	            String whereClause = "" ;
				@SuppressWarnings("unused")
				String setClause = "";
		        List<String> lines = Files.readAllLines(Paths.get(res.getURI()),
		                StandardCharsets.UTF_8);

		        for (String line : lines) {


		        	whereClause = whereClause + line;

		        }	
		       setClause = whereClause + args; 
		       
		    }
	}

