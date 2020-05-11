package com.automation.selenium;

import org.junit.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class HtmlDriverTest {
	@Test
	public void test() {
		WebDriver driver = new HtmlUnitDriver();
		driver.get("https://www.bilibili.com/");
		System.out.println(driver.getPageSource());
	}
}
