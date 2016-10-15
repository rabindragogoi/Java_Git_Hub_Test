package com.vimpelcom.test;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.vimpelcom.common.BaseConfiguration;

/**
 * Suite Class for the Automation test.
 * 
 * @author user
 *
 */
public class Suite_Git_Repo extends BaseConfiguration {

	@BeforeSuite
	public void beforeSuite() {
		BaseConfiguration.initialize();
		LOGGER.info("Suite Class got executed.....");
	}

	@AfterSuite
	public void afterSuite() {

	}
}
