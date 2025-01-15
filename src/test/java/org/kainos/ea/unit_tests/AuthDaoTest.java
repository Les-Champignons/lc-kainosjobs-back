package org.kainos.ea.unit_tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kainos.ea.daos.AuthDao;
import org.kainos.ea.models.User;
import org.kainos.ea.requests.LoginRequest;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthDaoTest {
    private AuthDao authDao;
    private LoginRequest loginRequest;
    private User user;

    @BeforeEach
    void setUp() {
        authDao = spy(new AuthDao());
        loginRequest = new LoginRequest("test@example.com", "password123");
        user = new User("test@example.com", "password123", 1);
    }

    @Test
    void getUser_shouldReturnUser_whenValidCredentials() throws SQLException {
        AuthDao mockDao = mock(AuthDao.class);
        when(mockDao.getUser(loginRequest)).thenReturn(user);

        User result = mockDao.getUser(loginRequest);
        assertNotNull(result);
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getPassword(), result.getPassword());
        assertEquals(user.getRoleId(), result.getRoleId());
    }

    @Test
    void getUser_shouldReturnNull_whenInvalidCredentials() throws SQLException {
        AuthDao mockDao = mock(AuthDao.class);
        when(mockDao.getUser(loginRequest)).thenReturn(null);

        User result = mockDao.getUser(loginRequest);
        assertNull(result);
    }
}