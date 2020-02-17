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
import com.google.appengine.api.datastore.Query;


@SuppressWarnings("serial")
@WebServlet(name = "createpostpage", value="/createpostpage")

public class CreatePostPage extends HttpServlet {
	
	  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		  req.getRequestDispatcher("/WEB-INF/post.jsp?username=username").forward(req, resp);
		  
	  }
}