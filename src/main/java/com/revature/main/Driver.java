package com.revature.main;

import com.revature.controller.AuthenticationController;
import com.revature.controller.Controller;
import com.revature.controller.HelloWorldController;
import io.javalin.Javalin;


public class Driver {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
        });

        //Controller array that has all controller objects to map endpoints
        //Controller is an abstract datatype (interface)
        //Controller defines a single abstract method "mapEndpoints"
        Controller[] controllers = {new HelloWorldController(), new AuthenticationController()};

        //iterating through array to map multiple controller endpoints
        for(int i = 0; i < controllers.length; i++){
            controllers[i].mapEndpoints(app);
        }

        app.start(8080);

        //PROGRAM FLOW:
        //Driver - instantiate AuthenticationController
        //AuthenticationController - invoke no-args constructor to create(instantiate) AuthenticationService object
        //   because AuthenticationService is a dependency for AuthenticationController
        //AuthenticationService - invoke no-args constructor to instantiate UserDao object because UserDao is a
        //   dependency of AuthenticationService
        //From there you make use of the various instance methods as necessary where one method calls another method and so on

    }
}
