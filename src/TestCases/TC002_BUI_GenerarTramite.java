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

import PageObjects.LoginABMTram;
import PageObjects.PortalABMTramites;
import Utilities.DataBaseQuery;
import Utilities.ReadPropertyFile;
import Utilities.UsefulMethods;

public class TC002_BUI_GenerarTramite {

	private String scriptName = "TC002_BUI_GenerarTramite";
	
	private WebDriver driver;
	
	private ReadPropertyFile data;
	private String user;
	private String pwd;
	private String urlABMTram;
	private String browser;
	private UsefulMethods usefulM;
	private LoginABMTram loginPage;
	private PortalABMTramites portalABMTram;
	private String dataTestPath;	
	private JSONObject dataTest;
	private String tituloTram;
	private String mensajeTram;
	private String usuarioTram;
	private String pwdTram;
	private String concepto1;
	private String concepto2;
	private DataBaseQuery queryClass;
	
	@Test
	public void ReadConfigFile() throws Exception{
		
		data = new ReadPropertyFile();
		user = data.getUser();
		pwd = data.getPwd();
		urlABMTram = data.getUrlABMTram();
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
		driver.navigate().to(urlABMTram);		
	}
	
	@Test (dependsOnMethods={"OpenBrowser"})
	public void ReadDataTest() throws JSONException{
		
		usefulM = new UsefulMethods(driver);
		String path = dataTestPath + scriptName + ".xlsx";
		dataTest = usefulM.GetDataTest(path);
	}
	
	@Test (dependsOnMethods={"ReadDataTest"})
	public void LoginABMTram() throws InterruptedException{
		
		loginPage = new LoginABMTram(driver);
		
		Assert.assertTrue(usefulM.CheckpointByCSS(true,LoginABMTram.btnLoginCSS,"Ingresar",30),"No se carga la pagina login ABM Tramites.");
		
		loginPage.LoginUser(user, pwd);
		
		Assert.assertTrue(usefulM.CheckpointByCSS(true,PortalABMTramites.textTituloABMTramCSS,"Tareas de administración del Portal",30),"Aun sigue mostrando la pagina login ABM Tramites.");								
	}
	
	@Test (dependsOnMethods={"LoginABMTram"})
	public void IrACrearTramite() throws InterruptedException{
		
		portalABMTram = new PortalABMTramites(driver);
		
		Assert.assertTrue(usefulM.CheckpointByClassName(true,PortalABMTramites.bodyTareasClass,5),"No se carga el panel de Tareas.");
		
		portalABMTram.ClickOnTramites();
		
		Assert.assertTrue(usefulM.CheckpointByCSS(true, PortalABMTramites.btnCrearTramCSS,"Crear un nuevo trámite",10),"No se carga el boton de Crear Tramite.");
		
		portalABMTram.ClickOnCrearTramite();	
		
		Assert.assertTrue(usefulM.CheckpointByCSS(false, PortalABMTramites.btnCrearTramCSS,"Crear un nuevo trámite",10),"Aun se muestra el boton de Crear Tramite");
	}
	
	@Test (dependsOnMethods={"IrACrearTramite"})
	public void GuardarTramite() throws InterruptedException, JSONException{
		
		Assert.assertTrue(usefulM.CheckpointByCSS(true, PortalABMTramites.btnGuardarTramCSS,"¡Guardar el Trámite!",20),"No se carga la pagina 'Creacion de un nuevo Tramite'.");
		
		tituloTram = dataTest.get("Titulo").toString()+" "+ usefulM.GetId();
		portalABMTram.IngresarTitulo(tituloTram);
		
		String descripcionTram = dataTest.get("Descripcion").toString();
		portalABMTram.IngresarDescripcion(descripcionTram);
		
		mensajeTram = dataTest.get("Mensaje").toString();
		portalABMTram.IngresarMensaje(mensajeTram);
		
		usuarioTram = dataTest.get("Usuario").toString();
		portalABMTram.IngresarUsuario(usuarioTram);
		
		pwdTram = dataTest.get("Password").toString();
		portalABMTram.IngresarPwd(pwdTram);
		
		portalABMTram.ClickOnActivo();
		
		String rubro = dataTest.get("Rubro").toString();
		portalABMTram.SelectRubro(rubro);
		
		String pagina = dataTest.get("Pagina").toString();
		portalABMTram.SelectPagina(pagina);
		
		portalABMTram.ClickOnAgregarGrupo();
		
		portalABMTram.ClickOnGrupoOpc();
		
		String mensajeG = dataTest.get("MensajeGrupo").toString();
		portalABMTram.IngresarMensajeDeGrupo(mensajeG);
		
		portalABMTram.ClickOnAgregarConcepto();
		
		concepto1 = dataTest.get("Concepto").toString();
		portalABMTram.SelectConcepto(concepto1);
		
		portalABMTram.ClickOnAgregarGrupo();
		
		portalABMTram.ClickOnGrupoOpc();
		
		String mensajeG2 = dataTest.get("MensajeGrupo2").toString();
		portalABMTram.IngresarMensajeDeGrupo(mensajeG2);
		
		portalABMTram.ClickOnAgregarConcepto();
		
		concepto2 = dataTest.get("Concepto2").toString();
		portalABMTram.SelectConcepto(concepto2);
		
		portalABMTram.ClickOnGuardarTramite();
		
		Assert.assertTrue(usefulM.CheckpointByCSS(true,PortalABMTramites.btnAceptarCSS,"Aceptar",10),"No se carga el boton de 'Aceptar' del modal.");
		
		portalABMTram.ClickOnAceptar();
		
		Assert.assertTrue(usefulM.CheckpointByCSS(false,PortalABMTramites.btnAceptarCSS,"Aceptar",10),"No se hace click en el boton de 'Aceptar' del modal.");
	}
	
