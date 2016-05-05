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
	
	public static final String textMessageCSS = ".texto>strong";
	@FindBy(css = textMessageCSS)
	private WebElement txtMessage;
	
	@FindBy(css = ".texto")
	private List<WebElement> txtData;
	
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
	
	public void IngresarDocumento (String documento) {		
		for(int x=0;x<txtBox.size();x++){
			if(txtBox.get(x).getAttribute("name").contentEquals("NRODOC")){
				txtBox.get(x).sendKeys(documento);
				break;
			}
		}		
	}	

	public void IngresarCalle (String calle) {		
		for(int x=0;x<txtBox.size();x++){
			if(txtBox.get(x).getAttribute("name").contentEquals("CALLE")){
				txtBox.get(x).sendKeys(calle);
				break;
			}
		}		
	}
	
	public void IngresarNumero (String numero) {		
		for(int x=0;x<txtBox.size();x++){
			if(txtBox.get(x).getAttribute("name").contentEquals("NROPUERTA")){
				txtBox.get(x).sendKeys(numero);
				break;
			}
		}		
	}	
		
	public void IngresarFechaNac (String fecha) {		
		for(int x=0;x<txtBox.size();x++){
			if(txtBox.get(x).getAttribute("name").contentEquals("FECHANACIMIENTO")){
				txtBox.get(x).sendKeys(fecha);
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

	public boolean CheckMessage () {
		boolean flag = false;
		if(txtMessage.getText().contains("La transacción ha sido APROBADA.")){
			flag = true;
		}
		return flag;
	}
	
	public String GetOperacionId () {
		int y = 0;
		String text = "";
		for(int x=0;x<txtData.size();x++){
			if(txtData.get(x).getText().contains("Código de Operación:")){
				y = x+1;
				break;
			}
		}
		
		for(int x=0;x<txtData.size();x++){
			if(x==y){
				text = txtData.get(x).getText();
				break;
			}
		}
		return text;
	}
}
