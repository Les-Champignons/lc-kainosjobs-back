package org.kainos.ea;

import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import io.dropwizard.Application;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.jsonwebtoken.Jwts;
import org.kainos.ea.controllers.ApplicantController;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.kainos.ea.auth.JwtAuthenticator;
import org.kainos.ea.auth.RoleAuthorisor;
import org.kainos.ea.controllers.AuthController;
import org.kainos.ea.daos.ApplicantDao;
import org.kainos.ea.daos.AuthDao;
import org.kainos.ea.services.ApplicantService;
import org.kainos.ea.models.JwtToken;
import org.kainos.ea.services.AuthService;
import javax.crypto.SecretKey;
import org.kainos.ea.controllers.JobRoleController;
import org.kainos.ea.daos.JobRoleDao;
import org.kainos.ea.services.JobRoleService;


public class KainosJobsApplication
        extends Application<KainosJobsConfiguration> {

    public static void main(final String[] args) throws Exception {
        new KainosJobsApplication().run(args);
    }

    @Override
    public String getName() {
        return "KainosJobs";
    }

    @Override
    public void initialize(final Bootstrap<KainosJobsConfiguration> bootstrap) {

        bootstrap.addBundle(new SwaggerBundle<KainosJobsConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(
                    final KainosJobsConfiguration configuration) {
                return configuration.getSwagger();
            }
        });
    }

    @Override
    public void run(final KainosJobsConfiguration configuration,
                    final Environment environment) {
        SecretKey jwtKey = Jwts.SIG.HS256.key().build();

        environment.jersey().register(new AuthDynamicFeature(
                new OAuthCredentialAuthFilter.Builder<JwtToken>()
                        .setAuthenticator(new JwtAuthenticator(jwtKey))
                        .setAuthorizer(new RoleAuthorisor())
                        .setPrefix("Bearer")
                        .buildAuthFilter()
        ));
        environment.jersey().register(RolesAllowedDynamicFeature.class);

        environment.jersey().register(new AuthController(
                new AuthService(
                        new AuthDao(), jwtKey
                )
        ));

        environment.jersey()
                .register(
                        new JobRoleController(
                                new JobRoleService(
                                        new JobRoleDao()
                                )
                        )
                );

        environment.jersey().register(new ApplicantController(
                new ApplicantService(new ApplicantDao())));
    }
}
