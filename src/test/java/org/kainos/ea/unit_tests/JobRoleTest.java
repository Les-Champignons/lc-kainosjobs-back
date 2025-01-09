package org.kainos.ea.unit_tests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kainos.ea.controllers.JobRoleController;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.mappers.JobRoleMapper;
import org.kainos.ea.models.JobRoleRequest;
import org.kainos.ea.responses.JobRoleResponse;
import org.kainos.ea.services.JobRoleService;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    public void mapJobRoleRequestToJobRoleResponse_shouldReturnJobRoleResponse_whenJobRolesRequestsPassedIn() {
        List<JobRoleRequest> jobRoles = new ArrayList<>();

        jobRoles.add(new JobRoleRequest(1, "SE", "Belfast", new Date(2020, 12, 13), "design", "senior"));
        jobRoles.add(new JobRoleRequest(2, "UX Designer", "NY", new Date(2024, 17, 12), "design", "senior"));
        jobRoles.add(new JobRoleRequest(3, "Sales", "London", new Date(2023, 17, 12), "design", "senior"));

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
    public void getAllJobRoles_shouldReturnOkResponse_whenJobRolesExist() throws SQLException {
        List<JobRoleResponse> jobRolesResponse = new ArrayList<>();

        jobRolesResponse.add(new JobRoleResponse(1, "SE", "Belfast", new Date(2020, 13, 12), "design", "senior"));
        jobRolesResponse.add(new JobRoleResponse(2, "UX Designer", "NY", new Date(2024, 17, 12), "design", "senior"));
        jobRolesResponse.add(new JobRoleResponse(3, "Sales", "London", new Date(2023, 17, 12), "design", "senior"));

        when(jobRoleService.getAllJobRoles()).thenReturn(jobRolesResponse);

        Response response = jobRoleController.getAllJobRoles();

        assertNotNull(response, "Response should not be null");
        assertEquals(200, response.getStatus());
        assertEquals(jobRolesResponse, response.getEntity());
    }
}
