package service;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.jws.WebMethod;
import javax.jws.WebService;

@Path("/hola")
@ApplicationPath("/")
@WebService
public class HelloWorldService {

    @GET
    @Produces("text/plain")
    @WebMethod
    public String getClichedMessage() {
        return "Hello World";
    }
	
}

