package edu.javacours.city.web;


import edu.javacours.city.dao.PersonCheckDao;
import edu.javacours.city.dao.PoolConnectionBuilder;
import edu.javacours.city.domain.PersonRequest;
import edu.javacours.city.domain.PersonResponse;
import edu.javacours.city.exception.PersonCheckException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;

@Path("/check")
@Singleton
public class CheckPersonService {

    private PersonCheckDao dao;

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

    @PostConstruct
    public void init(){
        logger.info("DAO Init");
        dao = new PersonCheckDao();
//        dao.setConnectionBuilder(new DirectConnectionBuilder());
        dao.setConnectionBuilder(new PoolConnectionBuilder());
    }

//    @PreDestroy
//    public void destroy(){
//        logger.info("DESTROY");
//    }

    @POST
    //    аннатация для потребления  запроса  в Jason
    @Consumes(MediaType.APPLICATION_JSON)
    //    аннатация для преобразования  ответа в Jason
    @Produces(MediaType.APPLICATION_JSON)
    public PersonResponse checkPerson(PersonRequest personRequest) throws PersonCheckException {
        logger.info("personRequest  : " + personRequest.toString());


//        PersonResponse personResponse = new PersonResponse();
//        personResponse.setStatus("Respons Сформирован");
//        personResponse.setTemporal(true);
//        personResponse.setRegistered(true);


        return dao.checkPerson(personRequest);
    }
    //       отправка запроса через ЗщыеЬфт
    //      Результа должен быть такой
    // pictures/Снимок экрана от  2022-08-07 22-03-23.png




}

