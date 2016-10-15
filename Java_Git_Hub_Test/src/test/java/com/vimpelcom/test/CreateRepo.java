package com.vimpelcom.test;

import static com.jayway.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;

import com.vimpelcom.common.BaseConfiguration;
import com.vimpelcom.common.RepoPresentCheck;
import com.vimpelcom.exception.GitExampleException;

/**
 * This class tests the Create Repository API.
 * @author Rabindra Nath Gogoi
 *
 */
public class CreateRepo extends Suite_Git_Repo {
	
	@BeforeTest
	public void beforeCreateRepo()
	{
		LOGGER.info("All the requirements before Creating Repository in Git is fulfilled..........");
	}
	
	@Test
	public void createRepoTest() throws GitExampleException
	{
		LOGGER.info("Checking if Repository name already exists in Git.......");
		RepoPresentCheck repoCheck = new RepoPresentCheck();
		String result = repoCheck.checkRepoPresent();
		
		if(result.equals("N"))
		{
			LOGGER.info("Repository Name does not exists in Git .......");
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = null;
			try {
				jsonObject = (JSONObject) jsonParser.parse(BaseConfiguration.getCreateJson());
			} catch (FileNotFoundException e) {
				throw new GitExampleException("Request Create Json File is not Found.....");
			} catch (IOException e) {
				throw new GitExampleException("Error in reading the Create Json File.....");
			} catch (ParseException e) {
				throw new GitExampleException("Error in parsing the response List Json..... ");
			}
			LOGGER.info("Creating Repository in Git..........");
			
			given().header("Authorization",
					BaseConfiguration.getProperties().getProperty("tokenName"))
					.contentType("application/json")
					.request()
					.body(jsonObject.toString())
					.expect()
					.statusCode(201)
					.when()
					.post("https://api.github.com/user/repos");
			
			LOGGER.info("Repository got created successfully.......");
		}
		else
		{
		 LOGGER.info("The Repository Name already exists in Git..........");
		}
	}
	
	@AfterTest
	public void afterCreateTest()
	{
	}

}
