package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginTools {
	
	public LoginTools(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	public static final String txtUserId = "User";
	@FindBy(id=txtUserId)
	public WebElement txtUser;	
	
	public static final String txtPwdId = "Pass"; 
	@FindBy(id=txtPwdId)
	public WebElement txtPwd;
	
	@FindBy(css = ".button.ui-button.ui-widget.ui-state-default.ui-corner-all.ui-state-hover")
	public WebElement btnIngresar;	
	
	public void LoginUser(String user,String pwd) throws InterruptedException{
		txtUser.sendKeys(user);
		Thread.sleep(1000);
		txtPwd.sendKeys(pwd);
		Thread.sleep(1000);		
		btnIngresar.click();
	}

}
