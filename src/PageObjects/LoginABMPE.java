package PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginABMPE {

	public LoginABMPE(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(how = How.TAG_NAME, using = "input")
	private List<WebElement> txtBox;	
	
	@FindBy(how = How.TAG_NAME, using = "button")
	private List<WebElement> btnIngresar;
	
	public static final String btnLoginCSS = ".btn.btn-sm.btn-primary";	
	
	public void LoginUser(String user,String pwd) throws InterruptedException{
		
		for(int x=0;x<txtBox.size();x++){
			if(txtBox.get(x).getAttribute("data-ng-model").contentEquals("usuario")){
				txtBox.get(x).sendKeys(user);
				Thread.sleep(1000);
				break;
			}
		}
		
		for(int y=0;y<txtBox.size();y++){
			if(txtBox.get(y).getAttribute("data-ng-model").contentEquals("password")){
				txtBox.get(y).sendKeys(pwd);
				Thread.sleep(1000);
				break;
			}
		}		
		
		for(int z=0;z<btnIngresar.size();z++){
			if(btnIngresar.get(z).getAttribute("type").contentEquals("submit")){
				btnIngresar.get(z).click();
				Thread.sleep(1000);
				break;
			}
		}		
		
	}	
	
}
