package edu.oregonstate.mist.videogamesampleapi.resources

import edu.oregonstate.mist.api.AuthenticatedUser
import edu.oregonstate.mist.api.Error

/**
 * Resource for the Platform object
 */

import edu.oregonstate.mist.videogamesampleapi.core.Game
import edu.oregonstate.mist.videogamesampleapi.core.Platform
import edu.oregonstate.mist.videogamesampleapi.db.GameDAO
import edu.oregonstate.mist.api.Resource
import edu.oregonstate.mist.videogamesampleapi.db.PlatformDAO
import io.dropwizard.auth.Auth

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
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriInfo
import javax.ws.rs.core.Response.ResponseBuilder
import org.skife.jdbi.v2.exceptions.UnableToExecuteStatementException

@Path("/games")
@Produces(MediaType.APPLICATION_JSON)
class GameResource extends Resource {

    private final GameDAO gameDAO
    private final PlatformDAO platformDAO

    public GameResource(GameDAO gameDAO, PlatformDAO platformDAO) {
        this.gameDAO = gameDAO
        this.platformDAO = platformDAO
    }

    /**
     * GETs all of the Games that are not filtered out by the parameters
     * If no parameters are provided it returns all of the entries
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response allGames(
            @Auth AuthenticatedUser authenticatedUser,
            @QueryParam("releaseYear") String releaseYear,
            @QueryParam("publisher") String publisher) {

        ResponseBuilder responseBuilder
        List<Game> returnList = gameDAO.allGames(releaseYear, publisher)

        returnList.each {
            it.compatiblePlatforms = platformDAO.grabCompatiblePlatforms(it.id)
        }

        responseBuilder = ok(returnList)
        responseBuilder.build()
    }

    /**
     * Returns a single entry based on the id in the path
     */
    @Path("/{id : \\d+}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response gameById(@PathParam("id") Integer id) {
        Game returnGame = gameDAO.gameById(id)
        ResponseBuilder responseBuilder

        returnGame.compatiblePlatforms = platformDAO.grabCompatiblePlatforms(id)

        if (!returnGame) {
            responseBuilder = notFound()
        }
        else {
            responseBuilder = ok(returnGame)
        }
        responseBuilder.build()
    }
}
