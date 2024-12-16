package org.kainos.ea;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;

public class KainosJobsApplication extends Application<KainosJobsConfiguration> {

    public static void main(final String[] args) throws Exception {
        new KainosJobsApplication().run(args);
    }

    @Override
    public String getName() {
        return "KainosJobs";
    }

    @Override
    public void initialize(final Bootstrap<KainosJobsConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final KainosJobsConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
