package Utilities;

import java.io.InputStream;
import java.util.Properties;

public class ReadPropertyFile {

	protected Properties prop = null;
	protected InputStream input = ReadPropertyFile.class.getClassLoader().getResourceAsStream("ConfigFile/config.properties");
	
	public ReadPropertyFile() throws Exception{
		prop = new Properties();
		prop.load(input);			
	}
	
	public String getUrlBUI(){
		return prop.getProperty("urlBUI");
	}
	
	public String getUrlPortalTram(){
		return prop.getProperty("urlPortalTram");
	}

	public String getUrlABMTram(){
		return prop.getProperty("urlAMBTram");
	}
	
	public String getUrlABMPE(){
		return prop.getProperty("urlAMBPE");
	}	
	
	public String getBrowser(){
		return prop.getProperty("browser");
	}	
	
	public String getUser(){
		return prop.getProperty("user");
	}	
	
	public String getPwd(){
		return prop.getProperty("psw");
	}
	
	public String getDataTestPath(){
		return prop.getProperty("dataTestPath");
	}
	
	public String getServerDB(){
		return prop.getProperty("serverDB");
	}	
	
	public String getUserDB(){
		return prop.getProperty("userDB");
	}
	
	public String getPwdDB(){
		return prop.getProperty("pwdDB");
	}
	
	public String getWorkspacePath(){
		return prop.getProperty("workplaceHomePath");
	}
	
	public String getUrlPagoBUI(){
		return prop.getProperty("urlPagoBUI");
	}

	public String getTitularVisa(){
		return prop.getProperty("titularVisa");
	}
	
	public String getTarjetaVisa(){
		return prop.getProperty("tarjetaVisa");
	}

	public String getVencVisa(){
		return prop.getProperty("vencVisa");
	}
	
	public String getCodSeguridadVisa(){
		return prop.getProperty("codSeguridadVisa");
	}
	
	public String getDocumento(){
		return prop.getProperty("documentoVisa");
	}
	
	public String getDireccion(){
		return prop.getProperty("direccionVisa");
	}
	
	public String getNumeroDirec(){
		return prop.getProperty("numeroVisa");
	}
	
	public String getFechaNac(){
		return prop.getProperty("nacimientoVisa");
	}	

	public String getUrlSIRTools(){
		return prop.getProperty("urlSIRTools");
	}
	
	public String getUserTools(){
		return prop.getProperty("userTools");
	}
	
	public String getPwdTools(){
		return prop.getProperty("pwdTools");
	}
	
	public String getUrlGUIDConvert(){
		return prop.getProperty("urlGUIDConverter");
	}
	
}
