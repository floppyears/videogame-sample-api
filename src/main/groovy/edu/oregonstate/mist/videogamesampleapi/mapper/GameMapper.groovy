package edu.oregonstate.mist.videogamesampleapi.mapper

/**
 * Mapper for the Game class
 */

import edu.oregonstate.mist.videogamesampleapi.core.Game
import org.skife.jdbi.v2.StatementContext
import org.skife.jdbi.v2.tweak.ResultSetMapper
import java.sql.ResultSet
import java.sql.SQLException

public class GameMapper implements ResultSetMapper<Game> {
    public Game map(int i, ResultSet rs, StatementContext sc) throws SQLException {
        new Game(
                id:                     rs.getInt('ID'),
                releaseYear:            rs.getInt('RELEASE_YEAR'),
                price:                  rs.getFloat('PRICE'),
                title:                  rs.getString('TITLE'),
                publisher:              rs.getString('PUBLISHER'),
                genre:                  rs.getString('GENRE'),
                dateCreated:            rs.getDate('DATE_CREATED'),
                lastUpdated:            rs.getDate('LAST_UPDATED'),
        )
    }
}