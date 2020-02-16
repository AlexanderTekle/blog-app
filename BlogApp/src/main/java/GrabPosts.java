import java.io.IOException;
import java.io.PrintWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;


@SuppressWarnings("serial")
@WebServlet(name = "grabposts", value="/grabposts")

public class GrabPosts extends HttpServlet{
	
	  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
	  
		  DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		  
		  String[] postTitles = new String[3];
		  String[] postContents = new String[3];
		  String[] postTimes = new String[3];

		  int i=0;
		  
		  for (Entity entity : datastore.prepare(new Query("BlogPost")).asIterable()) {
			  postTitles[i] = (String) entity.getProperty("Title");
			  postContents[i] = (String) entity.getProperty("Content");	
			  postTimes[i] = convertTime((Long)entity.getProperty("timestamp"));
			  i++;
			  if (i >= 3)
				  break;
		  }
		  
		  for (Entity entity : datastore.prepare(new Query("BlogPost")).asIterable()) {
			  System.out.println(entity);
		  }
		  
		  req.setAttribute("titles", postTitles);
		  req.setAttribute("contents", postContents);
		  req.setAttribute("times", postTimes);
		  req.setAttribute("length",i);
		  RequestDispatcher view = req.getRequestDispatcher("index.jsp");
	      view.forward(req, resp);
		  
	  }
	  
	  public String convertTime(long time){
		    Date date = new Date(time);
		    Format format = new SimpleDateFormat("MM/dd/yyyy, HH:mm");
		    return format.format(date);
	  }
}
