package com.vimpelcom.test;

import static com.jayway.restassured.RestAssured.given;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.vimpelcom.common.BaseConfiguration;
import com.vimpelcom.common.RepoPresentCheck;
import com.vimpelcom.exception.GitExampleException;

/**
 * This class tests the Delete Repository API.
 * @author user
 *
 */
public class DeleteRepo extends Suite_Git_Repo{
	
	@BeforeTest
	public void beforeDeleteRepo()
	{
		LOGGER.info("All the requirements before Deleting Repository in Git is fulfilled..........");
	}
	
	@Test
	public void deleteRepoTest() throws GitExampleException
	{
		LOGGER.info("Checking if Repository Name already exists in Git.......");
		RepoPresentCheck repoCheck = new RepoPresentCheck();
		String result = repoCheck.checkRepoPresent();
		if(result.equals("Y"))
		{
	    LOGGER.info("Repository Name exists in Git.......");
		String repoName = BaseConfiguration.getProperties().getProperty("repoToDelete");
			String username = BaseConfiguration.getProperties().getProperty(
					"username"); 	
		LOGGER.info("Deleting Repository in Git..........");
			 given().header("Authorization",
					BaseConfiguration.getProperties().getProperty("tokenName"))
					.expect()
					.statusCode(204)
					.when()
					.delete(" https://api.github.com/repos/" + username + "/" + repoName);
			
			
			LOGGER.info("Repository got deleted successfully.......");
		}
		else{
		LOGGER.info("The Repository Name does not exists in Git..........");
		}
	}
	
	@AfterTest
	public void afterDeleteRepo()
	{
	}
}
