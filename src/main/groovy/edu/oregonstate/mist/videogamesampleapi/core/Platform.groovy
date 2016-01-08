package edu.oregonstate.mist.videogamesampleapi.core

import java.sql.Date

/**
 * Platform representation
 */

class Platform {
    Integer id
    Integer releaseYear
    String name
    String manufacturer
    Boolean computer
    Boolean console
    Date dateCreated
    Date lastUpdated
}