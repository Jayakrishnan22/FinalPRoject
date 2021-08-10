package FinalProject;


import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;

import javax.net.ssl.HttpsURLConnection;

public class SmsSender {
	public void sendSms(String numbers){
		try {
			String apiKey="aYePZiR74OGHsbUFq5Sl3EcVCKzA6DtLxJgBj2fnrpIvQu0yk9mQE0a3xCOeKH6Jq7fGh5oDPpkZTYjg";
			String sendId ="FSTSMS";
			
			LocalDate firstvaccine = LocalDate.now();
    		LocalDate secondvaccine = firstvaccine.plusDays(90);
    		
	        String message="Hello, You have done 1st vaccination of CoviShield on "
	        		+firstvaccine+". We will remember you that 2nd vaccination of Covishield date is "
	        		+secondvaccine+". Please save for future reference. You are advised to isolate yourself. Keep social distance. Stay Safe and Stay Strong";
	        
			
	        //important step...
			message=URLEncoder.encode(message, "UTF-8");
			String language="english";
			
			String route="v3";
			
			
			String myUrl="https://www.fast2sms.com/dev/bulkV2?authorization="+apiKey+"&sender_id="+sendId+"&message="+message+"&language="+language+"&route="+route+"&numbers="+numbers;
			
			//sending get request using java..
			
			URL url=new URL(myUrl);
			
			HttpsURLConnection con=(HttpsURLConnection)url.openConnection();
			
			con.setRequestMethod("GET");
			
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			con.setRequestProperty("cache-control", "no-cache");
			System.out.println(" Process Initiated..");
			
			
			//int code=con.getResponseCode();
			
			//System.out.println("Response code : "+code);
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}


