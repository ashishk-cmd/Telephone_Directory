package aiims.cf.td_api.utils;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class TextMessageServices {

	private static final Logger logger = LogManager.getLogger(TextMessageServices.class);
	
	@Value("${application.textmessage.url}")
	private String apiUrl;
	
	@Value("${application.textmessage.username}")
	private String apiUsername;
	
	@Value("${application.textmessage.password}")
	private String apiPassword;
	
	@Value("${application.textmessage.senderid}")
	private String apiSenderId;
	
	@Value("${application.textmessage.templateid}")
	private String apiTemplateId;
	
	@Value("${application.textmessage.templateid}")
	private String superAdminUsername;
	
	@Value("${application.textmessage.active}")
	private boolean isTextmessageActive;
	
	public void sendMessage(String mobileNo, String message) throws Exception {
		 try {
	          if(!isTextmessageActive) {
	        	  System.out.println("Message sent to  "+mobileNo+": "+message);
	        	  //throw new Exception("Text message services is disabled");
	          } 
		      else  if(mobileNo != null && !mobileNo.equals("") && mobileNo.length() == 10) {
	                System.out.println("message to  "+mobileNo);
	            	String url = apiUrl+"/Service.asmx/sendSingleSMS";
	                URL obj = new URL(url);
	
		            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
	                
		            connection.setRequestMethod("POST");
		            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		            connection.setDoOutput(true);
	
		            Map<String, String> formData = Map.of(
		                    "username",   apiUsername,
		                    "password",   apiPassword,
		                    "senderid",   apiSenderId,
		                    "mobileNos",  mobileNo,
		                    "message",    message,
		                    "templateid1",apiTemplateId
		            );
		           
		            StringBuilder formDataString = new StringBuilder();
		            for (Map.Entry<String, String> entry : formData.entrySet()) {
		                if (formDataString.length() != 0) {
		                    formDataString.append('&');
		                }
		                formDataString.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
		                formDataString.append('=');
		                formDataString.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
		            }
		            
		            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
		                wr.writeBytes(formDataString.toString());
		                wr.flush();
		            }
		            int responseCode = connection.getResponseCode();
		            if(responseCode != 200 || responseCode != 201)
		               logger.error(responseCode+": Text message response code");
		       }else
		    	   throw new Exception("Invalid Mobile No.");
	        } catch (Exception e) {
	        	logger.error("TextMessageServices Exception:{}, while sending message to:{}",e.getMessage(),mobileNo);
	        	throw new Exception(e.getMessage());
	        }
	}
	
}
