package edu.oregonstate.mist.videogamesampleapi

import edu.oregonstate.mist.api.Configuration
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid
import javax.validation.constraints.NotNull
import io.dropwizard.db.DataSourceFactory

/**
 * An object representation of the YAML configuration file.
 */
class VideogameApplicationConfiguration extends Configuration {
    DataSourceFactory database = new DataSourceFactory()
}