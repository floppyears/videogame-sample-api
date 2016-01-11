package edu.oregonstate.mist.videogamesampleapi

import edu.oregonstate.mist.api.Configuration
import edu.oregonstate.mist.api.Resource
import edu.oregonstate.mist.api.InfoResource
import edu.oregonstate.mist.api.AuthenticatedUser
import edu.oregonstate.mist.api.BasicAuthenticator
import edu.oregonstate.mist.videogamesampleapi.db.PlatformDAO
import edu.oregonstate.mist.videogamesampleapi.resources.PlatformResource
import edu.oregonstate.mist.videogamesampleapi.resources.SampleResource
import io.dropwizard.Application
import io.dropwizard.jdbi.DBIFactory
import org.skife.jdbi.v2.DBI
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import io.dropwizard.auth.AuthFactory
import io.dropwizard.auth.basic.BasicAuthFactory

/**
 * Main application class.
 */
class VideogameApplication extends Application<VideogameApplicationConfiguration> {
    /**
     * Initializes application bootstrap.
     *
     * @param bootstrap
     */
    @Override
    public void initialize(Bootstrap<VideogameApplicationConfiguration> bootstrap) {}

    /**
     * Parses command-line arguments and runs the application.
     *
     * @param configuration
     * @param environment
     */
    @Override
    public void run(VideogameApplicationConfiguration configuration, Environment environment) {
        Resource.loadProperties('resource.properties')

        final DBIFactory factory = new DBIFactory()
        final DBI jdbi = factory.build(environment, configuration.getDatabase(), "jdbi")

        final PlatformDAO platformDAO = jdbi.onDemand(PlatformDAO.class)

        environment.jersey().register(new SampleResource())
        environment.jersey().register(new InfoResource())
        environment.jersey().register(new PlatformResource(platformDAO))

        environment.jersey().register(
                AuthFactory.binder(
                        new BasicAuthFactory<AuthenticatedUser>(
                                new BasicAuthenticator(configuration.getCredentialsList()),
                                'VideogameApplication',
                                AuthenticatedUser.class)))
    }

    /**
     * Instantiates the application class with command-line arguments.
     *
     * @param arguments
     * @throws Exception
     */
    public static void main(String[] arguments) throws Exception {
        new VideogameApplication().run(arguments)
    }
}
