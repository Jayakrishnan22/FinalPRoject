package FinalProject;
import java.io.IOException;
import java.util.Scanner;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
//
//import CovidVerification.OTPGenerator;
//import CovidVerification.SendSMS;

public class MailSend {
	public static void main(String[] args) throws AddressException, MessagingException {
		EmailSender ar=new EmailSender();
		//OTPGenerator otpg = new OTPGenerator();
		try {
			ar.emailrecipiens();
		}catch(IOException e) {
			e.printStackTrace();
		}

	}
	
}