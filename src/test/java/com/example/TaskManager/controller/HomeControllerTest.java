package com.example.TaskManager.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class HomeControllerTest {

    private final HomeController homeController = new HomeController();
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();

    @Test
    @DisplayName("Home page should return home view")
    void homePageShouldReturnHomeView() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("homepage"));
    }

    @Test
    @DisplayName("Home page with /home URL should return home view")
    void homePageWithHomeUrlShouldReturnHomeView() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("homepage"));
    }

    @Test
    @DisplayName("Non-existing URL should return 404 status")
    void nonExistingUrlShouldReturn404Status() throws Exception {
        mockMvc.perform(get("/non-existing"))
                .andExpect(status().isNotFound());
    }
}