package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.revature.dto.LoginDTO;
import com.revature.dto.MessageDTO;
import com.revature.model.User;
import com.revature.service.UserService;

import io.javalin.Javalin;
import io.javalin.http.Handler;


//authentication= providing credentials to identify who you are
public class AuthenticationController implements Controller {

	
	
	//Whenever working with ENDPOINTS, ALWAYS ask yourself what information is needed to send to
	//the server?
	//to log into something, we need to send
		//1.username
		//2.password
	//Where should this info be contained inside the HTTP request?
		//1.request body --> discrete location for login credentials (JSON)
		//2.path parameter(required) --> http://localhost:8080/login/employees
		//3.query parameter(optional) --> the key value pairs
		//4.form data --> discrete location for login credentials (key/value pair)
	//How do I receive this info inside of endpoint handler? (dto)
		//1.JSON in request body? cts.bodyAsClass
		//2. path param? ctx.pathParam(<path param name>)
		//3. query param? ctsx.queryParam(<query param name>)
		//4. form data? ctx.formParam(<key name>)
	private UserService userService;
	
	public AuthenticationController() {
		this.userService = new UserService ();
	}
	
	
	private Handler login = (ctx) -> {
		//use request body(JSON) for log in 
		//DTO is needed to put that info somewhere
		LoginDTO loginDTO = ctx.bodyAsClass(LoginDTO.class);
//		System.out.println(loginDTO.getUsername()); //works
//		System.out.println(loginDTO.getPassword()); //works
		
		//returns a user obj
		User user = this.userService.getUserByUsernameAndPassword(loginDTO.getUsername(),loginDTO.getPassword());
		//now use HTTPSession to track who is sending requests to the server
		//associated through the use of cookies
		//whenever an HttpSession object is created for a request, a cookie will be sent to the client from the server
		//the client uses this cookie and any other requests to identify themselves
		
		HttpServletRequest req = ctx.req;
		
//		if(req.getSession(false) != null) { //getsession(false) wont create a new session if one doesn't exist; it'll return null
//			ctx.json(new MessageDTO("You are already logged in."));
//		}else {		
		HttpSession session = req.getSession(); //returns current session associated w this request or creates a NEW one if one doesnt alr exist
		session.setAttribute("currentuser", user); //tells you which user is logged in
//		ctx.json(new MessageDTO(user + " is now logged in.")); cannot have 2 ctx.json
		ctx.json(user); 
//		}
	};
	
	private Handler logout = (ctx) -> {
		HttpServletRequest req = ctx.req;
		
		if(req.getSession(false) == null || req.getSession(false).getAttribute("currentuser") == null) { 
			ctx.json(new MessageDTO("You cannot log out due to not being logged in."));
		}else {
			User user = (User) req.getSession(false).getAttribute("currentuser");
				req.getSession(false).invalidate(); //actually logs you out
//				ctx.json(new MessageDTO(user + " is now logged out."));
				ctx.json(user);	
		}
		
	};
	
	private Handler checkIfLoggedIn = (ctx) -> {
		HttpSession session = ctx.req.getSession();
		
		// Check if session.getAttribute("currentuser"); is null or not
		if (!(session.getAttribute("currentuser") == null)) {
			ctx.json(session.getAttribute("currentuser"));
			ctx.status(200);
		} else {
			ctx.json(new MessageDTO("User is not logged in"));
			ctx.status(401);
		}
	};

	@Override
	public void mapEndpoints(Javalin app) {
		app.post("/login", login);
		app.post("/logout", logout);
		app.get("/checklogin", checkIfLoggedIn);
	}
	
	
	
	
}


