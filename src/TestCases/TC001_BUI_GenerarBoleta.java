package TestCases;

import java.awt.AWTException;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import PageObjects.BUIHomePage;
import PageObjects.LoginBUI;
import PageObjects.PortalDeTramitesHomePage;
import PageObjects.Tarjeta;
import Utilities.DataBaseQuery;
import Utilities.ReadPropertyFile;
import Utilities.UsefulMethods;

public class TC001_BUI_GenerarBoleta {
	
	private String scriptName = "TC001_BUI_GenerarBoleta";
	
	private WebDriver driver;
	
	private ReadPropertyFile data;
	private String user;
	private String pwd;
	private String urlBUI;
	private String urlPortalT;
	private String browser;
	private UsefulMethods usefulM;
	private LoginBUI loginPage;
	private BUIHomePage buiHomePage;
	private PortalDeTramitesHomePage portalTraHomePage;
	private String dataTestPath;	
	private String workspacePath;
	private String boletaId;
	private String urlPagoBUIToken;
	private JSONObject dataTest;
	private String documento;
	private String ayn;
	private String email;
	private DataBaseQuery queryClass;
	private String dependencia;
	private String titularVisa;
	private String tarjetaVisa;
	private String vencVisa;
	private String codSeguridadVisa;
	private String documentoVisa;
	private String calleVisa;
	private String numeroDirecVisa;
	private String fechaNacVisa;
	
	@Test
	public void ReadConfigFile() throws Exception{
		
		data = new ReadPropertyFile();
		user = data.getUser();
		pwd = data.getPwd();
		urlBUI = data.getUrlBUI();
		urlPortalT = data.getUrlPortalTram();
		browser = data.getBrowser();	
		dataTestPath = data.getDataTestPath();
		workspacePath = data.getWorkspacePath();
		urlPagoBUIToken = data.getUrlPagoBUI();
		titularVisa = data.getTitularVisa();
		tarjetaVisa = data.getTarjetaVisa();
		vencVisa = data.getVencVisa();
		codSeguridadVisa = data.getCodSeguridadVisa();
		documentoVisa = data.getDocumento();
		calleVisa = data.getDireccion();
		numeroDirecVisa = data.getNumeroDirec();
		fechaNacVisa = data.getFechaNac();
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
		driver.navigate().to(urlBUI);		
	}
	
	@Test (dependsOnMethods={"OpenBrowser"})
	public void ReadDataTest() throws JSONException{
		
		usefulM = new UsefulMethods(driver);
		String path = dataTestPath + scriptName + ".xlsx";
		dataTest = usefulM.GetDataTest(path);
	}
	
	@Test (dependsOnMethods={"ReadDataTest"})
	public void LoginBUI() throws InterruptedException{
		
		loginPage = new LoginBUI(driver);
		
		Assert.assertTrue(usefulM.CheckpointById(true,LoginBUI.txtUserId, 1),"No muestra User box.");
		
		loginPage.LoginUser(user, pwd);
		Assert.assertTrue(usefulM.CheckpointById(false,LoginBUI.txtPwdId, 3),"Aun sigue mostrando PWD box.");								
	}
	
	@Test (dependsOnMethods={"LoginBUI"})
	public void IrACrearBoleta() throws InterruptedException{
		
		buiHomePage = new BUIHomePage(driver);			
		
		buiHomePage.ClickOnMenu("boleta unica");		
		Thread.sleep(1000);
		
		buiHomePage.ClickOnItem("crear boleta");
		Thread.sleep(1000);
		
		Assert.assertTrue(usefulM.CheckpointById(true,BUIHomePage.txtDocumentoId, 2),"No se muestra los datos del contribuyente en 'Crear Boleta'.");				
	}
	
