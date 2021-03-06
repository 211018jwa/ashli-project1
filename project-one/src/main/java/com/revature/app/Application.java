package com.revature.app;

import com.revature.controller.AuthenticationController;
import com.revature.controller.Controller;
import com.revature.controller.EmployeeController;
import com.revature.controller.ExceptionMap;
import com.revature.controller.ReimbursementController;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Application {

	public static void main(String[] args) {
		Javalin app = Javalin.create((config) -> {
			config.enableCorsForAllOrigins();
			config.addStaticFiles("static", Location.CLASSPATH);
			
		});
		
		mapControllers(app, new AuthenticationController(), new EmployeeController(), new ReimbursementController());	
		
		ExceptionMap map = new ExceptionMap();
		map.mapExceptions(app);
		
		app.start(8080);
	}
	
	
//	try {
//			Connection connection = JDBCUtility.getConnection();
//			System.out.println(connection);
//	} catch (SQLException e) {
//		e.printStackTrace();
//	}//connected
	
	//use var-args for controllers
	//this method calls all mapEndpoints
	public static void mapControllers( Javalin app, Controller...controllers) {
		for(int i = 0; i < controllers.length; i++) {
			controllers[i].mapEndpoints(app);
		}
	}
		

}
