package email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

	private Session session;
	private Transport transport;
	private Message message;
	private boolean sessionCreated = false;

//	private String FROM = "subscriptions@virtualmeeting.com";
//	private String SMTP = "smtp.virtualmeeting.com";
//	private String PWD = "VMDC@8ubs!";

	private String FROM = "jon.davies@vmci.net";
	private String SMTP = "smtp.vmci.net";
	private String PWD = "!55T6tt0nmcr";

	public EmailSender() {
	}

	public void send(Email email) {
		try {
			if (!sessionCreated) {
				createMailSession();
				createMailTransport();
				sessionCreated = true;
			}
			createMessage(email);
			transport.connect(FROM, PWD);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		}
		catch (MessagingException e) {
			throw new EmailException(e);
		}
	}

	private void createMessage(Email email) throws MessagingException {
		message = new MimeMessage(session);
		message.setFrom();
		for (InternetAddress i : email.getInternetAddresses()) {
			message.addRecipient(Message.RecipientType.TO, i);
		}
		message.setSubject(email.getSubject());
		message.setText(email.getBody());
	}

	private void createMailSession() {
		Properties props = new Properties();
		props.put("mail.smtp.host", SMTP);
		props.put("mail.from", FROM);
		props.put("mail.smtp" + ".auth", "true");
		session = Session.getInstance(props, null);
	}

	private void createMailTransport() throws NoSuchProviderException {
		transport = session.getTransport("smtp");
	}
}
