package edu.javacours.city.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("/check")
public class CheckPersonService {

//    private static  final Logger logger = LoggerFactory.getLogger(CheckPersonService.class);

//    public CheckPersonService() {
//        logger.info("CheckPersonService -  IMPLEMENT!!!!!!!");
//    }

//    @GET
//    public  String checkPerson(){
//        return "Simpl String";
//    }

    @GET
    @Path("/{id}")
    public  String checkPerson(@PathParam("id") int simpleId, @QueryParam("name") String simpleName){
        return "Simpl QueryParam" + simpleId + " : " + simpleName;
    }
}

//       localhost:8080/city-register-1.0/rest/check/101?name=value
//Результат будет : Simpl QueryParam101 : value