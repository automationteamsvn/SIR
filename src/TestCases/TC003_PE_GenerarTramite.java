package TestCases;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.LoginABMPE;
import PageObjects.LoginABMTram;
import PageObjects.PortalABMPE;
import PageObjects.PortalABMTramites;
import Utilities.DataBaseQuery;
import Utilities.ReadPropertyFile;
import Utilities.UsefulMethods;

public class TC003_PE_GenerarTramite {
	
	private String scriptName = "TC003_PE_GenerarTramite";
	
	private WebDriver driver;
	
	private ReadPropertyFile data;
	private String user;
	private String pwd;
	private String urlABMPE;
	private String urlAMBTram;
	private String browser;
	private UsefulMethods usefulM;
	private LoginABMPE loginPage;
	private LoginABMTram loginABMTram;
	private PortalABMPE portalABMPE;
	private PortalABMTramites portalABMTram;
	private String dataTestPath;	
	private JSONObject dataTest;
	private String tituloTramPE;
	private String maxCuotasPE;
	private String vencimientoPE;
	private String concepto;
	private DataBaseQuery queryClass;
	
	@Test
	public void ReadConfigFile() throws Exception{
		
		data = new ReadPropertyFile();
		user = data.getUser();
		pwd = data.getPwd();
		urlABMPE = data.getUrlABMPE();
		urlAMBTram = data.getUrlABMTram();
		browser = data.getBrowser();	
		dataTestPath = data.getDataTestPath();
	}	

	@Test (dependsOnMethods={"ReadConfigFile"})
	public void OpenBrowser() {
		
		if (browser.toLowerCase().contentEquals("firefox")){
			driver = new FirefoxDriver();	
		}else if (browser.toLowerCase().contentEquals("chrome")){
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\mflores\\Desktop\\Work\\CFO\\Selenium\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}else{
			System.setProperty("webdriver.ie.driver", "C:\\Users\\mflores\\Desktop\\Work\\CFO\\Selenium\\drivers\\IEDriverServer.exe");
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			caps.setCapability("ignoreZoomSetting", true);
			driver = new InternetExplorerDriver(caps);
		}
		
		driver.manage().window().maximize();
		//driver.navigate().to(urlABMPE);		
	}
	
	@Test (dependsOnMethods={"OpenBrowser"})
	public void ReadDataTest() throws JSONException{
		
		usefulM = new UsefulMethods(driver);
		String path = dataTestPath + scriptName + ".xlsx";
		dataTest = usefulM.GetDataTest(path);
	}
	
	@Test (dependsOnMethods={"ReadDataTest"})
	public void LoginABMPE() throws InterruptedException{
		
		driver.navigate().to(urlABMPE);
		
		loginPage = new LoginABMPE(driver);
		
		Assert.assertTrue(usefulM.CheckpointByCSS(true,LoginABMPE.btnLoginCSS,"Ingresar",40),"No se carga la pagina login ABM Tramites PE.");
		
		loginPage.LoginUser(user, pwd);
		
		Assert.assertTrue(usefulM.CheckpointByCSS(false,LoginABMPE.btnLoginCSS,"Ingresar",40),"Aun sigue mostrando la pagina login ABM Tramites PE.");
												
	}

	@Test (dependsOnMethods={"LoginABMPE"})
	public void IrACrearTramite() throws InterruptedException{
		
		Assert.assertTrue(usefulM.CheckpointByCSS(true,PortalABMPE.textTituloABMPECSS,"Tareas de administración del Portal",30),"No se muestra la pantalla de inicio ABM Tramites PE.");
		
		portalABMPE = new PortalABMPE(driver);
		
		portalABMPE.ClickOnABMTramites();
		
		Assert.assertTrue(usefulM.CheckpointByCSS(false, PortalABMPE.textLoadingCSS,"Espere, por favor...", 15),"No se cierra el Modal post clic en ABM Tramites.");
		
		Assert.assertTrue(usefulM.CheckpointByCSS(true, PortalABMPE.btnLinkTablaCSS,"Crear un nuevo trámite", 10),"No se carga el boton de Crear Tramite.");
		
		portalABMPE.ClickOnCrearTramite();	
		
		//Assert.assertTrue(usefulM.CheckpointByCSS(false, PortalABMPE.textLoadingCSS,"Espere, por favor...", 20),"No se cierra el Modal post clic en Crear Tramite.");
		
		Assert.assertTrue(usefulM.CheckpointByCSS(false, PortalABMPE.btnLinkTablaCSS,"Crear un nuevo trámite",20),"Aun se muestra el boton de Crear Tramite");
	}
	
