package com.mindtree.yoyogift.service.serviceimpl;

import java.util.Date;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.yoyogift.entity.User;
import com.mindtree.yoyogift.exception.service.EmailNotExist;
import com.mindtree.yoyogift.exception.service.InvalidEmailException;
import com.mindtree.yoyogift.exception.service.ServiceException;
import com.mindtree.yoyogift.exception.service.custom.UserNotFoundException;
import com.mindtree.yoyogift.repository.UserRepository;
import com.mindtree.yoyogift.service.EmailService;
import com.mindtree.yoyogift.utility.ReferalCodeUtil;
import com.mindtree.yoyogift.utility.Utility;

@Service
public class EmailServiveImp implements EmailService {
	@Autowired
	UserRepository userRepository;

	/*
	 * Method to send email.
	 */
	public String sendHtmlEmail(String host, String port, final String userName, final String password,
			String toAddress, String subject, String message) throws AddressException, MessagingException {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};

		Session session = Session.getInstance(properties, auth);

		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(userName));
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		// set plain text message
		msg.setContent(message, "text/html");

		// sends the e-mail
		Transport.send(msg);
		return "Message Send";

	}

	/*
	 * This function will we called first when api get hited and passing email
	 * address to whome we have to send email and id of the user
	 */

	@Override
	public String sendNotification(String email, long id) throws ServiceException {
		/*
		 * checking email is valid or not(Email pattern).
		 */
		boolean result = emailValidation(email);
		if (result == false) {
			throw new EmailNotExist("Email address is not valid");
		}

		// SMTP server information
		String host = "smtp.gmail.com";
		String port = "587";
		String mailFrom = "yoyogift.12345@gmail.com";
		String password = "yoyogift@123";

		// outgoing message information
		String mailTo = email;
		String subject = "Referal code";

		/*
		 * Implementation for hash code to send in a message.
		 */

		User userdata = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));

		/*
		 * Implementing Split method
		 */
		String varEmail = email;
		String[] toUser = varEmail.split("@");
		System.out.println(toUser[0]);

		String fromUser = userdata.getName();
		String varString = userdata.getPhoneNo();
		String varReferal = ReferalCodeUtil.Encryption(varString);

		System.err.println(varReferal);
		// message contains HTML markups
		/*
		 * Template to be send in email
		 */
		String template = Utility.getEmailTemplate();
		template = template.replace("${to}", toUser[0]);
		template = template.replace("${from}", fromUser);
		template = template.replace("${code}", varReferal);
		EmailServiveImp mailer = new EmailServiveImp();
		/*
		 * calling the method for sending email
		 */
		try {
			mailer.sendHtmlEmail(host, port, mailFrom, password, mailTo, subject, template);
			return "message send";
		} catch (Exception ex) {
			System.out.println("Failed to sent email.");
			throw new InvalidEmailException(ex.getMessage());

		}

	}

	public static Boolean emailValidation(String email) throws EmailNotExist {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (email == null) {
			throw new EmailNotExist("Email is null");
		}

		return pat.matcher(email).matches();

	}

}
