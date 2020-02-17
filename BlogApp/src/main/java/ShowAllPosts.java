import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
@WebServlet(name = "printemail", value="/printemail")
public class ShowAllPosts extends HttpServlet {


  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

	  
      DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
      
      ArrayList<String> firstNames = new ArrayList<String>();
      ArrayList<String> lastNames = new ArrayList<String>();
      ArrayList<String> postTitles = new ArrayList<String>();
      ArrayList<String> postContents = new ArrayList<String>();
      ArrayList<String> postTimes = new ArrayList<String>();
	  int i=0;
	  
	  for (Entity entity : datastore.prepare(new Query("BlogPost")).asIterable()) {
		  firstNames.add((String) entity.getProperty("firstname"));
		  lastNames.add((String) entity.getProperty("lastname"));
		  postTitles.add((String) entity.getProperty("Title"));
		  postContents.add((String) entity.getProperty("Content"));
		  postTimes.add(convertTime((Long) entity.getProperty("timestamp")));	

		  i++;
	  }
	  
	  req.setAttribute("firstnames", firstNames.toArray(new String[firstNames.size()]));
	  req.setAttribute("lastnames", lastNames.toArray(new String[lastNames.size()]));
	  req.setAttribute("titles", postTitles.toArray(new String[postTitles.size()]));
	  req.setAttribute("contents", postContents.toArray(new String[postContents.size()]));
	  req.setAttribute("times", postTimes.toArray(new String[postTimes.size()]));
	  req.setAttribute("length",i);
	  req.setAttribute("showallposts", true);
	  RequestDispatcher view = req.getRequestDispatcher("index.jsp");
	  view.forward(req, resp);    
    
  }
  
  public String convertTime(long time){
	    Date date = new Date(time);
	    Format format = new SimpleDateFormat("MM/dd/yyyy");
	    return format.format(date);
  }
  
  
}