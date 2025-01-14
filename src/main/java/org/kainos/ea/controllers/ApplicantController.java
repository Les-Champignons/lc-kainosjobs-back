package org.kainos.ea.controllers;


import io.swagger.annotations.Api;
import org.kainos.ea.exceptions.FailedtoCreateException;
import org.kainos.ea.requests.ApplicantRequest;
import org.kainos.ea.services.ApplicantService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Kainos Jobs")
@Path("/api/applications")
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
        return Response.status(Response.Status.CREATED).entity(applicantService.createApplicant(
                applicantRequest)).build();
    }
}
