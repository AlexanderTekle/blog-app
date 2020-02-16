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

    
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    long time = System.currentTimeMillis();
    Key key = KeyFactory.createKey("BlogPost", time);
    
    Entity postEntity = new Entity(key);
    postEntity.setProperty("Title", title);
    postEntity.setProperty("Content", content);
    postEntity.setProperty("timestamp", time);
    datastore.put(postEntity);
    
    grabPosts(req, resp, datastore);
    
    //out.println("Persisted email");
    
    //name
    //date
    //title
    //content
    
    
  }
  
  public void grabPosts(HttpServletRequest req, HttpServletResponse resp, DatastoreService datastore) throws IOException, ServletException {
	  String[] postTitles = new String[3];
	  String[] postContents = new String[3];
	  String[] postTimes = new String[3];
	  int i=0;
	  
	  for (Entity entity : datastore.prepare(new Query("BlogPost")).asIterable()) {
		  postTitles[i] = (String) entity.getProperty("Title");
		  postContents[i] = (String) entity.getProperty("Content");		  
		  postTimes[i] = convertTime((Long) entity.getProperty("timestamp"));	
		  i++;
		  if (i >= 3)
			  break;
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