package PageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PortalABMTramites {
	
	public PortalABMTramites(WebDriver driver){
		PageFactory.initElements(driver, this);
		//localDriver = driver;
	}	
	
	//private WebDriver localDriver;
	
	public static final String bodyTareasClass = "panel-heading";
	public static final String tablaTramId = "tablaTramites";
	public static final String textTituloABMTramCSS = ".panel-title";
	
	@FindBy(how = How.TAG_NAME, using = "a")
	private List<WebElement> linkItems;
	
	@FindBy(how = How.TAG_NAME, using = "input")
	private List<WebElement> txtBox;			
	
	@FindBy(how = How.TAG_NAME, using = "button")
	private List<WebElement> btnButton;	
	
	@FindBy(how = How.TAG_NAME, using = "div")
	private List<WebElement> btnButtonDiv;	
	
	@FindBy(how = How.TAG_NAME, using = "select")
	private List<WebElement> cboList;	
	
	public static final String btnCrearTramCSS = ".btn.btn-success.ABMLinkTabla";
	@FindBy(how = How.CSS, using = btnCrearTramCSS)
	private List<WebElement> btnCrearTram;

	@FindBy(how = How.CSS, using = ".btn.btn-sm.btn-success")
	private List<WebElement> btnButtonGrupo;
	
	@FindBy(how = How.CSS, using = ".ng-scope.well")
	private List<WebElement> boxGrupo;
	
	public static final String btnGuardarTramCSS = ".btn.btn-lg.btn-success";
	@FindBy(how = How.CSS, using = btnGuardarTramCSS)
	private List<WebElement> btnGuardarTram;
	
	public static final String btnAceptarCSS = ".btn.btn-sm.btn-default";
	@FindBy(how = How.CSS, using = btnAceptarCSS)
	private List<WebElement> btnAceptar;
	
	public static final String linkEditTramCSS = ".ABMLinkTabla.glyphicon.glyphicon-edit";
	@FindBy(how = How.CSS, using = linkEditTramCSS)
	WebElement linkEditTram;
	
	@FindBy(tagName = "tbody")
	private WebElement bodyPage;

	@FindBy(css = ".ng-binding")
	private List<WebElement> textConceptos;	
	
	@FindBy(css = ".glyphicon.glyphicon.glyphicon-log-out.ABMLinkTabla")
	private WebElement linkLogout;	
	
	public void ClickOnTramites() throws InterruptedException{
		
		for(int x=0;x<linkItems.size();x++){
			if(linkItems.get(x).getAttribute("href").contains("/ABM/Tramites")){
				linkItems.get(x).click();;
				Thread.sleep(9000);
				break;
			}
		}
	}
	
	public void ClickOnCrearTramite() throws InterruptedException{
		
		for(int x=0;x<btnCrearTram.size();x++){
			if(btnCrearTram.get(x).getText().contains("Crear un nuevo trámite")){
				btnCrearTram.get(x).click();
				Thread.sleep(3000);
				break;									
			}
		}				
	}
	
	public void IngresarTitulo (String titulo) {
		
		for(int x=0;x<txtBox.size();x++){
			if(txtBox.get(x).getAttribute("data-ng-model").contentEquals("edicion.tramite.nombre")){
				txtBox.get(x).sendKeys(titulo);
				break;
			}
		}		
	}
	
	private String GetTitulo () {
		
		String text="";
		
		for(int x=0;x<txtBox.size();x++){
			if(txtBox.get(x).getAttribute("data-ng-model").contentEquals("edicion.tramite.nombre")){
				text = txtBox.get(x).getAttribute("value");
				break;
			}
		}
		return text;
	}	
	
	public void IngresarDescripcion (String desc) {
		
		for(int x=0;x<txtBox.size();x++){
			if(txtBox.get(x).getAttribute("data-ng-model").contentEquals("edicion.tramite.descripcion")){
				txtBox.get(x).sendKeys(desc);
				break;
			}
		}
	}
	
	public void IngresarMensaje (String mensaje) {
		
		for(int x=0;x<txtBox.size();x++){
			if(txtBox.get(x).getAttribute("data-ng-model").contentEquals("edicion.tramite.mensaje")){
				txtBox.get(x).sendKeys(mensaje);
				break;
			}
		}
	}	

	public void IngresarUsuario (String usuario){
		
		for(int x=0;x<txtBox.size();x++){
			if(txtBox.get(x).getAttribute("data-ng-model").contentEquals("edicion.tramite.usuario")){
				txtBox.get(x).sendKeys(usuario);
				break;
			}
		}
	}	
	
	public void IngresarPwd (String pwd){
		
		for(int x=0;x<txtBox.size();x++){
			if(txtBox.get(x).getAttribute("data-ng-model").contentEquals("edicion.tramite.password")){
				txtBox.get(x).sendKeys(pwd);
				break;
			}
		}
	}	
	
	public void ClickOnActivo () {
		
		for(int x=0;x<btnButton.size();x++){
			if(btnButton.get(x).getAttribute("data-ng-click").contentEquals("edicion.tramite.activo = true")){
				btnButton.get(x).click();
				break;
			}
		}
	}
	
	public void SelectRubro (String rubro) throws InterruptedException{
		
		for(int x=0;x<cboList.size();x++){
			if(cboList.get(x).getAttribute("data-ng-model").contentEquals("edicion.tramite.rubro")){
				cboList.get(x).click();
				Thread.sleep(1000);
				cboList.get(x).sendKeys(rubro);
				Thread.sleep(500);
				cboList.get(x).click();
				break;
			}
		}
	}
	
	public void SelectPagina (String pagina) throws InterruptedException{
		
		for(int x=0;x<cboList.size();x++){
			if(cboList.get(x).getAttribute("data-ng-model").contentEquals("edicion.tramite.pagina")){
				cboList.get(x).click();
				Thread.sleep(1000);
				cboList.get(x).sendKeys(pagina);
				Thread.sleep(500);
				cboList.get(x).click();
				break;
			}
		}
	}
	
	public void ClickOnAgregarGrupo () {
		
		for(int x=0;x<btnButtonGrupo.size();x++){
			if(btnButtonGrupo.get(x).getText().contains("Agregar nuevo grupo de conceptos")){
				btnButtonGrupo.get(x).click();
				break;
			}
		}	
	}
	
	public void ClickOnGrupoOpc (){
		
		for(int x=0;x<txtBox.size();x++){
			if(txtBox.get(x).getAttribute("data-ng-model").contentEquals("grupo.opcional")){
				if(txtBox.get(x).getAttribute("class").contains("pristine")){
					txtBox.get(x).click();
					break;					
				}
			}
		}
		
	}
	
	public void IngresarMensajeDeGrupo (String mensajeG){
		
		for(int x=0;x<txtBox.size();x++){
			if(txtBox.get(x).getAttribute("data-ng-model").contentEquals("grupo.mensaje")){
				if(txtBox.get(x).getAttribute("class").contains("pristine")){
					txtBox.get(x).sendKeys(mensajeG);
					break;					
				}

			}
		}
	}
	
	public void ClickOnAgregarConcepto (){

		for(int x=0;x<boxGrupo.size();x++){
			
			if(boxGrupo.get(x).getAttribute("class").contentEquals("ng-scope well")){
				WebElement btnConcepto = boxGrupo.get(x).findElement(By.cssSelector(".btn.btn-sm.btn-success")); 
				btnConcepto.click();			
				break;									
			}
		}
	}
	
	public void SelectConcepto (String concepto) throws InterruptedException{
		
		for(int x=0;x<cboList.size();x++){
			if(cboList.get(x).getAttribute("data-ng-model").contentEquals("tc.concepto") && 
					cboList.get(x).getAttribute("class").contentEquals("ng-pristine ng-valid")){
						cboList.get(x).click();
						Thread.sleep(1000);
						cboList.get(x).sendKeys(concepto);
						Thread.sleep(1000);
						cboList.get(x).sendKeys(Keys.TAB);
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
	
	public void IngresarTituloEnFiltro (String titulo){
		
		for(int x=0;x<txtBox.size();x++){
			if(txtBox.get(x).getAttribute("data-ng-model").contentEquals("filtro.nombre")){
				txtBox.get(x).sendKeys(titulo);
				break;
			}
		}
		
	}
	
	public boolean VerificarBusquedaDeTramite (String titulo, String mensaje, String estado, String redirec, String usuario, String pwd){
		
		boolean resultado = false;
		
		List<WebElement> listResult = bodyPage.findElements(By.className("ng-scope"));
			
		if(listResult.size()==1){			
			List<WebElement> list = listResult.get(0).findElements(By.className("ng-binding"));
			if(list.get(0).getText().trim().toLowerCase().contentEquals(titulo.trim().toLowerCase()) &&
				list.get(1).getText().trim().toLowerCase().contentEquals(mensaje.trim().toLowerCase()) &&
				list.get(2).getText().trim().toLowerCase().contentEquals(estado.trim().toLowerCase()) &&				
				list.get(3).getText().trim().toLowerCase().contentEquals(redirec.trim().toLowerCase()) &&
				list.get(4).getText().trim().toLowerCase().contentEquals(usuario.trim().toLowerCase()) &&
				list.get(5).getText().trim().contentEquals(pwd.trim()) ){
					
					resultado = true;
			}
		}		
		return resultado;
	}
	
	public void ClickOnEditarTram () throws InterruptedException{
		
		linkEditTram.click();
		Thread.sleep(4000);
	}
	
	public boolean VerificarTramiteCreado (String titulo, String concepto1, String concepto2){
		
		boolean resultado = false;

		String text = this.GetTitulo(); 
				
		if(titulo.trim().toLowerCase().contentEquals(text.trim().toLowerCase()) &&
				this.CheckConceptos(concepto1) &&
				this.CheckConceptos(concepto2)){								
					resultado = true;
		}		
		return resultado;
	}
	
	private boolean CheckConceptos (String concepto){
		
		boolean resultado = false;
		String tmpText = "";
		String[] arrSplit = concepto.trim().toLowerCase().split(" ", 2);
		tmpText = arrSplit[1];
		
		for(int x=0;x<textConceptos.size();x++){
			
			if(textConceptos.get(x).getText().trim().toLowerCase().contains(tmpText)){
				resultado = true;
				break;
			}
		}
		
		return resultado;
	}
	
	public void ClickOnLogout (){
		this.linkLogout.click();
	}
	
}
