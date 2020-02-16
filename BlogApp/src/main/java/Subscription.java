import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Subscription extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		boolean subbed = true;
		//check if signed in user is subscribed
		if(subbed)
		{
			//show unsubscribe
		}
		else
		{
			//show subscribe
		}
	}

}
