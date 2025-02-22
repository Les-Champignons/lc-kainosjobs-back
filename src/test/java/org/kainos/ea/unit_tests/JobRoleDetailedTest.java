package org.kainos.ea.unit_tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kainos.ea.controllers.JobRoleController;
import org.kainos.ea.daos.JobRoleDao;
import org.kainos.ea.exceptions.DoesNotExistException;
import org.kainos.ea.models.JobRoleDetailedParameters;
import org.kainos.ea.responses.JobRoleDetailedResponse;
import org.kainos.ea.services.JobRoleService;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class JobRoleDetailedTest {

    private JobRoleService jobRoleService;
    private JobRoleController jobRoleController;
    private JobRoleDao jobRoleDao;

    @BeforeEach
    public void setUp() {
        jobRoleService = Mockito.mock(JobRoleService.class);
        jobRoleController = new JobRoleController(jobRoleService);
        jobRoleDao = Mockito.mock(JobRoleDao.class);
    }

    @Test
    public void getDetailedJobRole_shouldReturnOkResponse_whenJobRoleExists()
            throws
            SQLException, DoesNotExistException {
        int jobId = 1;
        JobRoleDetailedResponse jobRoleDetailedResponse = new JobRoleDetailedResponse(
                jobId,
                "SE",
                "Belfast",
                "design",
                new JobRoleDetailedParameters(
                        "create good code, 50k a year",
                        "create apis",
                        "exampleurl.com",
                        "engineer",
                        "Belfast",
                        new Date(2025, 1, 8),
                        2
                ));

        when(jobRoleService.getDetailedJobRole(jobId)).thenReturn(jobRoleDetailedResponse);

        Response response = jobRoleController.getDetailedJobRole(jobId);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(jobRoleDetailedResponse, response.getEntity());
    }

    @Test
    public void getDetailedJobRole_shouldReturnInternalServerError_whenSQLExceptionThrown()
            throws SQLException, DoesNotExistException {
        int jobId = 1;

        when(jobRoleService.getDetailedJobRole(jobId)).thenThrow(new SQLException("Database error"));

        Response response = jobRoleController.getDetailedJobRole(jobId);

        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        assertEquals("Database error", response.getEntity());
    }

    @Test
    public void getDetailedJobRole_shouldThrowDoesNotExistException_whenDetailedJobRoleDoesNotExist()
            throws DoesNotExistException, SQLException {
        int id = 200;

        when(jobRoleDao.getJobRoleInformationById(id)).thenReturn(null);

        JobRoleService jobRoleServiceTemp = new JobRoleService(jobRoleDao);

        assertThrows(DoesNotExistException.class, () -> jobRoleServiceTemp.getDetailedJobRole(id));
    }

}

