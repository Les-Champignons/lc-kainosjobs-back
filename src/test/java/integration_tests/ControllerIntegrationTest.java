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
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(DropwizardExtensionsSupport.class)
public class ControllerIntegrationTest {

    private static final DropwizardAppExtension<KainosJobsConfiguration> APP =
            new DropwizardAppExtension<>(KainosJobsApplication.class);

    @Test
    void getJobRoles_shouldReturnListOfJobRoles() {
        Client client = APP.client();

        List<JobRoleResponse> response = client
                .target("http://localhost:8080/api/job-roles")
                .request()
                .get(List.class);

        Assertions.assertTrue(response instanceof List);
        Assertions.assertFalse(response.isEmpty());
    }

    @Test
    void getDetailedJobRole_shouldReturnOKResponseCodeWhenGivenValidId() {
        Client client = ClientBuilder.newClient();
        int id = 1;
        Response response = client
                .target("http://localhost:8080/api/job-roles/" + id)
                .request()
                .get();

        assertEquals(200, response.getStatus());
    }
}
