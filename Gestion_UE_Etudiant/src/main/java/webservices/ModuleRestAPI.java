package webservices;
import entities.Module;
import metiers.ModuleBusiness;
import entities.UniteEnseignement;

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
    @Consumes(MediaType.APPLICATION_JSON)//input ot the web sevice
    @Produces(MediaType.APPLICATION_JSON)
    public Response addModule(Module module) {
        if (helper.addModule(module)){
            return Response.status(201)
                    .entity("module aded secsesfuly")
                    .build(); }
        else {
            return Response.status(400)
                    .entity("erreur")
                    .build();
        }
    }
    @Path("/delete/{matricule}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteModule( @PathParam("matricule") String matricule) {
        if (helper.deleteModule(matricule)) {
            return Response.status(200)
                    .entity("Module deleted successfully")
                    .build();
        }
        else
        {
            return Response.status(404)
                    .entity("Module introuvable")
                    .build(); }
    }
    @Path("/type/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModulesByType( @PathParam("type") Module.TypeModule type) {
        return Response.status(200)
                .entity(helper.getModulesByType(type))
                .build();
    }
    @Path("/ue/{code}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModulesByUE( @PathParam("code") int code) {
        UniteEnseignement ue = new UniteEnseignement();
        ue.setCode(code);
        return Response.status(200)
                .entity(helper.getModulesByUE(ue))
                .build(); }

}
