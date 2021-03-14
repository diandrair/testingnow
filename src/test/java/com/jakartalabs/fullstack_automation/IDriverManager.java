package com.jakartalabs.fullstack_automation;

import java.io.IOException;

import org.testng.ITestResult;

public interface IDriverManager {
	public void setUpSystemUnderTest();
	public void cleanUp(ITestResult result) throws IOException;
}
