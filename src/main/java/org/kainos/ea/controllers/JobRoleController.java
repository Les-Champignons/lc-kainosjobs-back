package org.kainos.ea.controllers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiKeyAuthDefinition;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.SecurityDefinition;
import io.swagger.annotations.SwaggerDefinition;
import org.kainos.ea.exceptions.DoesNotExistException;
import org.kainos.ea.models.UserRole;
import org.kainos.ea.services.JobRoleService;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.logging.Logger;

@Api("Kainos Jobs")
@Path("/api/job-roles")
@SwaggerDefinition(
        securityDefinition = @SecurityDefinition(
                apiKeyAuthDefinitions = {
                        @ApiKeyAuthDefinition(
                                key = HttpHeaders.AUTHORIZATION,
                                name = HttpHeaders.AUTHORIZATION,
                                in = ApiKeyAuthDefinition.ApiKeyLocation.HEADER
                        )
                }
        )
)
public class JobRoleController {
    private final JobRoleService jobRoleService;
    private static final Logger LOGGER = Logger.getLogger(
            JobRoleController.class.getName());


    public JobRoleController(
            final JobRoleService jobRoleService
    ) {
        this.jobRoleService = jobRoleService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({UserRole.ADMIN, UserRole.USER})
    @ApiOperation(
            value = "Returns all job roles",
            authorizations = @Authorization(value = HttpHeaders.AUTHORIZATION)
    )
    public Response getAllJobRoles() {
        try {
            LOGGER.info("All job roles have been successfully returned");
            return Response.ok().
                    entity(jobRoleService.getAllJobRoles()).build();
        } catch (SQLException e) {
            LOGGER.severe("SEVERE: Internal Server Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({UserRole.ADMIN, UserRole.USER})
    @ApiOperation(
            value = "Returns a job role",
            authorizations = @Authorization(value = HttpHeaders.AUTHORIZATION)
    )
    public Response getDetailedJobRole(@PathParam("id") final int id) {
        try {
            LOGGER.info("Detailed job role has been successfully returned");
            return Response.ok()
                        .entity(jobRoleService.getDetailedJobRole(id))
                        .build();
        } catch (SQLException e) {
            LOGGER.severe("SEVERE: Internal Server Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        } catch (DoesNotExistException e) {
            LOGGER.severe("SEVERE: Job Role Not Found");
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        }
    }
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({UserRole.ADMIN})
    @ApiOperation(
            value = "Deletes a job role",
            authorizations = @Authorization(value = HttpHeaders.AUTHORIZATION)
    )
    public Response deleteJobRole(@PathParam("id") final int id) {
        try {
            LOGGER.info("Attempting to delete job role with ID: " + id);
            jobRoleService.deleteJobRole(id);
            return Response.noContent().build();
        } catch (SQLException e) {
            LOGGER.severe("SEVERE: Internal Server Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        } catch (DoesNotExistException e) {
            LOGGER.severe("SEVERE: Job Role Not Found");
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        }
    }
}

