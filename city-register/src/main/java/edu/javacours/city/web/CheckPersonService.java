package edu.javacours.city.web;


import edu.javacours.city.domain.PersonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
//    аннатация для преобразования  ответа в Jason
    @Produces(MediaType.APPLICATION_JSON)
    public PersonResponse checkPerson(@PathParam("id") int simpleId, @QueryParam("name") String simpleName){

        return new PersonResponse();
    }
}

//       localhost:8080/city-register-1.0/rest/check/101?name=value
//Результат будет : Simpl QueryParam101 : value