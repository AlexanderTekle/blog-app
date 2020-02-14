import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;


@SuppressWarnings("serial")
@WebServlet(name = "printemail", value="/printemail")
public class PrintEmail extends HttpServlet {


  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    PrintWriter out = resp.getWriter();
    
    String email = req.getParameter("email");
    
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Entity emailEntity = new Entity("Email");
    emailEntity.setProperty("email", email);
    emailEntity.setProperty("timestamp", System.currentTimeMillis());
    datastore.put(emailEntity);
    
    out.println("Persisted email");
    
    
  }
}