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

    private static  final Logger logger = LoggerFactory.getLogger(CheckPersonService.class);

    @GET
    @Path("/{id}")
    //    аннатация о том что ответ будет преобразовываться в тип  в JSON
    @Produces(MediaType.APPLICATION_JSON)
    public PersonResponse checkPerson(@PathParam("id") int simpleId, @QueryParam("name") String simpleName){
        return new PersonResponse();
    }
    //       localhost:8080/city-register-1.0/rest/check/101?name=value
    //Результат будет : Simpl QueryParam101 : value



    
    @POST
    //    аннатация для потребления  запроса  в Jason
    @Consumes(MediaType.APPLICATION_JSON)
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

        logger.info("PersonRequest  : " + pr);
        return pr;
    }
}

        //       отправка запроса через ЗщыеЬфт
        //      Результа должен быть такой
        // pictures/Снимок экрана от 2022-08-07 20-27-29.png