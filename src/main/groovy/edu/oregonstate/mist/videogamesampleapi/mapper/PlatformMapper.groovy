package edu.oregonstate.mist.videogamesampleapi.mapper

/**
 * Created by george on 12/22/15.
 * Mapper for Platform to database
 */

import edu.oregonstate.mist.videogamesampleapi.core.Platform
import org.skife.jdbi.v2.StatementContext
import org.skife.jdbi.v2.tweak.ResultSetMapper
import java.sql.ResultSet
import java.sql.SQLException

public class PlatformMapper implements ResultSetMapper<Platform> {
    public Platform map(int i, ResultSet rs, StatementContext sc) throws SQLException {
        new Platform(
                id:             rs.getInt('ID'),
                releaseYear:    rs.getInt('RELEASE_YEAR'),
                name:           rs.getString('TITLE'),
                manufacturer:   rs.getString('MANUFACTURER'),
                computer:       rs.getBoolean('COMPUTER'),
                console:        rs.getBoolean('CONSOLE')
        )
    }
}