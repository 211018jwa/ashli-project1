package com.revature.controller;

import java.security.InvalidParameterException;

import javax.security.auth.login.FailedLoginException;

import com.revature.dto.MessageDTO;
import com.revature.exception.ReimbursementNotFoundException;
//import com.revature.exception.UnauthorizedException;
import com.revature.exception.UnauthorizedException;

import io.javalin.Javalin;

public class ExceptionMap {

	public void mapExceptions(Javalin app) {
		app.exception(FailedLoginException.class, (e, ctx) -> {
			ctx.status(400);
			ctx.json(new MessageDTO(e.getMessage()));
		});
		
		app.exception(UnauthorizedException.class, (e, ctx) -> {
			ctx.status(401); //401 = unauthorized 
			ctx.json(new MessageDTO(e.getMessage()));
		});
		
		app.exception(ReimbursementNotFoundException.class, (e, ctx) -> {
			ctx.status(404);
			ctx.json(new MessageDTO(e.getMessage()));
		});
		app.exception(InvalidParameterException.class, (e, ctx) -> {
			ctx.json(e);
			ctx.status(400);
		});
	}
	
}
