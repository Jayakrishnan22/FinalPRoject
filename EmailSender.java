package FinalProject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailSender {
	void emailrecipiens()throws IOException, AddressException, MessagingException{
		EmailSender mail1=new EmailSender();
		SmsSender sms=new SmsSender();
		FileInputStream fis= new FileInputStream("C:\\HIT-2021\\workspace\\Project\\src\\CovidVaccination\\mailid.properties");
		//FileInputStream fis= new FileInputStream("mailid.properties");
		Properties prop = new Properties();
		prop.load(fis);
		
		Map<String,String>hm=(Map)prop;
		for(Map.Entry<String,String>me:hm.entrySet()) {
			String mail=(String)me.getKey();
			String[] name = mail.split("@");
			String name1 = name[0];
			if(mail.contains("@")) {
			System.out.println("Sending mail to "+name1);
			String mailid1= me.getKey();
			String mobilenum = me.getValue();
			sms.sendSms(mobilenum);
			mail1.setupServerProperties();
			mail1.draftEmail(name1,mailid1);
			mail1.sendEmail();
			try {Thread.sleep(1000);}
			catch(InterruptedException e) {e.printStackTrace();}
			}
			else {System.out.println("valid Email id Found:"+mail);
		
			}
		}
	}
Session newSession=null;
MimeMessage mimemessage=null;
void sendEmail() throws MessagingException {
	String fromUser = "projecthit2021@gmail.com";
	String fromUserPassword = "projecthit";
	String emailHost ="smtp.gmail.com";
	Transport transport = newSession.getTransport("smtp");
	transport.connect(emailHost,fromUser,fromUserPassword);
	transport.sendMessage(mimemessage,mimemessage.getAllRecipients());
	transport.close();
	System.out.println("Email Successfully Sent");
	
	System.out.println("SMS Successfully Sent");
	
}
MimeMessage draftEmail(String name, String mailid) throws AddressException,MessagingException {
	String emailSubject = "Covid19 Vaccination";
	String Signature = "<b style=\"color:red;\">Thanks & Regrads</b><br>Chennai Vaccination Centre<br>";
	
	LocalDate firstvaccine=LocalDate.now();
	LocalDate secondvaccine = firstvaccine.plusDays(90);
	
	String emailBody="Hi "+name+", <br><br>  You have done 1st vaccination of on "
			+firstvaccine+". We will remember you that 2nd vaccination of date is "
			+secondvaccine+". <br><br>  Please save for future reference. You are advised to isolate yourself. "
			+ "<br><br> Keep social distance. "
			+ "Stay Safe and Stay Strong</a><br><br><br>"+Signature;
	
	mimemessage = new MimeMessage(newSession);
	
	mimemessage.addRecipient(Message.RecipientType.TO,new InternetAddress(mailid));
	mimemessage.setSubject(emailSubject);
	MimeBodyPart bodypart = new MimeBodyPart();
	bodypart.setContent(emailBody,"text/html");
	
	MimeMultipart multipart = new MimeMultipart();
	multipart.addBodyPart(bodypart);
	mimemessage.setContent(multipart);
	return mimemessage;
	
}
void setupServerProperties() {
	Properties properties = new Properties();
	properties.put("mail.smtp.port","587");
	properties.put("mail.smpt.auth","true");
	properties.put("mail.smtp.starttls.enable","true");
	newSession=Session.getDefaultInstance(properties,null);
	}
}
