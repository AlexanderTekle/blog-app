import java.io.IOException;
import java.io.PrintWriter;
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
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;




@SuppressWarnings("serial")
@WebServlet(name = "printemail", value="/printemail")
public class PrintEmail extends HttpServlet {


  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    PrintWriter out = resp.getWriter();
    String title = req.getParameter("title");
    String content = req.getParameter("content");
    String firstname = req.getParameter("FirstName");
    String lastname = req.getParameter("LastName");
    
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    long time = System.currentTimeMillis();
    Key key = KeyFactory.createKey("BlogPost", time);
    
    Entity postEntity = new Entity(key);
    postEntity.setProperty("Title", title);
    postEntity.setProperty("Content", content);
    postEntity.setProperty("timestamp", time);
    postEntity.setProperty("firstname", firstname);
    postEntity.setProperty("lastname", lastname);
    
    datastore.put(postEntity);
    
    grabPosts(req, resp, datastore);

    
    
  }
  
  public void grabPosts(HttpServletRequest req, HttpServletResponse resp, DatastoreService datastore) throws IOException, ServletException {
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
	  RequestDispatcher view = req.getRequestDispatcher("index.jsp");
      view.forward(req, resp);
	  
  }
  
  public String convertTime(long time){
	    Date date = new Date(time);
	    Format format = new SimpleDateFormat("MM/dd/yyyy");
	    return format.format(date);
	}
}