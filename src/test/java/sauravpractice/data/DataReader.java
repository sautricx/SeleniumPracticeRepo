package sauravpractice.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException
	{
			//Convert json to strijng using readFileToString()
			String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+ "C:\\Study\\Eclipse\\seleniumFrameworkDesign\\src\\test\\java\\sauravpractice\\data"), StandardCharsets.UTF_8);
			
			//String to Hashmap - jackson Databind
			ObjectMapper mapper = new ObjectMapper();
			List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {});
			return data;
	}
	//This piece of code is common sp it has been moved to Base test.

}
