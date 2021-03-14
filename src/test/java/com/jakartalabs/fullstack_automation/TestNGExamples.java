package com.jakartalabs.fullstack_automation;

import org.testng.annotations.Test;

import com.jakartalabs.baseui.BaseTest;

public class TestNGExamples extends BaseTest {
	@Test(description = "Says BYE", priority = 2)
	public void test1() {
		System.out.println("BYE");
	}
	
	@Test(groups = {"Smoke", "Regression"}, description = "Says hello", priority = 1)
	public void test2() {
		System.out.println("HELLO1");
	}
	
	@Test(groups = "Smoke", description = "Says hello", priority = 1)
	public void test2a() {
		System.out.println("HELLO2a");
	}
	
	@Test(groups = "Smoke", description = "Says hello", priority = 1)
	public void test2b() {
		System.out.println("HELLO2b");
	}
}
