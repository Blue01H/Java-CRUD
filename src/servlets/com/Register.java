package servlets.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.json.JSONObject;


@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Register() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*String Name = request .getParameter("name");
		String userName = request.getParameter("username");
		String Password = request.getParameter("password");
		String Telf = request.getParameter("telf");
		String ID = request.getParameter("id");
        String Passes = "";
        
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] digest = md.digest(Password.getBytes(StandardCharsets.UTF_8));
		    String sha256 = DatatypeConverter.printHexBinary(digest).toLowerCase();
		    Passes = sha256;		
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
	
	    helpers.com.Conection Con = new helpers.com.Conection();
		Con.ST("INSERT INTO users VALUES ('"+Name+"', '"+userName+"', '"+ID+"', '"+Telf+"', '"+Passes+"')");
		
		PrintWriter out = response.getWriter();
		out.print("Your User:" + userName + " Have been Created Correctly");
        request.getRequestDispatcher("index.html").include(request, response); */ 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		  StringBuffer jb = new StringBuffer();
		  String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) { /*report an error*/ }
		  
		  JSONObject jo = new JSONObject(jb.toString());
		  
		  String passes = "";
		  try {
				MessageDigest md = MessageDigest.getInstance("SHA-256");
				byte[] digest = md.digest(jo.getString("password1").getBytes(StandardCharsets.UTF_8));
			    String sha256 = DatatypeConverter.printHexBinary(digest).toLowerCase();
			    passes = sha256;		
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			}
		  
		  String Name = jo.getString("name1");
		  String Username = jo.getString("username1");
		  String Telf = jo.getString("telf1");
		  String Id = jo.getString("id1");
		  String Password = passes;
		  
		  helpers.com.Conection Con = new helpers.com.Conection();
		  Con.ST("INSERT INTO users VALUES ('"+Name+"', '"+Username+"', '"+Id+"', '"+Telf+"', '"+Password+"')");
		  
		  response.setContentType("application/json");
		  JSONObject ResJSON = new JSONObject();
		  ResJSON.put("message", "Register Complete");
		  ResJSON.put("handle", "200");

		  PrintWriter out = response.getWriter();
		  out.print(ResJSON.toString());
	}

}
