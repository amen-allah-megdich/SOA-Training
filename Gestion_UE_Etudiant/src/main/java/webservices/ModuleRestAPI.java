package webservices;
import entities.Module;
import metiers.ModuleBusiness;
import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

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
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModulesByType( @PathParam("type") Module.TypeModule type) {
        return Response.status(200)
                .entity(helper.getModulesByType(type))
                .build();
    }

    @Path("/ue/code/{code}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModulesByUE(@PathParam("code") int code) {
        UniteEnseignementBusiness ueb = new UniteEnseignementBusiness();
        UniteEnseignement ue = ueb.getUEByCode(code);
        if (ue == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("UE introuvable")
                    .build();
        }
        return Response.status(Response.Status.OK)
                .entity(helper.getModulesByUE(ue))
                .build();
    }
    @Path("/ue/matricule/{mat}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModuleByMatricule(@PathParam("mat") String mat){
        return Response.status(201).entity(helper.getModuleByMatricule(mat)).build();

    }
    @Path("/update/{mat}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateModule(@PathParam("mat")String matricule, Module updatedModule){
        if (helper.updateModule(matricule,updatedModule)) {
            return Response.status(200)
                    .entity("Updated successfully")
                    .build();
        } else {
            return Response.status(404)
                    .entity(" introuvable")
                    .build();
        }
    }

}
