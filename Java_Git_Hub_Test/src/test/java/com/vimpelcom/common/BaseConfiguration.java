package com.vimpelcom.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * This class initializes the propertyfile, instruction Json files etc. what
 * ever is needed to run the test.
 * 
 * @author Rabindra Nath Gogoi.
 *
 */
public class BaseConfiguration {

	public static Logger LOGGER = Logger.getLogger(BaseConfiguration.class);
	private static boolean isInitialized = false;
	private static Properties properties;

	public static void initialize() {
		try {
			if (!isInitialized) {
				PropertyConfigurator.configure(System.getProperty("user.dir")
						+ Constants.LOG_PROPERTY);

				properties = new Properties();
				FileInputStream propertyFile = new FileInputStream(
						System.getProperty("user.dir")
								+ Constants.GIT_Properties);
				properties.load(propertyFile);

				LOGGER.info("All Properties File Loaded Successfully.......");
			}
		} catch (Exception e) {

		}
		isInitialized = true;

	}

	public static FileReader getCreateJson() throws FileNotFoundException {
		FileReader createRepoJson = new FileReader(
				System.getProperty("user.dir") + Constants.CREATE_JSON_FILE);

		return createRepoJson;

	}

	public static Properties getProperties() {
		return properties;
	}

}
