package org.kainos.ea.controllers;


import io.swagger.annotations.Api;
import org.kainos.ea.exceptions.FailedtoCreateException;
import org.kainos.ea.requests.ApplicantRequest;
import org.kainos.ea.services.ApplicantService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Kainos Jobs")
@Path("/api/applicants")
public class ApplicantController {

    ApplicantService applicantService;

    public ApplicantController(final ApplicantService applicantService) {
        this.applicantService = applicantService;
    }
    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createApplication(final ApplicantRequest applicantRequest)
            throws FailedtoCreateException {
        try {
            return Response.status(Response.Status.CREATED)
                    .entity(applicantService.createApplicant(
                            applicantRequest)).build();
        } catch (FailedtoCreateException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response selectApplicants() {
        try {
            return Response.ok().
                    entity(applicantService.selectApplicants()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }

    @PUT
}