	@Test (dependsOnMethods={"IrACrearTramite"})
	public void GuardarTramite() throws InterruptedException, JSONException{
		
		Assert.assertTrue(usefulM.CheckpointByCSS(true, PortalABMTramites.btnGuardarTramCSS,"¡Guardar el Trámite!",10),"No se carga la pagina 'Creacion de un nuevo Tramite'.");
		
		tituloTramPE = dataTest.get("Titulo").toString()+" "+ usefulM.GetId();
		portalABMPE.IngresarTitulo(tituloTramPE);
		
		maxCuotasPE = dataTest.get("MaxCuotas").toString();
		portalABMPE.IngresarMaxCuotas(maxCuotasPE);
		
		vencimientoPE = dataTest.get("Vencimiento").toString();
		portalABMPE.IngresarVencimiento(vencimientoPE);
		
		concepto = dataTest.get("Codigo").toString();
		portalABMPE.SelectConcepto(concepto);
		
		String descripcion = dataTest.get("Descripcion").toString();
		
		portalABMPE.ClickOnAgregarConcepto();
				
		Assert.assertTrue(portalABMPE.VericarConcepto(descripcion),"No se cargo el concepto al tramite.");
		
		portalABMPE.ClickOnDecidir();
		
		String comercio = dataTest.get("Comercio").toString();
		portalABMPE.IngresarNoComercio(comercio);
			
		portalABMPE.ClickOffNotificacion();
		
		String tarjeta = dataTest.get("Tarjeta").toString();
		portalABMPE.SelectTarjeta(tarjeta);
		
		portalABMPE.ClickOnAgregarMedioDePago();
		
		String cuotas = dataTest.get("Cuotas").toString();
		portalABMPE.ClickOnCuotas(cuotas);
		
		portalABMPE.ClickOnAgregarCuotas();
		
		portalABMPE.ClickOnGuardarTramite();

		Assert.assertTrue(usefulM.CheckpointByCSS(true,PortalABMPE.btnAceptarCSS,"Aceptar",10),"No se carga el boton de 'Aceptar' del modal.");
		
		portalABMPE.ClickOnAceptar();
			
		Assert.assertTrue(usefulM.CheckpointByCSS(false,PortalABMPE.btnAceptarCSS,"Aceptar",10),"No se hace click en el boton de 'Aceptar' del modal.");

	}	
	
	@Test (dependsOnMethods={"GuardarTramite"})	
	public void VerificarBase() throws Exception{
		
		queryClass = new DataBaseQuery();
		
		Assert.assertTrue(queryClass.Count("PAGOELECTRONICO.TRAMITE where nombre = '"+ tituloTramPE +"'")!=0,"No se genero el registro en la Base de Datos. (PAGOELECTRONICO.TRAMITE)");
		
		String tramitePEIdBase = queryClass.Select("PAGOELECTRONICO.TRAMITE where nombre = '"+ tituloTramPE +"'","id");
		
		String diasVencimientoBase = queryClass.Select("PAGOELECTRONICO.TRAMITE where nombre = '"+ tituloTramPE +"'","diasvencimiento");
			
		Assert.assertTrue(diasVencimientoBase.trim().toLowerCase().contentEquals(vencimientoPE.trim().toLowerCase()),"Los Dias de Vencimiento ingresado no coincide con la Base de Datos. Base: " + diasVencimientoBase + " Ingresado: "+ vencimientoPE);
			 
		String cuotasBase = queryClass.Select("PAGOELECTRONICO.TRAMITE where nombre = '"+ tituloTramPE +"'","cantcuotas");
		
		Assert.assertTrue(cuotasBase.trim().toLowerCase().contentEquals(maxCuotasPE.trim().toLowerCase()),"La cantidad de Cuotas ingresado no coincide con la Base de Datos. Base: " + cuotasBase + "Ingresado: "+ maxCuotasPE);

		String codigoConceptoBase = queryClass.Select("PAGOELECTRONICO.TRAMITE a inner join PAGOELECTRONICO.TRAMITEITEM b on a.ID = b.IDTRAMITE inner join SIR.CONCEPTO c on b.IDITEM = c.ID where a.id = '"+ tramitePEIdBase +"'","c.CODIGO");
		
		codigoConceptoBase = usefulM.RemoveCeros(codigoConceptoBase.replace(".", "-"));
				
		Assert.assertTrue(codigoConceptoBase.trim().toLowerCase().contentEquals(concepto.trim().toLowerCase()),"El Codigo del concept ingresado no coincide con la Base de Datos. Base: " + codigoConceptoBase + " Ingresado: "+ concepto);
		
	}
	
