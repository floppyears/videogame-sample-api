package edu.oregonstate.mist.videogamesampleapi.db

import edu.oregonstate.mist.videogamesampleapi.core.Platform
import edu.oregonstate.mist.videogamesampleapi.mapper.PlatformMapper
import org.skife.jdbi.v2.sqlobject.SqlUpdate
import org.skife.jdbi.v2.sqlobject.SqlQuery
import org.skife.jdbi.v2.sqlobject.Bind
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper

@RegisterMapper(PlatformMapper)
public interface PlatformDAO {
    /*
     *GETs all platforms from the database
     *
     */
    @SqlQuery("""
        SELECT *
        FROM PLATFORMS
    """)
    List<Platform> getAllPlatforms()
}