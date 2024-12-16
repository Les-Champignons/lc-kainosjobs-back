package org.kainos.ea.controllers;
import io.swagger.annotations.Api;
import org.kainos.ea.exceptions.DoesNotExistException;
import org.kainos.ea.services.JobRoleService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Kainos Jobs")
@Path("/api/job-roles")
public class JobRoleController {
    private final JobRoleService jobRoleService;

    public JobRoleController(
            JobRoleService jobRoleService
    ) {
        this.jobRoleService = jobRoleService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllJobRoles() {
        try {
            return Response.ok().entity(jobRoleService.getAllJobRoles()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }

    }
}

