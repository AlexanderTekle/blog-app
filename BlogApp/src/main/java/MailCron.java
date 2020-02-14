import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.mail.*;
import javax.mail.internet.*;

public class MailCron extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");

	    response.getWriter().print("You've got mail!\r\n");
	    
	    Properties props = new Properties();
	    Session session = Session.getDefaultInstance(props, null);

		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("s.dauenbaugh@utexas.edu", "Sam School"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress("s.dauenbaugh@gmail.com", "Sam"));
			msg.setSubject("Testing");
			msg.setText("This message is a test");
			Transport.send(msg);
		} catch (AddressException e) {
		} catch (MessagingException e) {
		} catch (UnsupportedEncodingException e) {
		}
	}

}
