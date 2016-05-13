package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PortalTools {

	public PortalTools(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="menu")
	public WebElement menu;	
	
	public static final String btnAmbientesId = "ddlAmbientes-button"; 
	@FindBy(id=btnAmbientesId)
	public WebElement btnAmbientes;
	
	@FindBy(id="ddlAmbientes-menu")
	public WebElement cboAmbientes;
	
	@FindBy(id="ddlAplicaciones-button")
	public WebElement btnAplicaciones;
	
	@FindBy(id="ddlAplicaciones-menu")
	public WebElement cboAplicaciones;
	
	@FindBy(id="btnExecute")
	public WebElement btnEjecutar;
	
	@FindBy(id="Parametros")
	public WebElement txtParametros;
	
	public void ClickOnMenu(String menuText){
		WebElement list = this.menu;
		List<WebElement> items = list.findElements(By.tagName("li"));
		for (int i=0;i<items.size();i++){
			if(items.get(i).getText().toLowerCase().contains(menuText.toLowerCase())){
				items.get(i).click();
				break;
			}
		}
	}
	
	public void ClickOnAmbiente(String ambiente) throws InterruptedException{
		btnAmbientes.click();
		Thread.sleep(1000);	
		WebElement list = this.cboAmbientes;
		List<WebElement> items = list.findElements(By.tagName("li"));
		for (int i=0;i<items.size();i++){
			if(items.get(i).getText().toLowerCase().contains(ambiente.toLowerCase())){
				items.get(i).click();
				break;
			}
		}
	}
	
	public void ClickOnAplicacion(String aplicacion) throws InterruptedException{
		btnAplicaciones.click();
		Thread.sleep(1000);	
		WebElement list = this.cboAplicaciones;
		List<WebElement> items = list.findElements(By.tagName("li"));
		for (int i=0;i<items.size();i++){
			if(items.get(i).getText().toLowerCase().contains(aplicacion.toLowerCase())){
				items.get(i).click();
				break;
			}
		}
	}
	
	public void ClickOnExecute(){
		btnEjecutar.click();
	}
	
	public void IngresarParametros(String parametro){
		txtParametros.sendKeys(parametro);
	}
	
}
