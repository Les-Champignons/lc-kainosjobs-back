package org.kainos.ea;

import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import io.dropwizard.Application;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import org.kainos.ea.controllers.JobRoleController;
import org.kainos.ea.dao.JobRoleDao;
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
        environment.jersey()
                .register(
                        new JobRoleController(
                                new JobRoleService(
                                        new JobRoleDao()
                                )
                        )
                );
    }
}