	@Test (dependsOnMethods={"IrACrearBoleta"})
	public void IngresarDatosDelContribuyente() throws InterruptedException, JSONException{
			
		buiHomePage.ClickOnComboDependencias();
		Thread.sleep(1000);
		
		dependencia = dataTest.get("Dependencia").toString();
		buiHomePage.SelectDependencia(dependencia);
		Thread.sleep(2000);
		
		documento = dataTest.get("Dni").toString();
		buiHomePage.IngresarDocumento(documento);
		
		ayn = dataTest.get("ApellidoYNombre").toString() +" "+ usefulM.GetId();
		buiHomePage.IngresarApellidoYNombre(ayn);
		
		email =  dataTest.get("Email").toString();
		buiHomePage.IngresarEmail(email);	
		
		String dir = dataTest.get("Direccion").toString();
		buiHomePage.IngresarDireccion(dir);
		
		String piso = dataTest.get("Piso").toString();
		buiHomePage.IngresarPiso(piso);
		
		String dep = dataTest.get("Departamento").toString();
		buiHomePage.IngresarDepartamento(dep);
		
		String loc = dataTest.get("Localidad").toString();
		buiHomePage.IngresarLocalidad(loc);
		
		String codpos = dataTest.get("CodigoPostal").toString();
		buiHomePage.IngresarCodigoPostal(codpos);
				
		Assert.assertTrue(usefulM.CheckpointById(true,BUIHomePage.txtCodPostalId, 1),"No se muestra el campo Codigo Postal en 'Crear Boleta'.");				
	}

	@Test (dependsOnMethods={"IngresarDatosDelContribuyente"})
	public void AgregarConcepto() throws InterruptedException, JSONException{
			
		Assert.assertTrue(usefulM.CheckpointById(true,BUIHomePage.btnConceptoId, 1),"No se muestra el boton Concepto en 'Crear Boleta'.");
		
		buiHomePage.ClickOnConcepto();		
		
		Assert.assertTrue(usefulM.CheckpointById(true,BUIHomePage.txtCodigoId, 3),"No se muestra el campo Codigo en agregar Concepto de 'Crear Boleta'.");

		String cod =  dataTest.get("Codigo").toString();		
		buiHomePage.IngresarCodigo(cod);
		Thread.sleep(4000);
		
		buiHomePage.SelectCodigo(cod);
		
		buiHomePage.ClickOnAgregar();
		Thread.sleep(4000);
		
		Assert.assertTrue(usefulM.CheckpointById(false,BUIHomePage.txtCodigoId, 5),"No se pudo agregar Concepto en 'Crear Boleta'.");
	}	

	@Test (dependsOnMethods={"AgregarConcepto"})
	public void GenerarBoleta() throws InterruptedException{
	
		buiHomePage.ClickOnGenerar();
		Thread.sleep(5000);
		
		Assert.assertTrue(usefulM.CheckpointById(true,BUIHomePage.btnAceptarId, 30),"No se muestra la Boleta (PDF).");
		
		boletaId = buiHomePage.ObtenerBoletaId();
		Thread.sleep(1000);
		
		buiHomePage.ClickOnAceptar();
		Thread.sleep(5000);
		
		Assert.assertTrue(usefulM.CheckpointById(false,BUIHomePage.btnAceptarId, 15),"No cierra el modal de la Boleta (PDF).");
		
	}
	
	@Test (dependsOnMethods={"GenerarBoleta"})	
	public void VerificarBase() throws Exception{
		
		queryClass = new DataBaseQuery();
		
		Assert.assertTrue(queryClass.Count("bu.boleta where numero = '"+ boletaId +"'")!=0,"No se genero el registro en la Base de Datos. (bu.boleta)");
		
		String contribuyente = queryClass.Select("bu.boleta where numero = '"+ boletaId +"'","contribuyenteid");

		Assert.assertTrue(queryClass.Count("bu.contribuyente where id = '"+ contribuyente +"'")!=0,"No se genero el registro en la Base de Datos. (bu.contribuyente)");
		
		String nombre = queryClass.Select("bu.contribuyente where id = '"+ contribuyente +"'","nombre");		
				
		Assert.assertTrue(nombre.contentEquals(ayn),"El nombre y apellido no coincide con la Base de Datos");
		
		String doc = queryClass.Select("bu.contribuyente where id = '"+ contribuyente +"'","documento");
		
		Assert.assertTrue(documento.contentEquals(doc),"El documento no coincide con la Base de Datos");
	
	}	
	
