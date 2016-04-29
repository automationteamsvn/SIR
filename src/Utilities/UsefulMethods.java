package Utilities;

import java.awt.AWTException;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.swing.JWindow;

public class UsefulMethods {

WebDriver driver;
	
	public UsefulMethods(WebDriver driver){
		this.driver = driver;
	}

	public UsefulMethods(){
		
	}
	
	public boolean CheckpointById(boolean positive,String id,int timeToWait){
		
		boolean exists;
		
		if(positive){
			driver.manage().timeouts().implicitlyWait(timeToWait, TimeUnit.SECONDS);	
			exists = driver.findElements(By.id(id)).size() != 0;
			if(exists && !driver.findElement(By.id(id)).isDisplayed()){
				exists = false;
			}
		}else{
			driver.manage().timeouts().implicitlyWait(timeToWait, TimeUnit.SECONDS);
			exists = driver.findElements(By.id(id)).size() == 0;
			if(!exists && !driver.findElement(By.id(id)).isDisplayed()){
				exists = true;
			}
		}
		
		return exists;
	}
	
	public boolean CheckpointByXpath(boolean positive,String xpath,int timeToWait){
		
		boolean exists;
		
		if(positive){
			driver.manage().timeouts().implicitlyWait(timeToWait, TimeUnit.SECONDS);	
			exists = driver.findElements(By.xpath(xpath)).size() != 0;
		}else{
			driver.manage().timeouts().implicitlyWait(timeToWait, TimeUnit.SECONDS);
			exists = driver.findElements(By.xpath(xpath)).size() == 0;
		}
		
		return exists;
	}
	
	public boolean CheckpointByClassName(boolean positive,String className,int timeToWait) throws InterruptedException{
		
		boolean exists;
		
		if(positive){
			driver.manage().timeouts().implicitlyWait(timeToWait, TimeUnit.SECONDS);	
			exists = driver.findElements(By.className(className)).size() != 0;	
		}else{
			Thread.sleep(timeToWait*1000);
			driver.manage().timeouts().implicitlyWait(timeToWait, TimeUnit.SECONDS);
			exists = driver.findElements(By.className(className)).size() == 0;
		}
		
		return exists;
	}
	
	public boolean CheckpointByCSS(boolean positive,String css,String text,int timeToWait) throws InterruptedException{
		
		boolean exists = false;
		
		for(int x=1;x<timeToWait;x++){
			Thread.sleep(1000);
			List<WebElement> cssWebElement = driver.findElements(By.cssSelector(css));
			if(positive){
				if(!cssWebElement.isEmpty()){
					for(int y=0;y<cssWebElement.size();y++){
						if(cssWebElement.get(y).getText().contains(text)){
							exists = true;			
							break;									
						}
					}
					if(exists){
						break;
					}
				}
			}else{
				exists = true;
				if(!cssWebElement.isEmpty()){
					for(int z=0;z<cssWebElement.size();z++){
						if(cssWebElement.get(z).getText().contains(text)){
							exists = false;			
							break;									
						}
					}				
				}else{		
					break;
				}
			}					
			
		}		
		
		return exists;
	}
	
	public JSONObject GetDataTest(String path) throws JSONException{
		
		String JsonText = "{";
		
		ExcelDataConfig excelFile = new ExcelDataConfig(path);
			
		int cols = excelFile.getColumnCount(0)-1;
		
		for (int i=0;i<cols;i++){
			JsonText = JsonText + excelFile.getData(0,0,i) + ":" + excelFile.getData(0,1,i) + ",";
		}
		
		JsonText = JsonText + excelFile.getData(0,0,cols) + ":" + excelFile.getData(0,1,cols) + "}";
			
		JSONObject jsonObj = new JSONObject(JsonText);
		
		return jsonObj;
	}
	
	public String GetId(){
		Date date = new Date();
		String id = String.valueOf(date.getTime());
		return id;
	}
	
	public String GetDate(){		
		String pattern = "dd/MM/yyyy";
	    SimpleDateFormat format = new SimpleDateFormat(pattern);
	    String date = format.format(new Date());
	    return date;
	}
	
	public String GetDateId(){		
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
	    return reportDate.replace("/", "").replace(":", "").replace(" ", "");
	}
	
	public String RemoveCeros(String codigo){
		String text = "";
		String split[] = codigo.split("-");
        for(String parts:split){
        	if(text.contentEquals("")){
        		text = Integer.toString(Integer.parseInt(parts));	
        	}else{
        		text = text + "." + Integer.toString(Integer.parseInt(parts));	
        	}        		
        	
        }		    	
		return text;
	}
	
	public void ReportAndErrors(String workspacePath) throws AWTException, IOException {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date today = Calendar.getInstance().getTime();        
		String reportDate = df.format(today);
		String reportPath = workspacePath+"Reports\\"+reportDate.replace("/","-")+"\\";
		File dateDir = new File(reportPath);
		
		if (!dateDir.exists()){
				dateDir.mkdir();	
		}
		
		String tmpPath = dateDir.getAbsolutePath();
		String homePath = tmpPath.substring(0,tmpPath.indexOf("src"));
				
		String idText = this.GetDateId();
		String executionPath = reportPath+idText+"\\";
		File executionDir = new File(executionPath);
		executionDir.mkdir();	
				
        if (driver != null) {    
    		String errorPath = executionPath+"ErrorImages\\";
    		File errorDir = new File(errorPath);
    		errorDir.mkdir();
    		
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage capture = new Robot().createScreenCapture(screenRect);
//            
//            ImageIO.write(capture, "png", new File(errorPath+"ErrorImage-"+idText+".png"));
    		   		
//    		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//
//    		FileUtils.copyFile(scrFile, new File("D:\\screenshot.jpg"));    		

			
            driver.close();
        }
        
        File srcDir = new File(homePath+"\\test-output\\");
        
        for (File srcFile: srcDir.listFiles()) {
            if (!srcFile.isDirectory()) {
                FileUtils.copyFileToDirectory(srcFile, executionDir);
            }
        }
        
    }
	
	public String GetToken(){
		String token = "";
		String url = driver.getCurrentUrl();
		int beginPos = url.indexOf("=");
		int lastPos = url.indexOf("&");
		token = url.substring(beginPos,lastPos);
//				split("-");
//        for(String parts:split){
//        	if(text.contentEquals("")){
//        		text = Integer.toString(Integer.parseInt(parts));	
//        	}else{
//        		text = text + "." + Integer.toString(Integer.parseInt(parts));	
//        	}        		
//        	
//        }		    	
		return token;
	}
	
}
