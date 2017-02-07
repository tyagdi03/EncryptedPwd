package page;

import java.util.Base64;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {
	
	WebDriver driver;
	@FindBy(xpath ="//*[@id='nav-link-yourAccount']/span[2]")
	WebElement hello_signIn_button;
	
	@FindBy(xpath ="//*[@id='nav-flyout-ya-signin']/a/span")
	WebElement signIn_button;
	
	@FindBy(xpath="//*[@id='ap_email']")
	WebElement userName;
	
	@FindBy(xpath ="//*[@id='ap_password']")
	WebElement password_box;
	
	@FindBy(xpath ="//*[@id='signInSubmit']")
	WebElement login_button;
	
	
	public LogInPage(WebDriver driver)
	{
		this.driver=driver;
		
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		
		PageFactory.initElements(driver, this);
	}
	
	private void signIn(String url, String user, String pwd)
	{
		
		driver.get(url);
		driver.manage().window().maximize();
		
		Actions builder= new Actions(driver);
		builder.moveToElement(hello_signIn_button);
		builder.perform();
		
		signIn_button.click();
		userName.sendKeys(user);
		
		String decryptedPassword;
		byte[] decryptedPwd = Base64.getDecoder().decode(pwd);
		decryptedPassword = new String(decryptedPwd);
		password_box.sendKeys(decryptedPassword);
		
        login_button.click();

		
	}

}
