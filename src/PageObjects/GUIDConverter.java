package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class GUIDConverter {

	public GUIDConverter(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.TAG_NAME, using = "input")
	private List<WebElement> txtBox;
	
	@FindBy(how = How.CSS, using = ".even_row>td")
	private List<WebElement> rowText;
	
	public static final String titleCSS = ".title";
			
	
	public void IngresarToken (String token) {
		
		for(int x=0;x<txtBox.size();x++){
			if(txtBox.get(x).getAttribute("name").contentEquals("supplied")){
				txtBox.get(x).sendKeys(token);
				break;
			}
		}		
	}
	
	public void ClickOnConvert () {
		
		for(int x=0;x<txtBox.size();x++){
			if(txtBox.get(x).getAttribute("name").contentEquals("convert")){
				txtBox.get(x).click();;
				break;
			}
		}		
	}
	
	public String GetRawCode() {
		String raw = "";
		for(int x=0;x<rowText.size();x++){
			if(rowText.get(x).getText().contains("RAW(16)")){
				List<WebElement> values = rowText.get(x).findElements(By.tagName("td"));
				for(int y=0;y<values.size();y++){
					if(y==1){
						raw = values.get(y).getText();																					
						break;
					}						
				}
				break;	
			}
		}
		return raw;
	}
	
}
