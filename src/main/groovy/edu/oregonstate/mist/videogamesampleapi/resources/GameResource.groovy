package edu.oregonstate.mist.videogamesampleapi.resources

import edu.oregonstate.mist.api.AuthenticatedUser
import edu.oregonstate.mist.api.Error

/**
 * Resource for the Platform object
 */

import edu.oregonstate.mist.videogamesampleapi.core.Game
import edu.oregonstate.mist.videogamesampleapi.db.GameDAO
import edu.oregonstate.mist.api.Resource
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

    public GameResource(GameDAO gameDAO) {
        this.gameDAO = gameDAO
    }

    /**
     * GETs all of the Games that are not filtered out by the parameters
     * If no parameters are provided it returns all of the entries
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Game> allGames(
            @Auth AuthenticatedUser authenticatedUser,
            @QueryParam("releaseYear") String releaseYear,
            @QueryParam("publisher") String publisher) {
        gameDAO.allGames(releaseYear, publisher)
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

        if (!returnGame) {
            responseBuilder = notFound()
        }
        else {
            responseBuilder = ok(returnGame)
        }
        responseBuilder.build()
    }
}
