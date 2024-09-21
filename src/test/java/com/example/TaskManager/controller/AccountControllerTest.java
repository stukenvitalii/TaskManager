package com.example.TaskManager.controller;

import com.example.TaskManager.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AccountControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Model model;

    @InjectMocks
    private AccountController accountController;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("Account page should return account view with username")
    void accountPageShouldReturnAccountViewWithUsername() {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("testUser");

        String viewName = accountController.account(model);

        verify(model).addAttribute("username", "testUser");
        assertEquals("account", viewName);
    }

    @Test
    @DisplayName("Delete account should redirect to home and clear security context")
    void deleteAccountShouldRedirectToHomeAndClearSecurityContext() {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("testUser");

        String viewName = accountController.deleteAccount();

        verify(userService).deleteByUsername("testUser");
        verify(securityContext).getAuthentication();
        SecurityContextHolder.clearContext();
        assertEquals("redirect:/home", viewName);
    }
}