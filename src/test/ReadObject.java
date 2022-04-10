package test;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
public class ReadObject {
    Properties p = new Properties();
    String a;
	public String getProperty(String string) throws FileNotFoundException {
		// Load the properties File
		Properties obj = new Properties();
		FileInputStream objfile = new FileInputStream(System.getProperty("user.dir") + "/src/userdata/application.properties");
		try {
			obj.load(objfile);
			 a=obj.get(string).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
    
}