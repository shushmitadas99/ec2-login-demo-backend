package com.revature.controller;

import com.revature.model.User;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class HelloWorldController implements Controller{
    @Override
    public void mapEndpoints(Javalin app) {
        //Mapping GET endpoint to the Javalin object
        //Takes 2 args: URI, Handler object
        //Handler is a functional interface (an interface with a single abstract method)
        //We can utilize lambdas to implement that functional interface
        //The lambda represents the code that will execute whenever the HTTP request is received
        app.get("/hello", (Context ctx) -> {
            //The Handler lambda has a parameter of the type "Context"
            //This provides the ability to extract information from the HTTP request
            //And to provide appropriate data (headers, body) and status codes in the response
            User user = new User(1, "test1", "testing123", "test@email.com");
            ctx.json(user); //return json user object
            ctx.status(200);
        });
    }
}
