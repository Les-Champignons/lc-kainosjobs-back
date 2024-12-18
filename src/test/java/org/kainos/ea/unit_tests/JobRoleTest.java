package org.kainos.ea.unit_tests;


import org.junit.jupiter.api.Test;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.mappers.JobRoleMapper;
import org.kainos.ea.models.JobRoleRequest;
import org.kainos.ea.responses.JobRoleResponse;
import org.kainos.ea.services.JobRoleService;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JobRoleTest {
    private Statement mockStatement;
    private Connection mockConnection;
    private ResultSet mockResultSet;

    JobRoleService jobRoleService = Mockito.mock(JobRoleService.class);
    JobRoleMapper jobRoleMapper = Mockito.mock(JobRoleMapper.class);


    @Test
    public void mapJobRoleRequestToJobRoleResponse_shouldReturnJobRoleResponse_whenJobRolesRequestsPassedIn()
            throws ParseException {
        List<JobRoleRequest> jobRoles = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

        jobRoles.add(new JobRoleRequest(1, "SE", "Belfast", dateFormat.parse("12-13-2020"), "design", "senior"));
        jobRoles.add(new JobRoleRequest(2, "UX Designer", "NY", dateFormat.parse("12-17-2024"), "design", "senior"));
        jobRoles.add(new JobRoleRequest(3, "Sales", "London", dateFormat.parse("12-17-2023"), "design", "senior"));

        List<JobRoleResponse> mappedJobRoles =
                jobRoleMapper.mapJobRoleRequestToJobRoleResponse(jobRoles);

        assertTrue(mappedJobRoles.stream()
                .allMatch(role -> role instanceof JobRoleResponse));
    }

    @Test
    public void jobRoleServiceGetAllJobRoles_shouldReturnJobRoleResponses_whenJobRolesResponsesPassedIn()
            throws ParseException, SQLException, SQLException {
        List<JobRoleResponse> jobRolesResponse = new ArrayList<>();
        List<JobRoleRequest> jobRolesRequest = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

        jobRolesRequest.add(new JobRoleRequest(1, "SE", "Belfast", dateFormat.parse("12-13-2020"), "design", "senior"));
        jobRolesRequest.add(new JobRoleRequest(2, "UX Designer", "NY", dateFormat.parse("12-17-2024"), "design", "senior"));
        jobRolesRequest.add(new JobRoleRequest(3, "Sales", "London", dateFormat.parse("12-17-2023"), "design", "senior"));

        jobRolesResponse.add(new JobRoleResponse(1, "SE", "Belfast", dateFormat.parse("12-13-2020"), "design", "senior"));
        jobRolesResponse.add(new JobRoleResponse(2, "UX Designer", "NY", dateFormat.parse("12-17-2024"), "design", "senior"));
        jobRolesResponse.add(new JobRoleResponse(3, "Sales", "London", dateFormat.parse("12-17-2023"), "design", "senior"));

        Mockito.when(jobRoleMapper.mapJobRoleRequestToJobRoleResponse(jobRolesRequest)).thenReturn(jobRolesResponse);

        assertTrue(jobRoleService.getAllJobRoles().stream().allMatch(role -> role instanceof JobRoleResponse));
    }
}