	@Test (dependsOnMethods={"VerificarBase"})
	public void IrAReportesPorNumero() throws InterruptedException{
		
		buiHomePage.ClickOnMenu("reportes");		
		Thread.sleep(1000);
		
		buiHomePage.ClickOnPorNumero();
		Thread.sleep(1000);
		
		Assert.assertTrue(usefulM.CheckpointById(true,BUIHomePage.txtNumeroId, 2),"No se muestra la busqueda por 'Numero' en datos de la Boleta de Reportes.");				
	}
	
	@Test (dependsOnMethods={"IrAReportesPorNumero"})
	public void IngresarBoletaPorNumero() throws InterruptedException{

		buiHomePage.IngresarNumero(boletaId);	
		Thread.sleep(1000);
		
		buiHomePage.ClickOnGenerar();
		Thread.sleep(2000);
		
		Assert.assertTrue(usefulM.CheckpointById(true,BUIHomePage.txtEstadoId, 5),"No se muestra el resultado de la busqueda por 'Numero' en datos de la Boleta de Reportes.");
		
		String estadoBoleta = buiHomePage.txtEstado.getText();
		
		Assert.assertTrue(estadoBoleta.contentEquals("Creada"),"El estado de la boleta '"+ estadoBoleta +"' no es el correcto en la busqueda por 'Numero'. Estado esperado 'Creada'");
		Thread.sleep(1000);
		
		Assert.assertTrue(buiHomePage.CheckTextInvoice(ayn),"No se encuentra el nombre '"+ ayn +"' en la Boleta por pantalla.");
	}
	
	@Test (dependsOnMethods={"IngresarBoletaPorNumero"})
	public void IrAReportesPorListado() throws InterruptedException{
		
		buiHomePage = new BUIHomePage(driver);			
		
		buiHomePage.ClickOnMenu("reportes");		
		Thread.sleep(1000);
		
		buiHomePage.ClickOnPorListado();
		Thread.sleep(1000);
		
		Assert.assertTrue(usefulM.CheckpointById(true,BUIHomePage.txtNombreId, 2),"No se muestra la busqueda por 'Listado' en Filtros de Reportes.");		
	}
	
	@Test (dependsOnMethods={"IrAReportesPorListado"})
	public void IngresarBoletaPorListado() throws InterruptedException{

		buiHomePage.SelectDependenciaReport(dependencia);
		
		buiHomePage.IngresarAyNReporte(ayn);
		
		buiHomePage.IngresarFechaDesde(usefulM.GetDate());
		
		buiHomePage.IngresarFechaHasta(usefulM.GetDate());
		
		buiHomePage.IngresarDocumentoReport(documento);
		
		buiHomePage.ClickOnBuscar();
		Thread.sleep(3000);
		
		Assert.assertTrue(buiHomePage.CheckGrid(boletaId),"La boleta '"+ boletaId +"' no se encontro en la grilla de la busqueda por 'Listado'.");
	}
	
