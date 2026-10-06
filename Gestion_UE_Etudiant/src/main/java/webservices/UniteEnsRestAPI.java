package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("/ue")
public class UniteEnsRestAPI {
    static UniteEnseignementBusiness helper= new UniteEnseignementBusiness();
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListUe(){
        return Response.status(200)
                .entity(helper.getListeUE())
                .build();
    }

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)//input ot the web sevice
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUnitEnseignement(UniteEnseignement ue){
        if (helper.addUniteEnseignement(ue)){
            return Response.status(201).entity("added secsesfly").build();
        }
        else{
            return Response.status(400).entity("Erreur").build();
        }
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{code}")
    public Response getUEByCode(@PathParam("code") int code){
        return Response.status(200)
                .entity(helper.getUEByCode(code))
                .build();
    }



    @Path("/update/{code}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUnitEnseignement(@PathParam("code") int code, UniteEnseignement ue) {
        if (helper.updateUniteEnseignement(code, ue)) {
            return Response.status(200)
                    .entity("Updated successfully")
                    .build();
        } else {
            return Response.status(404)
                    .entity("UE introuvable")
                    .build();
        }
    }
    @Path("/delete/{codeUE}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUnitEnseignement(@PathParam("codeUE") int codeUE) {

        if (helper.deleteUniteEnseignement(codeUE)) {
            return Response.status(200)
                    .entity("Deleted successfully")
                    .build();
        } else {
            return Response.status(404)
                    .entity("UE introuvable")
                    .build();
        }
    }

}
