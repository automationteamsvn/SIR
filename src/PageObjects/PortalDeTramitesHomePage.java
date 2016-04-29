package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PortalDeTramitesHomePage {
	
	public PortalDeTramitesHomePage(WebDriver driver){
		PageFactory.initElements(driver, this);
		//GlocalDriver = driver;
	}
	
	//WebDriver localDriver;
	
	private static final String menuClass = "headerMenu";
	@FindBy(how = How.CLASS_NAME, using = menuClass)	
	private WebElement menu;
	
	public static final String txtDocumentoId = "Documento";
	@FindBy(id=txtDocumentoId)	
	private WebElement txtDocumento;	
	
	@FindBy(id="NroBoleta")	
	private WebElement txtBoleta;	
	
	@FindBy(id="CorreoElectronico")	
	private WebElement txtCorreo;	
	
	public static final String btnContinuarId = "btnContinuar";
	@FindBy(id=btnContinuarId)
	private WebElement btnContinuar;
	
	public static final String radMedioPagoId = "MedioPago";
	@FindBy(id=radMedioPagoId)
	private WebElement radMedioPago;

	public static final String cboCuotasCSS = ".k-icon.k-i-arrow-s";
	@FindBy(css=cboCuotasCSS)
	private WebElement cboCuotas;
	
	@FindBy(id="Cuotas_listbox")
	private WebElement itemCuotas;
	
	@FindBy(id="btnPago")
	private WebElement btnPago;
	
	public void ClickOnMenu(String menuName){
		WebElement list = this.menu;
		List<WebElement> items = list.findElements(By.tagName("li"));
		for (int i=0;i<items.size();i++){
			if(items.get(i).getText().toLowerCase().contentEquals(menuName.toLowerCase())){
				items.get(i).click();
				break;
			}
		}
	}			
	
	public void IngresarDocumento(String documento){
		txtDocumento.sendKeys(documento);
	}
	
	public void IngresarBoleta(String boleta){
		txtBoleta.sendKeys(boleta);
	}
	
	public void IngresarCorreo(String correo){
		txtCorreo.sendKeys(correo);
	}
	
	public void ClickOnContinuar(){
		btnContinuar.click();
	}
	
	public void ClickOnMedioPago(){
		radMedioPago.click();
	}
	
	public void ClickOnComboCuotas(){
		cboCuotas.click();
	}
	
	public void ClickOnCuotas(){
		itemCuotas.click();
	}
	
	public void ClickOnPago(){
		btnPago.click();
	}
	
}
