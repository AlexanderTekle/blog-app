import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Date;
import java.util.Calendar;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MailCron extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");

	    response.getWriter().print("You've got mail!\r\n");
	    
	    Properties props = new Properties();
	    Session session = Session.getDefaultInstance(props, null);
	    
	    ArrayList<String> mailingList = getMailList();
	    
	    String dailyMessage = "New Posts: \n";
	    ArrayList<String> newPosts = new ArrayList<String>();
	    newPosts.add("User x posted title");
	    //Add new posts
	    for(String post : newPosts)
	    {
	    	dailyMessage += post + "\n\n";
	    }
	    dailyMessage += "Thanks for subscribing to the Bernie Blog!";
	    
	    for(String sub : mailingList)
	    {
	    	try {
				Message msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress("mailer@blog-app-268202.appspotmail.com", "Bernie Blog"));
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(sub, "Subscriber"));
				msg.setSubject("Bernie Blog Daily Update");
				msg.setText(dailyMessage);
				Transport.send(msg);
			} catch (AddressException e) {
			} catch (MessagingException e) {
			} catch (UnsupportedEncodingException e) {
			}
	    }
	}
	
	public ArrayList<String> getMailList()
	{
		ArrayList<String> mailingList = new ArrayList<String>();
		mailingList.add("s.dauenbaugh@gmail.com");
		//get all the subscribed emails from the datastore
		return mailingList;
	}

}
