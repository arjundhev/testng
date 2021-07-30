package org.ng;


import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SampleTest extends LibGlobal {
	
	
	LibGlobal glo=new LibGlobal();
	
	
	
	@BeforeClass
	public void beforeClass() {
    glo.getdriver("chrome");
	glo.launchUrl("https://adactinhotelapp.com/");
	
		
	}

	@AfterClass
	public void afterClass() {
		System.out.println("After class");

	}
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Before Every@Test");
	}
	@AfterMethod
	public void afterMethod() {
		System.out.println("After Every@Test");

	}
	@Parameters({"username"})
	@Test
	public void tc0(String s1) {
		WebElement txtUserName = glo.findById("xpath", "//input[@id='username']");
		glo.typeText(txtUserName,s1);
	}
}
