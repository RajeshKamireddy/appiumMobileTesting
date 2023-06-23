package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {

	public String getProperty(String key) throws IOException {
		Properties prop = readPropertiesFile(
				"C://Users//DELL//eclipse-workspace//appiumMobileTesting//src//test//resources//Rajesh_Device1.properties");
		String value = prop.getProperty(key);
		return value;
	}

	public int getPropertyInt(String key) throws IOException {
		Properties prop = readPropertiesFile(
				"C://Users//DELL//eclipse-workspace//appiumMobileTesting//src//test//resources//Rajesh_Device1.properties");
		String s = prop.getProperty(key);
		int value = Integer.parseInt(s);
		return value;
	}

	public Properties readPropertiesFile(String fileName) throws IOException {
		FileInputStream fis = null;
		Properties prop = null;
		try {
			fis = new FileInputStream(fileName);
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			fis.close();
		}
		return prop;
	}
}