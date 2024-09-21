package com.example.TaskManager.controller;

import com.example.TaskManager.entity.User;
import com.example.TaskManager.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setUsername("testuser");
        testUser.setPassword("password");
    }

    @Test
    @DisplayName("GET /register should return registration page")
    void showRegistrationForm_ShouldReturnRegisterPage() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    @DisplayName("POST /register should process registration when passwords match")
    void processRegistration_ShouldSucceed_WhenPasswordsMatch() throws Exception {
        Mockito.when(userService.findByUsername(anyString())).thenReturn(Optional.empty());

        mockMvc.perform(post("/register")
                        .param("username", "testuser")
                        .param("password", "password")
                        .param("confirmPassword", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));

        Mockito.verify(userService, Mockito.times(1)).register("testuser", "password");
    }

    @Test
    @DisplayName("POST /register should fail when passwords do not match")
    void processRegistration_ShouldFail_WhenPasswordsDoNotMatch() throws Exception {
        mockMvc.perform(post("/register")
                        .param("username", "testuser")
                        .param("password", "password1")
                        .param("confirmPassword", "password2"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "Passwords do not match!"));
    }

    @Test
    @DisplayName("POST /register should fail when username is already taken")
    void processRegistration_ShouldFail_WhenUsernameTaken() throws Exception {
        Mockito.when(userService.findByUsername("testuser")).thenReturn(Optional.of(testUser));

        mockMvc.perform(post("/register")
                        .param("username", "testuser")
                        .param("password", "password")
                        .param("confirmPassword", "password"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "Username is already taken!"));
    }

    @Test
    @DisplayName("GET /login should return login page")
    void showLoginForm_ShouldReturnLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    @DisplayName("POST /login should succeed when credentials are valid")
    void processLogin_ShouldSucceed_WhenCredentialsAreValid() throws Exception {
        Mockito.when(userService.login("testuser", "password")).thenReturn(Optional.of(testUser));

        mockMvc.perform(post("/login")
                        .param("username", "testuser")
                        .param("password", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));
    }

    @Test
    @DisplayName("POST /login should fail when credentials are invalid")
    void processLogin_ShouldFail_WhenCredentialsAreInvalid() throws Exception {
        Mockito.when(userService.login("testuser", "wrongpassword")).thenReturn(Optional.empty());

        mockMvc.perform(post("/login")
                        .param("username", "testuser")
                        .param("password", "wrongpassword"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "Invalid username or password!"));
    }

    @Test
    @DisplayName("GET /logout should redirect to login page with logout message")
    void logout_ShouldRedirectToLoginPage() throws Exception {
        mockMvc.perform(get("/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?logout"));
    }
}
