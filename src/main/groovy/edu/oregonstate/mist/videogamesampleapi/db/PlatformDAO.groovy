package edu.oregonstate.mist.videogamesampleapi.db

import edu.oregonstate.mist.videogamesampleapi.core.Platform
import edu.oregonstate.mist.videogamesampleapi.mapper.PlatformMapper
import org.skife.jdbi.v2.sqlobject.SqlUpdate
import org.skife.jdbi.v2.sqlobject.SqlQuery
import org.skife.jdbi.v2.sqlobject.Bind
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper

@RegisterMapper(PlatformMapper)
public interface PlatformDAO {

    /**
     * GETs platforms from the database based on the parameters provided
     * If no query parameters are provided it returns all of the entries in the database
     * Multiple filters are allowed
     */
    @SqlQuery("""
        SELECT *
        FROM PLATFORMS
        WHERE RELEASE_YEAR LIKE '%'||:releaseYearFilter||'%'
        AND COMPUTER LIKE '%'||:computerFilter||'%'
        AND CONSOLE LIKE '%'||:consoleFilter||'%'
        """)
    List<Platform> getPlatforms(@Bind("releaseYearFilter") String releaseYearFilter,
                                @Bind("computerFilter") Boolean computerFilter,
                                @Bind("consoleFilter") Boolean consoleFilter)

    /**
     * GETs a single platform based on the id provided in the path
     */
    @SqlQuery("""
        SELECT *
        FROM PLATFORMS
        WHERE ID = :id
        """)
    Platform getPlatformById(@Bind("id") Integer id)

}