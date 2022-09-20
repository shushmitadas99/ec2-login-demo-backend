package com.revature.controller;

import com.revature.exceptions.InvalidLoginException;
import com.revature.model.User;
import com.revature.service.AuthenticationService;
import io.javalin.Javalin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthenticationController implements Controller{

    private AuthenticationService authService;  //property

    //no-args constructor
    public AuthenticationController(){
        this.authService = new AuthenticationService();
    }

    @Override
    public void mapEndpoints(Javalin app) {
        app.post("/login", ctx -> {
            //ctx.result("Login endpoint reached");  //Breadcrumb statements: temporary lines of code that help
            // you to understand what lines of code are actually working and being reached and to better debug the
            //application if you run into issue
            User user = ctx.bodyAsClass(User.class);//takes a JSON and places it into a newly instantiate User object

            //Extract username and password from the request body
            String username = user.getUsername();
            String password = user.getPassword();

            try{
                User loggedInUser = authService.login(username, password); //call authService (service layer)
                // and login successfully

                //Handling HTTP Sessions - logs you in and track of you as a session identified by a cookie
                HttpServletRequest req =  ctx.req;
                HttpSession session = req.getSession();
                session.setAttribute("logged_in_user", loggedInUser);

                ctx.json(loggedInUser);//send back loggedInUser as response
            }catch (InvalidLoginException e){ //unsuccessful login would enter catch block
                ctx.result(e.getMessage());
                ctx.status(400);
            }

        });
        app.post("/logout", ctx -> {
            HttpServletRequest req =  ctx.req;

            HttpSession session = req.getSession();
            session.invalidate();

            ctx.result("Logged out successfully!");
        });

        app.get("/current-user", ctx -> {
            HttpServletRequest req =  ctx.req;

            HttpSession session = req.getSession();
            User myUser = (User) session.getAttribute("logged_in_user"); //Downcast the Object return type to User
            //The underlying object is still a User object

            if (myUser == null){
                ctx.result("You are not logged in!");
                ctx.status(404);
            }else{
                ctx.json(myUser);
                ctx.status(200);
            }
        });
    }
}
