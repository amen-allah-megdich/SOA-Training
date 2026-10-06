package webservices;

import metiers.ModuleBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/Module")
public class ModuleRestAPI {
    static ModuleBusiness helper = new ModuleBusiness();
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllModules() {
        return Response.status(200)
                .entity(helper.getAllModules())
                .build();
    }
    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addModule(Module module) {
        if (helper.addModule(module)){
            return Response.status(201)
                    .entity("Module added successfully")
                    .build(); }
        else {
            return Response.status(400)
                    .entity("Erreur")
                    .build();
        }
    }
}
