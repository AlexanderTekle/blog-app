import java.io.IOException;
import java.io.PrintWriter;
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
public class PrintEmail extends HttpServlet {


  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    PrintWriter out = resp.getWriter();
    
    String title = req.getParameter("title");
    String content = req.getParameter("content");

    
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Entity postEntity = new Entity("BlogPost");
    postEntity.setProperty("Title", title);
    postEntity.setProperty("Content", content);
    postEntity.setProperty("timestamp", System.currentTimeMillis());
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
	  int i=0;
	  
	  for (Entity entity : datastore.prepare(new Query("BlogPost")).asIterable()) {
		  postTitles[i] = (String) entity.getProperty("Title");
		  postContents[i] = (String) entity.getProperty("Content");		  
		  i++;
		  if (i >= 3)
			  break;
	  }
	  req.setAttribute("titles", postTitles);
	  req.setAttribute("contents", postContents);
	  req.setAttribute("length",i);
	  RequestDispatcher view = req.getRequestDispatcher("index.jsp");
      view.forward(req, resp);
	  
  }
}