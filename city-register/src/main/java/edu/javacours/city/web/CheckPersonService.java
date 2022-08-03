package edu.javacours.city.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/check")
public class CheckPersonService {

//    private static  final Logger logger = LoggerFactory.getLogger(CheckPersonService.class);

//    public CheckPersonService() {
//        logger.info("CheckPersonService -  IMPLEMENT!!!!!!!");
//    }

    @GET
    public  String checkPerson(){
//        logger.info("CheckPersonService -  method checkPerson!!!!!!!!!");
        return "Simpl String";
    }
}
