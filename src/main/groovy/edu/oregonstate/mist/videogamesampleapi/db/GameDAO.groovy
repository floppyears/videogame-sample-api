package edu.oregonstate.mist.videogamesampleapi.db

import edu.oregonstate.mist.videogamesampleapi.core.Game
import edu.oregonstate.mist.videogamesampleapi.mapper.GameMapper
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

}