	@Test (dependsOnMethods={"VerificarBase"})	
	public void AsociarCliente() throws InterruptedException, JSONException{
		
		portalABMPE.ClickOnInicio();
		
		Assert.assertTrue(usefulM.CheckpointByCSS(true,PortalABMPE.textTituloABMPECSS,"Tareas de administración del Portal",10),"No se muestra el home page ABM Tramites PE.");
		
		portalABMPE.ClickOnABMClientes();
		
		Assert.assertTrue(usefulM.CheckpointById(true, "tablaClientes", 5),"No se muestra la pantalla de 'Administración de Clientes'.");
		
		String cliente = dataTest.get("Cliente").toString();
		portalABMPE.ClickOnClienteParaEditar(cliente);
		
		Assert.assertTrue(usefulM.CheckpointByCSS(false, PortalABMPE.textLoadingCSS,"Espere, por favor...",20),"No se cierra el Modal post clic en Editar Cliente.");
	
		Assert.assertTrue(usefulM.CheckpointByCSS(true, PortalABMPE.btnGuardarCSS,"¡Guardar el Cliente!",20),"No se carga la pagina 'Edición de cliente existente'.");
		
		portalABMPE.SelectTramite(tituloTramPE);
		
		portalABMPE.ClickOnGuardarCliente();
		
		Assert.assertTrue(usefulM.CheckpointByCSS(true,PortalABMPE.btnAceptarCSS,"Aceptar",5),"No se carga el boton de 'Aceptar' del modal.");
		
		Assert.assertTrue(usefulM.CheckpointByCSS(false,PortalABMPE.btnAceptarCSS,"Aceptar",5),"No desaparece el boton de 'Aceptar' del modal.");
		
		Assert.assertTrue(usefulM.CheckpointByCSS(false, PortalABMPE.textLoadingCSS,"Espere, por favor...", 15),"No se cierra el Modal post clic en ABM Tramites.");
		
	}
	
	@Test (dependsOnMethods={"AsociarCliente"})
	public void LogoutABMPE() throws InterruptedException{
				
		portalABMPE.ClickOnLogout();
		
		Assert.assertTrue(usefulM.CheckpointByCSS(true,LoginABMPE.btnLoginCSS,"Ingresar",10),"No se pudo desloguear de la pagina ABM Pago Electronico.");										
	
		//driver.quit();
	}
	
	@Test (dependsOnMethods={"LogoutABMPE"})	
	public void LoginABMTram() throws InterruptedException {

		driver.navigate().to(urlAMBTram);	
		
		loginABMTram = new LoginABMTram(driver);
		
		Assert.assertTrue(usefulM.CheckpointByCSS(true,LoginABMTram.btnLoginCSS,"Ingresar",30),"No se carga la pagina login ABM Tramites.");
		
		loginABMTram.LoginUser(user, pwd);
		
		Assert.assertTrue(usefulM.CheckpointByCSS(true,PortalABMTramites.textTituloABMTramCSS,"Tareas de administración del Portal",30),"Aun sigue mostrando la pagina login ABM Tramites.");								

	}
	
	@Test (dependsOnMethods={"LoginABMTram"})
	public void BuscarTramite() throws InterruptedException{
		
		portalABMTram = new PortalABMTramites(driver);
		
		Assert.assertTrue(usefulM.CheckpointByClassName(true,PortalABMTramites.bodyTareasClass,5),"No se carga el panel de Tareas.");
		
		portalABMTram.ClickOnTramites();
		
		Assert.assertTrue(usefulM.CheckpointByCSS(true, PortalABMTramites.btnCrearTramCSS,"Crear un nuevo trámite",10),"No se carga el boton de Crear Tramite.");
		
		portalABMTram.IngresarTituloEnFiltro(tituloTramPE);
		
		Assert.assertTrue(portalABMTram.VerificarBusquedaDeTramite(tituloTramPE,"","","","",""),"No se encuentra el tramite creado. Tramite id: "+tituloTramPE);
		
		portalABMTram.ClickOnEditarTram();
		
	}
}
