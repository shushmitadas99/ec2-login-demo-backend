package com.revature.controller;

import io.javalin.Javalin;

public interface Controller {
    //single abstract method
    //all controllers should have this method - they implement from this interface
    void mapEndpoints(Javalin app);

}
