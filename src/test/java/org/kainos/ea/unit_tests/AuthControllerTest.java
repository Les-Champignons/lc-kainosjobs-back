package org.kainos.ea.unit_tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kainos.ea.controllers.AuthController;
import org.kainos.ea.exceptions.Entity;
import org.kainos.ea.exceptions.InvalidException;
import org.kainos.ea.requests.LoginRequest;
import org.kainos.ea.services.AuthService;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthControllerTest {
    private AuthController authController;
    private AuthService mockService;
    private LoginRequest loginRequest;

    @BeforeEach
    void setUp() {
        mockService = mock(AuthService.class);
        authController = new AuthController(mockService);
        loginRequest = new LoginRequest("test@example.com", "password123");
    }

    @Test
    void login_shouldReturnOkResponse_whenValidCredentials() throws SQLException, InvalidException {
        when(mockService.login(loginRequest)).thenReturn("mockToken");

        Response response = authController.login(loginRequest);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("mockToken", response.getEntity());
    }

    @Test
    void login_shouldReturnBadRequest_whenInvalidCredentials() throws SQLException, InvalidException {
        doThrow(new InvalidException(Entity.USER, "Invalid credentials"))
                .when(mockService).login(loginRequest);

        Response response = authController.login(loginRequest);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        assertEquals("User is not valid: Invalid credentials", response.getEntity());
    }

    @Test
    void login_shouldReturnServerError_whenSQLExceptionOccurs() throws SQLException, InvalidException {
        doThrow(SQLException.class).when(mockService).login(loginRequest);

        Response response = authController.login(loginRequest);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
    }
}
