package org.kainos.ea.unit_tests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kainos.ea.controllers.JobRoleController;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.mappers.JobRoleMapper;
import org.kainos.ea.models.JobRoleRequest;
import org.kainos.ea.responses.JobRoleResponse;
import org.kainos.ea.services.JobRoleService;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class JobRoleTest {
    private Statement mockStatement;
    private Connection mockConnection;
    private ResultSet mockResultSet;

    private JobRoleService jobRoleService;
    private JobRoleDao jobRoleDao;
    private JobRoleController jobRoleController;


    @BeforeEach
    public void setUp() {
        jobRoleService = Mockito.mock(JobRoleService.class);
        jobRoleDao = Mockito.mock(JobRoleDao.class);
        jobRoleController = new JobRoleController(jobRoleService);
    }

    @Test
    public void mapJobRoleRequestToJobRoleResponse_shouldReturnJobRoleResponse_whenJobRolesRequestsPassedIn()
            throws ParseException {
        List<JobRoleRequest> jobRoles = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

        jobRoles.add(new JobRoleRequest(1, "SE", "Belfast", dateFormat.parse("12-13-2020"), "design", "senior"));
        jobRoles.add(new JobRoleRequest(2, "UX Designer", "NY", dateFormat.parse("12-17-2024"), "design", "senior"));
        jobRoles.add(new JobRoleRequest(3, "Sales", "London", dateFormat.parse("12-17-2023"), "design", "senior"));

        List<JobRoleResponse> mappedJobRoles =
                JobRoleMapper.mapJobRoleRequestToJobRoleResponse(jobRoles);

        assertTrue(mappedJobRoles.stream()
                .allMatch(role -> role instanceof JobRoleResponse));
    }


    @Test
    public void getAllJobRoles_shouldReturnInternalServerError_whenSQLExceptionThrown() throws SQLException {

        when(jobRoleService.getAllJobRoles()).thenThrow(new SQLException("Database error"));

        Response response = jobRoleController.getAllJobRoles();

        assertEquals(500, response.getStatus());
        assertEquals("Database error", response.getEntity());
    }

    @Test
    public void getAllJobRoles_shouldReturnOkResponse_whenJobRolesExist() throws ParseException, SQLException {
        List<JobRoleResponse> jobRolesResponse = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

        jobRolesResponse.add(new JobRoleResponse(1, "SE", "Belfast", dateFormat.parse("12-13-2020"), "design", "senior"));
        jobRolesResponse.add(new JobRoleResponse(2, "UX Designer", "NY", dateFormat.parse("12-17-2024"), "design", "senior"));
        jobRolesResponse.add(new JobRoleResponse(3, "Sales", "London", dateFormat.parse("12-17-2023"), "design", "senior"));

        when(jobRoleService.getAllJobRoles()).thenReturn(jobRolesResponse);

        Response response = jobRoleController.getAllJobRoles();

        assertNotNull(response, "Response should not be null");
        assertEquals(200, response.getStatus());
        assertEquals(jobRolesResponse, response.getEntity());
    }
}
