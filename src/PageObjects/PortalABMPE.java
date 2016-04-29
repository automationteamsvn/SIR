package PageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PortalABMPE {
	
	public PortalABMPE(WebDriver driver){
		PageFactory.initElements(driver, this);
		//localDriver = driver;
	}	
	
	//private WebDriver localDriver;
	
	public static final String textTituloABMPECSS = ".panel-title";

	public static final String btnLinksCSS = ".btn.btn-default";
	@FindBy(how = How.CSS, using = btnLinksCSS)
	private List<WebElement> btnLinks;
	
	public static final String btnLinkTablaCSS = ".btn.btn-success.ABMLinkTabla";
	@FindBy(how = How.CSS, using = btnLinkTablaCSS)
	private List<WebElement> btnLinkTabla;
	
	@FindBy(how = How.CSS, using = ".btn.btn-success.btn-sm")
	private List<WebElement> btnLinkTramite;
	
	@FindBy(how = How.TAG_NAME, using = "input")
	private List<WebElement> txtBox;
	
	@FindBy(how = How.TAG_NAME, using = "select")
	private List<WebElement> cboList;

	@FindBy(how = How.CSS, using = ".ng-binding")
	private List<WebElement> textTramite;

	@FindBy(how = How.CSS, using = ".ng-binding.ng-scope")
	private List<WebElement> textCuotas;
	
	@FindBy(how = How.CSS, using = ".ABMLinkTabla.glyphicon.glyphicon-chevron-right")
	private List<WebElement> btnLinkAgregarCuotas;
	
	public static final String btnGuardarCSS = ".btn.btn-lg.btn-success";
	@FindBy(how = How.CSS, using = btnGuardarCSS)
	private List<WebElement> btnGuardarTram;
	
	public static final String btnAceptarCSS = ".btn.btn-sm.btn-default";
	@FindBy(how = How.CSS, using = btnAceptarCSS)
	private List<WebElement> btnAceptar;
	
	@FindBy(how = How.CSS, using = ".breadcrumb>li>a")
	private List<WebElement> linkText;
	
	@FindBy(how = How.TAG_NAME, using = "tbody")
	private WebElement tableText;
	
	public static final String textLoadingCSS = ".loading-message";
	
	@FindBy(css = ".glyphicon.glyphicon.glyphicon-log-out.ABMLinkTabla")
	private WebElement linkLogout;
	
	public void ClickOnABMTramites() throws InterruptedException{
		
		for(int x=0;x<btnLinks.size();x++){
			if(btnLinks.get(x).getText().contentEquals("ABM Trámites")){
				btnLinks.get(x).click();;
				Thread.sleep(5000);
				break;
			}
		}
	}
	
	public void ClickOnCrearTramite() throws InterruptedException{
		
		for(int x=0;x<btnLinkTabla.size();x++){
			if(btnLinkTabla.get(x).getText().contentEquals("Crear un nuevo trámite")){
				btnLinkTabla.get(x).click();
				Thread.sleep(3000);
				break;									
			}
		}				
	}
	
	public void IngresarTitulo (String titulo) {
		
		for(int x=0;x<txtBox.size();x++){
			if(txtBox.get(x).getAttribute("data-ng-model").contentEquals("edicion.tramite.Nombre")){
				txtBox.get(x).sendKeys(titulo);
				break;
			}
		}		
	}
	
	public void IngresarMaxCuotas (String cuotas) throws InterruptedException {
		
		for(int x=0;x<txtBox.size();x++){
			if(txtBox.get(x).getAttribute("data-ng-model").contentEquals("edicion.tramite.CantCuotas")){				
				WebElement toClear = txtBox.get(x); 
				toClear.sendKeys(Keys.CONTROL + "a");	
				Thread.sleep(500);
				txtBox.get(x).sendKeys(cuotas);
				break;
			}
		}		
	}
	
	public void IngresarVencimiento (String dias) throws InterruptedException {
		
		for(int x=0;x<txtBox.size();x++){
			if(txtBox.get(x).getAttribute("data-ng-model").contentEquals("edicion.tramite.DiasVencimiento")){
				txtBox.get(x).clear();
				Thread.sleep(500);
				txtBox.get(x).sendKeys(dias);
				break;
			}
		}		
	}
	
	public void SelectConcepto (String concepto) {
		
		for(int x=0;x<cboList.size();x++){
			if(cboList.get(x).getAttribute("data-ng-model").contentEquals("edicion.conceptoSeleccionado")){
					cboList.get(x).click();
					List<WebElement> values = cboList.get(x).findElements(By.tagName("option"));
					for(int y=0;y<values.size();y++){
						if(values.get(y).getText().contains(concepto)){
							values.get(y).click();
							break;
						}						
					}
					break;	
			}
		}
	}
	
	public void ClickOnAgregarConcepto() throws InterruptedException{
		
		for(int x=0;x<btnLinkTramite.size();x++){
			if(btnLinkTramite.get(x).getText().contentEquals("Agregar el concepto seleccionado")){
				btnLinkTramite.get(x).click();
				Thread.sleep(1000);
				break;									
			}
		}				
	}
	
	public boolean VericarConcepto (String desc){
		
		boolean resultado = false;	
		
		if(CheckConcepto(desc)){
			resultado = true;
		}
			
		return resultado;
	}
	
	
	private boolean CheckConcepto (String tmpText){
		
		boolean resultado = false;	
		
		for(int x=0;x<textTramite.size();x++){
			
			if(textTramite.get(x).getText().trim().toLowerCase().contains(tmpText.trim().toLowerCase())){
				resultado = true;
				break;
			}
		}	
		return resultado;
	}
	
	public void ClickOnDecidir () {
		
		for(int x=0;x<txtBox.size();x++){
			if(txtBox.get(x).getAttribute("data-ng-model").contentEquals("edicion.tramite.utilizaDecidir")){
				if(txtBox.get(x).getAttribute("class").contains("ng-pristine")){
					txtBox.get(x).click();	
				}				
				break;
			}
		}		
	}
	
	public void IngresarNoComercio (String comercio) {
		
		for(int x=0;x<txtBox.size();x++){
			if(txtBox.get(x).getAttribute("data-ng-model").contentEquals("edicion.tramite.Decidir.NroComercio")){
				txtBox.get(x).sendKeys(comercio);
				break;
			}
		}		
	}

	public void ClickOffNotificacion () {

		for(int x=0;x<txtBox.size();x++){
			if(txtBox.get(x).getAttribute("data-ng-model").contentEquals("edicion.tramite.utilizaNotificacion")){
				if(txtBox.get(x).getAttribute("class").contains("ng-dirty")){
					txtBox.get(x).click();	
				}				
				break;
			}
		}		
	}
	
	public void SelectTarjeta (String tarjeta) throws InterruptedException{
		
		for(int x=0;x<cboList.size();x++){
			if(cboList.get(x).getAttribute("data-ng-model").contentEquals("edicion.mdpSeleccionado")){
						cboList.get(x).click();
						Thread.sleep(500);
						cboList.get(x).sendKeys(tarjeta);
						Thread.sleep(3000);
						cboList.get(x).sendKeys(Keys.TAB);
						break;
			}
		}
	}	
	
	public void ClickOnAgregarMedioDePago() throws InterruptedException{
		
		for(int x=0;x<btnLinkTramite.size();x++){
			if(btnLinkTramite.get(x).getText().contentEquals("Agregar el medio de pago seleccionado")){
				btnLinkTramite.get(x).click();
				Thread.sleep(1000);
				break;									
			}
		}				
	}	
	
	public void ClickOnCuotas (String cuotas) throws InterruptedException{
		
		for(int x=0;x<textCuotas.size();x++){
			if(textCuotas.get(x).getAttribute("value").contentEquals(cuotas)){
				textCuotas.get(x).click();
				Thread.sleep(1000);
				break;									
			}
		}				
	}

	public void ClickOnAgregarCuotas () throws InterruptedException{
		
		for(int x=0;x<btnLinkAgregarCuotas.size();x++){
			if(btnLinkAgregarCuotas.get(x).getAttribute("title").contentEquals("Seleccionar cuotas.")){
				btnLinkAgregarCuotas.get(x).click();
				Thread.sleep(1000);
				break;									
			}
		}				
	}
	
	public void ClickOnGuardarTramite (){
		
		for(int x=0;x<btnGuardarTram.size();x++){
			if(btnGuardarTram.get(x).getText().contains("¡Guardar el Trámite!")){
				btnGuardarTram.get(x).click();			
				break;									
			}
		}		
	}
	
	public void ClickOnAceptar (){
		
		for(int x=0;x<btnAceptar.size();x++){
			if(btnAceptar.get(x).getText().contains("Aceptar")){
				btnAceptar.get(x).click();			
				break;									
			}
		}		
	}
	
	public void ClickOnInicio () {
		
		for(int x=0;x<linkText.size();x++){
			if(linkText.get(x).getText().contains("Inicio")){
				linkText.get(x).click();;
				break;
			}
		}
	}
	
	public void ClickOnABMClientes() throws InterruptedException{
		
		for(int x=0;x<btnLinks.size();x++){
			if(btnLinks.get(x).getText().contentEquals("ABM Clientes")){
				btnLinks.get(x).click();;
				Thread.sleep(3000);
				break;
			}
		}
	}
	
	public void ClickOnClienteParaEditar(String cliente) throws InterruptedException{
		List<WebElement> rowText = tableText.findElements(By.className("ng-scope"));
		for(int x=0;x<rowText.size();x++){
			List<WebElement> row = rowText.get(x).findElements(By.tagName("td"));			
			if(row.get(0).getText().contentEquals(cliente)){
				row.get(2).findElement(By.cssSelector(".ABMLinkTabla.glyphicon.glyphicon-edit")).click();
				break;
			}
		}
	}
	
	public void SelectTramite (String tramite) {
			for(int x=0;x<cboList.size();x++){
				if(cboList.get(x).getAttribute("data-ng-model").contentEquals("edicion.tramiteSeleccionado")){
						cboList.get(x).click();
						List<WebElement> values = cboList.get(x).findElements(By.tagName("option"));
						for(int y=0;y<values.size();y++){
							if(values.get(y).getText().contains(tramite)){
								values.get(y).click();																					
								break;
							}						
						}
						break;	
				}
			}
		
	}
	
	public void ClickOnGuardarCliente (){
		
		for(int x=0;x<btnGuardarTram.size();x++){
			if(btnGuardarTram.get(x).getText().contains("¡Guardar el Cliente!")){
				btnGuardarTram.get(x).click();			
				break;									
			}
		}		
	}
	
	public void ClickOnLogout (){
		this.linkLogout.click();
	}
	
}