	@Test (dependsOnMethods={"IngresarBoletaPorListado"})
	public void PagarBoleta() throws InterruptedException{
		
		driver.navigate().to(urlPortalT);
	
		portalTraHomePage = new PortalDeTramitesHomePage(driver);
		
		portalTraHomePage.ClickOnMenu("Paga tu Boleta");
		Thread.sleep(2000);
		
		portalTraHomePage.IngresarDocumento(documento);
		Thread.sleep(1000);
		
		portalTraHomePage.IngresarBoleta(boletaId);
		Thread.sleep(1000);
		
		portalTraHomePage.IngresarCorreo(email);
		Thread.sleep(1000);
		
		portalTraHomePage.ClickOnContinuar();
		Thread.sleep(1000);
				
		Assert.assertTrue(usefulM.CheckpointById(true,PortalDeTramitesHomePage.btnContinuarId, 10),"No se muestra la Boleta Id a pagar.");
		
		portalTraHomePage.ClickOnContinuar();
		Thread.sleep(5000);
		
		Assert.assertTrue(usefulM.CheckpointById(true,PortalDeTramitesHomePage.radMedioPagoId, 45),"No se muestra el Medio de Pago.");
		
		String token = usefulM.GetToken();
		
		driver.navigate().to(urlPagoBUIToken+token);
		Thread.sleep(1000);
		
		Assert.assertTrue(usefulM.CheckpointById(true,PortalDeTramitesHomePage.radMedioPagoId, 10),"No se muestra el Medio de Pago despues de cambiar la Url de pago + token.");
		
		portalTraHomePage.ClickOnMedioPago();
		Thread.sleep(1000);
		
		Assert.assertTrue(usefulM.CheckpointByCSS(true,PortalDeTramitesHomePage.cboCuotasCSS,"select",10),"No se muestra las cuotas.");
		
		portalTraHomePage.ClickOnComboCuotas();
		Thread.sleep(1000);
		
		portalTraHomePage.ClickOnCuotas();
		Thread.sleep(1000);
		
		portalTraHomePage.ClickOnPago();
		Thread.sleep(1000);
		
		Assert.assertTrue(usefulM.CheckpointByCSS(false,PortalDeTramitesHomePage.cboCuotasCSS,"select",10),"Aun se muestra la pantalla de forma de pago.");
		
		Tarjeta tarjeta = new Tarjeta(driver);
		
		tarjeta.IngresarTitular(titularVisa);
		Thread.sleep(500);
		
		tarjeta.IngresarNumTarjeta(tarjetaVisa);
		Thread.sleep(500);
		
		tarjeta.IngresarVencVisa(vencVisa);
		Thread.sleep(500);

		tarjeta.IngresarCodSeguridadVisa(codSeguridadVisa);
		Thread.sleep(500);

		tarjeta.IngresarEmail(email);
		Thread.sleep(500);

		tarjeta.IngresarDocumento(documentoVisa);
		Thread.sleep(500);

		tarjeta.IngresarCalle(calleVisa);
		Thread.sleep(500);

		tarjeta.IngresarNumero(numeroDirecVisa);
		Thread.sleep(500);
		
		tarjeta.IngresarFechaNac(fechaNacVisa);
		Thread.sleep(500);
		
		tarjeta.ClickOnAceptar();
		Thread.sleep(3000);
		
		Assert.assertTrue(usefulM.CheckpointByCSS(true,Tarjeta.textMessageCSS,"transacción",10),"La ventata del resultado de la transaccion con tarteja no se esta mostrando.");
		
		Assert.assertTrue(tarjeta.CheckMessage(),"La operacion de pago no fue exitosa. Verificar datos");
				
		System.out.println(tarjeta.GetOperacionId());
	}	
	
	
	@Test (dependsOnMethods={"PagarBoleta"})	
	public void VerificarPagoEnBase() throws Exception{
		
		queryClass = new DataBaseQuery();
		
		Assert.assertTrue(queryClass.Count("PAGOELECTRONICO.COBRO where OBSERVACION like '"+ boletaId +"'")!=0,"No se genero el registro de pago en la Base de Datos. (PAGOELECTRONICO.COBRO)");
		
		String pagoID = queryClass.Select("PAGOELECTRONICO.COBRO where OBSERVACION like '"+ boletaId +"'","ID");
	
	}
	
	@Test (dependsOnMethods={"PagarBoleta"})
	public void LogoutBUI(){
		
//		buiHomePage.Logout();
//		
//		Assert.assertTrue(usefulM.CheckpointByXpath(false,BUIHomePage.btnLogoutXpath, 3),"Aun sigue mostrando el BUI Home Page.");
//		
//		driver.quit();
	}	
	
	@AfterTest
    public void ReportAndErrors() throws AWTException, IOException {
		usefulM.ReportAndErrors(workspacePath);
        
    }

}
