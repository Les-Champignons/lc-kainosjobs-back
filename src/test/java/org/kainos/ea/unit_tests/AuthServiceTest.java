package org.kainos.ea.unit_tests;

import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kainos.ea.daos.AuthDao;
import org.kainos.ea.exceptions.InvalidException;
import org.kainos.ea.models.User;
import org.kainos.ea.requests.LoginRequest;
import org.kainos.ea.services.AuthService;
import java.security.Key;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceTest {
    private AuthService authService;
    private AuthDao mockDao;
    private Key mockKey;
    private LoginRequest loginRequest;
    private User user;

    @BeforeEach
    void setUp() {
        mockDao = mock(AuthDao.class);
        mockKey = Jwts.SIG.HS256.key().build();
        authService = new AuthService(mockDao, mockKey);
        loginRequest = new LoginRequest("test@example.com", "password123");
        user = new User("test@example.com", "password123", 1);
    }

    @Test
    void login_shouldReturnJwtToken_whenValidCredentials() throws SQLException, InvalidException {
        when(mockDao.getUser(loginRequest)).thenReturn(user);

        String token = authService.login(loginRequest);
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void login_shouldThrowInvalidException_whenUserNotFound() throws SQLException {
        when(mockDao.getUser(loginRequest)).thenReturn(null);

        assertThrows(InvalidException.class, () -> authService.login(loginRequest));
    }
}
