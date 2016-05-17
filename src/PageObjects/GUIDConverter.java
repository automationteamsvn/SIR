package PageObjects;

import java.util.List;

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
	
	public static final String rowTextCSS = ".even_row>td";
	@FindBy(how = How.CSS, using = rowTextCSS)
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
				raw = rowText.get(x+1).getText();				
				break;	
			}
		}
		return raw;
	}

}
