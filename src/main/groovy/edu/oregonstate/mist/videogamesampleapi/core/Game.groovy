package edu.oregonstate.mist.videogamesampleapi.core

import edu.oregonstate.mist.videogamesampleapi.core.Platform
import java.sql.Date

/**
 * Game representation
 */

class Game {
    Integer id
    Integer releaseYear
    Float price
    String title
    String publisher
    String genre
    Date dateCreated
    Date lastUpdated
    List<Platform> compatiblePlatforms
}
