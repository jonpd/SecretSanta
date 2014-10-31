package email;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Email {

	private final List<InternetAddress> RECIPIENTS = new ArrayList<InternetAddress>();
	private String body;
	private String subject;

	public void addRecipient(String email) {
		try {
			InternetAddress addr = new InternetAddress(email, true);
			addr.validate();
			RECIPIENTS.add(addr);
		}
		catch (AddressException e) {

			throw new EmailException(e);
		}
	}

	public void addRecipients(List<String> emailList) {
		for (String emailAddress : emailList) {
			addRecipient(emailAddress);
		}
	}

	public List<String> getRecipients() {
		List<String> emailStrings = new ArrayList<String>();
		for (InternetAddress email : RECIPIENTS) {
			emailStrings.add(email.getAddress());
		}
		return emailStrings;
	}

	public List<InternetAddress> getInternetAddresses() {
		return RECIPIENTS;
	}

	public void setRecipients(List<String> emails) {
		RECIPIENTS.clear();
		for (String i : emails) {
			addRecipient(i);
		}
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public static boolean validEmailAddress(String email) {
		try {
			InternetAddress addr = new InternetAddress(email, true);
			addr.validate();
			return true;
		}
		catch (AddressException e) {
			return false;
		}
	}
}
