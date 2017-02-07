package test.poc.login;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import page.LogInPage;

public class TestCases {
	
	LogInPage login;
	static WebDriver driver;

	@BeforeClass
	public void init(){
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium rc\\chromedriver.exe");
		driver= new ChromeDriver();
		login= new LogInPage(driver);
	}

	@Test(priority=0)
public void logInTest() throws Exception{

	Properties obj= new Properties();
	InputStream fin= new FileInputStream(new File("C:\\Users\\DIGGANT\\NewWorkSpace\\Amazon\\src\\utilities\\ObjectRepository"));
	obj.load(fin);
	String url =obj.getProperty("URL");
	String user= obj.getProperty("UserName");
	String pwd= obj.getProperty("Password");
	Method m=LogInPage.class.getDeclaredMethod("signIn",String.class, String.class,String.class); 
    m.setAccessible(true);
try{
m.invoke(login,url,user,pwd);
}
	catch(Exception e){
	e.getCause().printStackTrace();
}
Assert.assertEquals(driver.getTitle(), obj.getProperty("Title"));
	driver.quit();
	}
	
	@Test(priority=1)
	public void passTest(){
		Assert.assertTrue(true);
	}
	@Test(priority=2)
	public void failTest(){
		Assert.assertFalse(true);
	}
	@Test(priority=3,dependsOnMethods="failTest")
	public void skippedTest(){
		Assert.assertTrue(true);
	}
}
