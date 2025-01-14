package org.kainos.ea.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiKeyAuthDefinition;
import io.swagger.annotations.SecurityDefinition;
import io.swagger.annotations.SwaggerDefinition;
import org.kainos.ea.exceptions.InvalidException;
import org.kainos.ea.requests.LoginRequest;
import org.kainos.ea.services.AuthService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.logging.Logger;

@Api("Auth API")
@Path("/api/auth")
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
public class AuthController {
    AuthService authService;

    public AuthController(final AuthService authService) {
        this.authService = authService;
    }

    @POST
    @Path("/login")
    public Response login(final LoginRequest loginRequest) {
        try {
            return Response.ok().entity(
                    authService.login(loginRequest)
            ).build();
        } catch (SQLException e) {
            Logger.getLogger("default").severe(e.getMessage());
            return Response.serverError().build();
        } catch (InvalidException e) {
            Logger.getLogger("default").warning(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        }
    }
}