	@Test (dependsOnMethods={"GuardarTramite"})	
	public void VerificarBase() throws Exception{
		
		queryClass = new DataBaseQuery();
		
		Assert.assertTrue(queryClass.Count("PORTALTRAMITES.TRAMITE where nombre = '"+ tituloTram +"'")!=0,"No se genero el registro en la Base de Datos. (PORTALTRAMITES.TRAMITE)");
		
		//String tramiteIDBase = queryClass.Select("PORTALTRAMITES.TRAMITE where nombre = '"+ tituloTram +"'","id");
		
		String mensajeBase = queryClass.Select("PORTALTRAMITES.TRAMITE where nombre = '"+ tituloTram +"'","mensaje");
			
		Assert.assertTrue(mensajeBase.trim().toLowerCase().contentEquals(mensajeTram.trim().toLowerCase()),"El MENSAJE ingresado no coincide con la Base de Datos. Base: " + mensajeBase + "Ingresado: "+ mensajeTram);
			
		String usuarioBase = queryClass.Select("PORTALTRAMITES.TRAMITE where nombre = '"+ tituloTram +"'","usuario");
		
		Assert.assertTrue(usuarioBase.trim().toLowerCase().contentEquals(usuarioTram.trim().toLowerCase()),"El Usuario ingresado no coincide con la Base de Datos. Base: " + usuarioBase + "Ingresado: "+ usuarioTram);

		String pwdBase = queryClass.Select("PORTALTRAMITES.TRAMITE where nombre = '"+ tituloTram +"'","password");
		
		Assert.assertTrue(pwdBase.trim().toLowerCase().contentEquals(pwdTram.trim().toLowerCase()),"El PWD ingresado no coincide con la Base de Datos. Base: " + pwdBase + "Ingresado: "+ pwdTram);

		//String codigoConceptoBase = queryClass.Select("portaltramites.grupo a inner join  portaltramites.tramiteconcepto b on a.ID = b.GRUPO_ID inner join SIR.CONCEPTO c on c.ID = b.CONCEPTO_ID WHERE a.tramite_id = '"+ tramiteIDBase +"'","c.CODIGO");
		
		//String descripcionConceptoBase = queryClass.Select("portaltramites.grupo a inner join  portaltramites.tramiteconcepto b on a.ID = b.GRUPO_ID inner join SIR.CONCEPTO c on c.ID = b.CONCEPTO_ID WHERE a.tramite_id = '"+ tramiteIDBase +"'","c.DESCRIPCION");
		
	}
	
	
	@Test (dependsOnMethods={"VerificarBase"})
	public void VerificarTramite() throws InterruptedException{
		
		portalABMTram.ClickOnTramites();
		
		Assert.assertTrue(usefulM.CheckpointByCSS(true, PortalABMTramites.btnCrearTramCSS,"Crear un nuevo trámite",10),"No se carga la pantalla de Tramites.");
		
		portalABMTram.IngresarTituloEnFiltro(tituloTram);
		
		Assert.assertTrue(portalABMTram.VerificarBusquedaDeTramite(tituloTram, mensajeTram, "Activo", "No", usuarioTram, pwdTram),"El resultado del filtro no coincide con los datos del tramite creado.");
		
		portalABMTram.ClickOnEditarTram();
		
		Assert.assertTrue(portalABMTram.VerificarTramiteCreado(tituloTram, concepto1, concepto2),"Los datos del tramite no coinciden con los datos ingresados.");
	}
	
	@Test (dependsOnMethods={"VerificarTramite"})
	public void LogoutABMTram() throws InterruptedException{
		
		portalABMTram.ClickOnLogout();
		
		Assert.assertTrue(usefulM.CheckpointByCSS(true,LoginABMTram.btnLoginCSS,"Ingresar",10),"No se pudo desloguear de la pagina ABM Tramites.");										
	
		driver.quit();
	}
	
}
