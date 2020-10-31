package servlets.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import org.json.JSONObject;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {} 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		  StringBuffer jb = new StringBuffer();
		  String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) { }
		  
		  JSONObject jo2 = new JSONObject(jb.toString());
		  
		  String passes = "";
		  try {
				MessageDigest md = MessageDigest.getInstance("SHA-256");
				byte[] digest = md.digest(jo2.getString("password2").getBytes(StandardCharsets.UTF_8));
			    String sha256 = DatatypeConverter.printHexBinary(digest).toLowerCase();
			    passes = sha256;		
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			}
		  
		  String Username = jo2.getString("username2");
		  String Password = passes; 
		  
		  //System.out.println(Username);
		  //System.out.println(Password);
		  
		  helpers.com.Conection Con = new helpers.com.Conection();
		  ArrayList<String> data = Con.ST("SELECT * FROM users WHERE username='"+Username+"' AND password='"+Password+"'");
		  
		  if(data.isEmpty()) {
			  response.setContentType("application/json");
			  JSONObject ResJSONLogin = new JSONObject();
			  ResJSONLogin.put("message", "Error Try Again");
		      ResJSONLogin.put("handle", "300");

			  PrintWriter out = response.getWriter();
			  out.print(ResJSONLogin.toString());
		  } else {
		  String name = data.get(0);
		  String username = data.get(1);
		  String telf = data.get(2);
		  String id = data.get(3);
		  
		  System.out.println();
		  System.out.println(name);
		  System.out.println(username);
		  System.out.println(telf);
		  System.out.println(id);
		  
		  HttpSession session=request.getSession();  
		  session.setAttribute("name",name);  
		  session.setAttribute("username",username);
		  session.setAttribute("telf",telf);
		  session.setAttribute("id",id);
		  String idSession = session.getId();
		  System.out.println(idSession);
		        
		  response.setContentType("application/json");
		  JSONObject ResJSONLogin = new JSONObject();
		  ResJSONLogin.put("message", "Welcome to your Session");
		  ResJSONLogin.put("handle", "200");
		  ResJSONLogin.put("name", name);
		  ResJSONLogin.put("username", username);
		  ResJSONLogin.put("telf", telf);
		  ResJSONLogin.put("id", id);

		  PrintWriter out = response.getWriter();
		  out.print(ResJSONLogin.toString());
			
		  }
	}
}
