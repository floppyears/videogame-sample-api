package edu.oregonstate.mist.videogamesampleapi.resources

import edu.oregonstate.mist.api.AuthenticatedUser

/**
 * Created by george on 12/22/15.
 * Resource for the Platform object
 */

import edu.oregonstate.mist.videogamesampleapi.core.ErrorPOJO
import edu.oregonstate.mist.videogamesampleapi.core.Platform
import edu.oregonstate.mist.videogamesampleapi.db.PlatformDAO
import io.dropwizard.auth.Auth
import io.dropwizard.jersey.params.IntParam
import com.google.common.base.Optional

import javax.print.attribute.standard.Media
import javax.validation.Valid
import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.QueryParam
import javax.ws.rs.Produces
import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriInfo
import javax.ws.rs.core.Response.ResponseBuilder
import org.skife.jdbi.v2.exceptions.UnableToExecuteStatementException

@Path("/platforms")
@Produces(MediaType.APPLICATION_JSON)
class PlatformResource {

    private final PlatformDAO platformDAO

    @Context
    UriInfo uriInfo

    public PlatformResource(PlatformDAO platformDAO) {
        this.platformDAO = platformDAO
    }

    /*
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

    /*
     * Returns a single entry based on the id in the path
     */
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response platformById(@PathParam("id") Integer id) {
        Platform returnPlatform = platformDAO.getPlatformById(id)
        Response returnResponse

        returnResponse = Response.ok(returnPlatform).build()
        returnResponse
    }

}