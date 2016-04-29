package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class BUIHomePage {

	public BUIHomePage(WebDriver driver){
		PageFactory.initElements(driver, this);
		localDriver = driver;
	}	
	
	public WebDriver localDriver;
	
	public static final String btnAceptarId = "btnConfirm";
	@FindBy(id = btnAceptarId)	
	private WebElement btnAceptar;
	
	@FindBy(id = "msgConfirm")	
	private WebElement msgBoleta;
	
	private static final String btnGenerarId = "btnGenerar";
	@FindBy(id = btnGenerarId)	
	private WebElement btnGenerar;
	
	private static final String txtCodigoListId = "txtCodigo_listbox";
	@FindBy(id = txtCodigoListId)	
	private WebElement listCodigo;
	
	public static final String btnAgregarId = "Add";
	@FindBy(id=btnAgregarId)	
	private WebElement btnAgregar;
	
	public static final String txtCodigoId = "txtCodigo";
	@FindBy(id=txtCodigoId)	
	private WebElement txtCodigo;
	
	public static final String btnConceptoId = "addConcepto";
	@FindBy(id=btnConceptoId)	
	private WebElement btnConcepto;
	
	public static final String txtDocumentoId = "Contribuyente_Documento";
	@FindBy(id=txtDocumentoId)	
	private WebElement txtDocumento;
	
	@FindBy(id="Contribuyente_Nombre")	
	public WebElement txtApellidoYNombre;
	
	@FindBy(id="Contribuyente_Email")	
	public WebElement txtEmail;
	
	@FindBy(id="Contribuyente_Direccion")	
	private WebElement txtDireccion;
	
	@FindBy(id="Contribuyente_Piso")	
	private WebElement txtPiso;
	
	@FindBy(id="Contribuyente_Departamento")	
	private WebElement txtDepartamento;
	
	@FindBy(id="Contribuyente_Localidad")	
	private WebElement txtLocalidad;
	
	public static final String txtCodPostalId = "Contribuyente_CodigoPostal";
	@FindBy(id=txtCodPostalId)	
	private WebElement txtCodigoPostal;	
	
	private static final String menuBoletaClass = "navbar-header";
	@FindBy(how = How.CLASS_NAME, using = menuBoletaClass)	
	private WebElement menuItem;

	private static final String menuClass = "headerMenu";
	@FindBy(how = How.CLASS_NAME, using = menuClass)	
	private WebElement menu;
	
	private static final String listDependenciasId = "ddlDependencias_listbox";
	@FindBy(id=listDependenciasId)	
	private WebElement listDependencias;
	
	@FindBy(how=How.CLASS_NAME, using="ddlDependencias")	
	private WebElement cboDependencias;	
	
	public static final String btnLogoutXpath = "html/body/div[1]/div/div[1]/div[3]/a";
	@FindBy(xpath=btnLogoutXpath)
	public WebElement btnLogout;	
	
	public static final String txtNumeroId = "buNumero";
	@FindBy(id=txtNumeroId)
	public WebElement txtNumero;
	
	public static final String txtEstadoId = "buiStatus";
	@FindBy(id=txtEstadoId)
	public WebElement txtEstado;
	
	public static final String txtNombreId = "nombre";
	@FindBy(id=txtNombreId)
	public WebElement txtNombre;
	
	@FindBy(how = How.TAG_NAME, using = "a")
	private List<WebElement> btnNumero;
	
	@FindBy(how = How.CLASS_NAME, using = "k-input")
	private List<WebElement> cboDependencia;
	
	@FindBy(id = "btnBuscar")
	private WebElement btnBuscar;
	
	@FindBy(id = "documento")
	private WebElement txtDocumentoReport;
	
	@FindBy(id = "fechaDesde")
	private WebElement txtFechaDesde;
	
	@FindBy(id = "fechaHasta")
	private WebElement txtFechaHasta;
	
	@FindBy(id = "gridBoletas")
	private WebElement gridBoletas;
	
	@FindBy(id = "invoice")
	private WebElement textInvoice;
	
	public void Logout(){
		btnLogout.click();		
	}

	public void SelectCodigo(String codigo){
		WebElement list = this.listCodigo;
		List<WebElement> items = list.findElements(By.tagName("li"));
		for (int i=0;i<items.size();i++){
			if(items.get(i).getText().toLowerCase().contains(codigo.toLowerCase())){
				items.get(i).click();
				break;
			}
		}				
	}	
	
	public void SelectDependencia(String dependencia){
		WebElement list = this.listDependencias;
		List<WebElement> items = list.findElements(By.tagName("li"));
		for (int i=0;i<items.size();i++){
			if(items.get(i).getText().toLowerCase().contains(dependencia.toLowerCase())){
				items.get(i).click();
				break;
			}
		}				
	}
	
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
	
	public void ClickOnItem(String menuItem){
		WebElement list = this.menuItem;
		List<WebElement> items = list.findElements(By.tagName("li"));
		for (int i=0;i<items.size();i++){
			if(items.get(i).getText().toLowerCase().contentEquals(menuItem.toLowerCase())){
				items.get(i).click();
				break;
			}
		}				
	}
	
	public void IngresarCodigo(String codigo) throws InterruptedException{
		
		Actions builder = new Actions(localDriver);
	    WebElement element = txtCodigo;
	    for (char c : codigo.toCharArray()) {
	        builder = builder.sendKeys(element, c + "");
	        Thread.sleep(500);
	    }
	    builder.build().perform();
	    Thread.sleep(1000);
	}
	
	public void ClickOnAceptar(){
		btnAceptar.click();
	}
	
	public String ObtenerBoletaId(){
		String boletaId="";
		String[] text = msgBoleta.getText().split(" ");
		for(int x=0;x<text.length;x++){
			if(text[x].contains("-")){
				boletaId = text[x]; 
			}
		}
		return boletaId;
	}
	
	public void ClickOnAgregar(){
		btnAgregar.click();
	}	
	
	public void ClickOnConcepto(){
		btnConcepto.click();	
	}
	
	public void ClickOnComboDependencias(){
		cboDependencias.click();;
	}	
	
	public void ClickOnGenerar(){
		btnGenerar.click();;
	}		
	
	public void IngresarDocumento(String documento){
		txtDocumento.sendKeys(documento);
	}
	
	public void IngresarApellidoYNombre(String apeynombre){
		txtApellidoYNombre.sendKeys(apeynombre);
	}
	
	public void IngresarEmail(String email){
		txtEmail.sendKeys(email);
	}
	
	public void IngresarDireccion(String direccion){
		txtDireccion.sendKeys(direccion);
	}
	
	public void IngresarPiso(String piso){
		txtPiso.sendKeys(piso);
	}
	
	public void IngresarDepartamento(String departamento){
		txtDepartamento.sendKeys(departamento);
	}
	
	public void IngresarLocalidad(String localidad){
		txtLocalidad.sendKeys(localidad);
	}
	
	public void IngresarCodigoPostal(String codpostal){
		txtCodigoPostal.sendKeys(codpostal);
	}
	
	public void ClickOnPorNumero() throws InterruptedException{
		
		for(int x=0;x<btnNumero.size();x++){
			if(btnNumero.get(x).getText().contains("Por Numero")){
				btnNumero.get(x).click();
				Thread.sleep(1000);
				break;
			}
		}
	}
	
	public void ClickOnPorListado() throws InterruptedException{
		
		for(int x=0;x<btnNumero.size();x++){
			if(btnNumero.get(x).getText().contains("Listado")){
				btnNumero.get(x).click();
				Thread.sleep(1000);
				break;
			}
		}
	}
	
	public void IngresarNumero(String numero){
		txtNumero.sendKeys(numero);
	}
	
	public void SelectDependenciaReport(String dependencia) throws InterruptedException{
		
		for(int x=0;x<cboDependencia.size();x++){
			if(cboDependencia.get(x).getText().contains("ACA")){
				cboDependencia.get(x).click();
				Thread.sleep(1000);
				String depen = "APRA - Agencia de Prot";
				Actions builder = new Actions(localDriver);
			    WebElement element = cboDependencia.get(x);
			    for (char c : depen.toCharArray()) {
			        builder = builder.sendKeys(element, c + "");
			        Thread.sleep(100);;
			    }
			    builder.build().perform();				    
				break;
			}
		}
	}
	
	public void IngresarAyNReporte(String apeynombre){
		txtNombre.sendKeys(apeynombre);
	}
	
	public void ClickOnBuscar(){
		btnBuscar.click();
	}

	public void IngresarFechaDesde(String fechaDesde){
		txtFechaDesde.sendKeys(fechaDesde);
	}
	
	public void IngresarFechaHasta(String fechaHasta){
		txtFechaHasta.sendKeys(fechaHasta);
	}
	
	public void IngresarDocumentoReport(String documento){
		txtDocumentoReport.sendKeys(documento);
	}
	
	public boolean CheckGrid(String boletaId){
		return gridBoletas.getText().contains(boletaId);	
	}
	
	public boolean CheckTextInvoice(String ayn){
		return textInvoice.getText().toLowerCase().contains(ayn.toLowerCase());
	}
}
