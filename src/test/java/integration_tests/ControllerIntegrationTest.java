package integration_tests;

import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.KainosJobsApplication;
import org.kainos.ea.KainosJobsConfiguration;
import org.kainos.ea.responses.JobRoleResponse;

import javax.ws.rs.client.Client;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class ControllerIntegrationTest {

    private static final DropwizardAppExtension<KainosJobsConfiguration> APP =
            new DropwizardAppExtension<>(KainosJobsApplication.class);

    @Test
    void getEmployees_shouldReturnListOfEmployees() {
        Client client = APP.client();

        List<JobRoleResponse> response = client
                .target("http://localhost:8080/api/job-roles")
                .request()
                .get(List.class);

        Assertions.assertFalse(response.isEmpty());
    }
}
