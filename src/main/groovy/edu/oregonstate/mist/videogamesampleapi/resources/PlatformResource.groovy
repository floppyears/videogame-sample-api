package edu.oregonstate.mist.videogamesampleapi.resources

import edu.oregonstate.mist.api.AuthenticatedUser
import edu.oregonstate.mist.api.Error

/**
 * Resource for the Platform object
 */

import edu.oregonstate.mist.videogamesampleapi.core.Platform
import edu.oregonstate.mist.videogamesampleapi.db.PlatformDAO
import edu.oregonstate.mist.api.Resource
import io.dropwizard.auth.Auth

import javax.validation.Valid
import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.QueryParam
import javax.ws.rs.Produces
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriInfo
import javax.ws.rs.core.Response.ResponseBuilder
import org.skife.jdbi.v2.exceptions.UnableToExecuteStatementException

@Path("/platforms")
@Produces(MediaType.APPLICATION_JSON)
class PlatformResource extends Resource {

    private final PlatformDAO platformDAO

    public PlatformResource(PlatformDAO platformDAO) {
        this.platformDAO = platformDAO
    }

    /**
     * GETs all of the platforms that are not filtered out by the parameters
     * If no parameters are provided it returns all of the entries
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Platform> getPlatforms(
            @Auth AuthenticatedUser authenticatedUser,
            @QueryParam("releaseYear") String releaseYear,
            @QueryParam("computer") Boolean computer,
            @QueryParam("console") Boolean console) {
        platformDAO.getPlatforms(releaseYear, computer, console)
    }

    /**
     * Returns a single entry based on the id in the path
     */
    @Path("/{id : \\d+}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response platformById(@PathParam("id") Integer id) {
        Platform returnPlatform = platformDAO.getPlatformById(id)
        ResponseBuilder responseBuilder

        if(returnPlatform == null) {
           responseBuilder = notFound()
        }
        else {
            responseBuilder = ok(returnPlatform)
        }
        responseBuilder.build()
    }

    /**
     * POSTs a new game to the database
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postPlatform(@Valid Platform newPlatform) {
        ResponseBuilder responseBuilder
        Integer newId = platformDAO.grabNextPlatformId()
        try {
            platformDAO.postPlatform(newId, newPlatform.getReleaseYear(), newPlatform.getName(), newPlatform.getManufacturer(), newPlatform.getComputer(), newPlatform.getConsole())
            responseBuilder = ok(newPlatform)
            responseBuilder.build()
        } catch (UnableToExecuteStatementException err) {
            String constraintError = err.getMessage()
        }
    }

    @Path("/{id : \\d+}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlatform(@PathParam("id") Integer id) {
        platformDAO.deleteById(id)
        Response.ok().build()
    }
}