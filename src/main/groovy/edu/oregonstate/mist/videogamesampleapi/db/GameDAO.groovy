package edu.oregonstate.mist.videogamesampleapi.db

import edu.oregonstate.mist.videogamesampleapi.core.Game
import edu.oregonstate.mist.videogamesampleapi.core.Platform
import edu.oregonstate.mist.videogamesampleapi.mapper.GameMapper
import edu.oregonstate.mist.videogamesampleapi.mapper.PlatformMapper
import org.skife.jdbi.v2.sqlobject.SqlUpdate
import org.skife.jdbi.v2.sqlobject.SqlQuery
import org.skife.jdbi.v2.sqlobject.Bind
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper

@RegisterMapper(GameMapper)
public interface GameDAO {

    @SqlQuery("""
        SELECT *
        FROM GAMES WHERE
        RELEASE_YEAR LIKE '%'||:releaseYearFilter||'%'
        AND PUBLISHER LIKE '%'||:publisherFilter||'%'
        """)
    List<Game> allGames(@Bind("releaseYearFilter") String releaseYearFilter,
                        @Bind("publisherFilter") String publisherFilter)

    @SqlQuery("""
        SELECT *
        FROM GAMES
        WHERE ID = :id
        """)
    Game gameById(@Bind("id") Integer id)

  /*  @SqlQuery("""
        SELECT *
        FROM PLATFORMS
        WHERE ID
        IN (SELECT PLATFORM_ID FROM GAME_PLATFORM WHERE GAME_ID = :gameId)
        """)
    List<Platform> grabCompatiblePlatforms(@Bind("gameId") Integer gameId)*/

}