package com.vimpelcom.common;

import static com.jayway.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jayway.restassured.response.Response;
import com.vimpelcom.exception.GitExampleException;

public class RepoPresentCheck {

	// Method which check the repository was already created.
	public String checkRepoPresent() throws GitExampleException {
		try {
			String username = BaseConfiguration.getProperties().getProperty(
					"username");
			JSONArray jsonArray = null;
			JSONParser listParser = new JSONParser();
			ArrayList<String> repolist = new ArrayList<>();
			Response res = given()
					.header("Authorization",
							BaseConfiguration.getProperties().getProperty(
									"tokenName")).when()
					.get("https://api.github.com/users/" + username + "/repos");

			jsonArray = (JSONArray) listParser.parse(res.asString());

			Iterator i = jsonArray.iterator();
			while (i.hasNext()) {
				JSONObject innerObj = (JSONObject) i.next();
				String[] repoNames = innerObj.get("full_name").toString()
						.split("/");
				repolist.add(repoNames[1]);
			}
			JSONParser jparser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jparser
					.parse(BaseConfiguration.getCreateJson());
			String repoName = (String) jsonObject.get("name");

			if (repolist.contains(repoName)) {
				return "Y";
			} else {
				return "N";
			}
		} catch (ParseException e) {
			throw new GitExampleException(
					"Error in parsing the response List Json..... ");
		} catch (FileNotFoundException e) {
			throw new GitExampleException(
					"Request Create Json File is not Found.....");
		} catch (IOException e) {
			throw new GitExampleException(
					"Error in reading the Create Json File.....");
		}

	}

}
