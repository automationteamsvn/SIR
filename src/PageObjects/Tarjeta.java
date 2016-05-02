package PageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Tarjeta {

	public Tarjeta(WebDriver driver){
		PageFactory.initElements(driver, this);
		//localDriver = driver;
	}
	
	@FindBy(how = How.TAG_NAME, using = "input")
	private List<WebElement> txtBox;
	
	public void IngresarTitular (String titular) {		
		for(int x=0;x<txtBox.size();x++){
			if(txtBox.get(x).getAttribute("name").contentEquals("NOMBREENTARJETA")){
				txtBox.get(x).sendKeys(titular);
				break;
			}
		}		
	}
	
	public void IngresarNumTarjeta (String numTarjeta) {		
		for(int x=0;x<txtBox.size();x++){
			if(txtBox.get(x).getAttribute("name").contentEquals("NROTARJETA")){
				txtBox.get(x).sendKeys(numTarjeta);
				break;
			}
		}		
	}
	
	public void IngresarVencVisa (String vencTarjeta) {		
		for(int x=0;x<txtBox.size();x++){
			if(txtBox.get(x).getAttribute("name").contentEquals("VENCTARJETA")){
				txtBox.get(x).sendKeys(vencTarjeta);
				break;
			}
		}		
	}
	
	public void IngresarCodSeguridadVisa (String codigo) {		
		for(int x=0;x<txtBox.size();x++){
			if(txtBox.get(x).getAttribute("name").contentEquals("CODSEGURIDAD")){
				txtBox.get(x).sendKeys(codigo);
				break;
			}
		}		
	}

	public void IngresarEmail (String email) {		
		for(int x=0;x<txtBox.size();x++){
			if(txtBox.get(x).getAttribute("name").contentEquals("EMAILCLIENTE")){
				txtBox.get(x).sendKeys(email);
				break;
			}
		}		
	}	
	
	public void ClickOnAceptar () {		
		for(int x=0;x<txtBox.size();x++){
			if(txtBox.get(x).getAttribute("value").contentEquals("Aceptar")){
				txtBox.get(x).click();
				break;
			}
		}		
	}
		
}
