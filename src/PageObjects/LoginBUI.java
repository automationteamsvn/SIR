package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginBUI {
	
	public LoginBUI(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	public static final String txtUserId = "txtUserName";
	@FindBy(id=txtUserId)
	public WebElement txtUser;	
	
	public static final String txtPwdId = "txtPassword"; 
	@FindBy(id=txtPwdId)
	public WebElement txtPwd;
	
	@FindBy(xpath = "html/body/div[1]/div/div[2]/form/button")
	public WebElement btnIngresar;	
	
	public void LoginUser(String user,String pwd) throws InterruptedException{
		txtUser.sendKeys(user);
		Thread.sleep(1000);
		txtPwd.sendKeys(pwd);
		Thread.sleep(1000);		
		btnIngresar.click();
	}
	
}
