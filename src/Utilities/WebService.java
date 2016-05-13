package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;


public class WebService {
	
	public String Post() throws URISyntaxException, IOException {

		DefaultHttpClient client = new DefaultHttpClient();
		URI uri = new URI("http://buisir-testing.buenosaires.gob.ar/service/api/BUI/Get");
		InputStream stream = new FileInputStream("C:\\Users\\mflores\\Desktop\\Work\\SIR_Workspace\\SIR\\src\\ZZ\\GetListBoletas");
		String inputData = IOUtils.toString(stream);
		HttpEntity entity = new StringEntity(inputData);
		HttpPost post = new HttpPost(uri);
		post.setEntity(entity);
		post.setHeader("Content-Type" , "application/json" );
		
		String userCredentials = "mfloresauto:123456";
		String basicAuth = "Basic " + new String(new Base64().encode(userCredentials.getBytes()));
		
		//String basicAuth = "basic bGNhbHpvbjpsY2Fsem9u";
		post.setHeader("Authorization", basicAuth);
		HttpResponse response = client.execute(post);
		int StatusCode = response.getStatusLine().getStatusCode();
		Assert.assertEquals(StatusCode, 200);
		String Final_Response = EntityUtils.toString(response.getEntity());
		
		System.out.println(Final_Response);
		
		return Final_Response;

	}
	
//	private static SOAPMessage createSOAPRequest(String strPath) throws Exception {
//	         
//	    // Create a SOAP message from the XML file located at the given path
//	    FileInputStream fis = new FileInputStream(new File(strPath));
//	    MessageFactory factory = MessageFactory.newInstance();
//	    SOAPMessage message = factory.createMessage(new MimeHeaders(), fis);
//	    return message;
//	}
//	
//	private static SOAPMessage getSOAPResponse(SOAPMessage soapRequest, String strEndpoint) throws Exception, SOAPException {
//	         
//	    // Send the SOAP request to the given endpoint and return the corresponding response
//	    SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
//	    SOAPConnection soapConnection = soapConnectionFactory.createConnection();
//	    SOAPMessage soapResponse = soapConnection.call(soapRequest, strEndpoint);
//	    return soapResponse;    
//	}
//	
//	private static void validateValue(SOAPMessage soapMsg, String strEl, String strExpected) throws Exception {
//        
//	    // Get all elements with the requested element tag from the SOAP message
//	    SOAPBody soapBody = soapMsg.getSOAPBody();
//	    NodeList elements = soapBody.getElementsByTagName(strEl);
//	         
//	    // Check whether there is exactly one element with the given tag
//	    if (elements.getLength() != 1){
//	        System.out.println("Expected exactly one element " + strEl + "in message, but found " + Integer.toString(elements.getLength()));
//	    } else {
//	        // Validate the element value against the expected value
//	        String strActual = elements.item(0).getTextContent();
//	        if (strActual.equals(strExpected)) {
//	            System.out.println("Actual value " + strActual + " for element " + strEl + " matches expected value");
//	        } else {
//	            System.out.println("Expected value " + strExpected + " for element " + strEl + ", but found " + strActual + " instead");
//	        }
//	    }
//	}
	

}
