package edu.javacours.city.web;


import edu.javacours.city.domain.PersonRequest;
import edu.javacours.city.domain.PersonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;

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
//    аннатация для преобразования  ответа в Jason
    @Produces(MediaType.APPLICATION_JSON)
    public PersonRequest checkPerson(){

        PersonRequest pr = new PersonRequest();
        pr.setSurName("Васильев");
        pr.setGivenName("Павел");
        pr.setPatronymic("Николаевич");
        pr.setDateOfBird(LocalDate.of(1995, 3, 18));
        pr.setStreetCode(1);
        pr.setBuilding("10");
        pr.setExtension("2");
        pr.setApartment("121");

        return pr;
    }
}

//       localhost:8080/city-register-1.0/rest/check/101?name=value
//Результат будет : Simpl QueryParam101 : value