package org.kainos.ea.controllers;
import io.swagger.annotations.Api;
import org.kainos.ea.exceptions.DoesNotExistException;
import org.kainos.ea.services.JobRoleService;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.logging.Logger;

@Api("Kainos Jobs")
@Path("/api/job-roles")
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
    public Response getDetailedJobRole(@PathParam("id") final int id) {
        try {
            System.out.println("ID: " + id);
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

