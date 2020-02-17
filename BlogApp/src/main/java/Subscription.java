import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;

@SuppressWarnings("serial")
@WebServlet(name = "Subscription", value="/subscription")
public class Subscription extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
	    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	    String email = request.getParameter("email");
	    Key key = KeyFactory.createKey("Subscription", email);
	    
	    Entity e = datastore.get(Arrays.asList(key)).get(key);
	    
	    if (e == null) {
	    	doPost(request, response, email, key, datastore);
	    	//subscribed!
	    } 
	    else {
	    	//already subscribed
	    }
	}

	
	  public void doPost(HttpServletRequest req, HttpServletResponse resp, String email, Key key, DatastoreService datastore)
	  throws IOException, ServletException {
		  Entity e = new Entity(key);
		  e.setProperty("email", email);
		  datastore.put(e);
		  RequestDispatcher view = req.getRequestDispatcher("index.jsp");
	      view.forward(req, resp);
	  }
}
