package com.example.calculatordemo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CalculatorController.class)
@ContextConfiguration(classes = com.example.tradepulse.TradepulseApplication.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(Calculator.class)
class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    @DisplayName("GET /calc - Checking the loading of the default form")
    void testShowForm() throws Exception {
        mockMvc.perform(get("/calc"))
                .andExpect(status().isOk())
                .andExpect(view().name("calc"))
                .andExpect(model().attributeExists("calcForm"))
                .andExpect(model().attribute("calcForm", hasProperty("op", is("add"))))
                .andExpect(model().attribute("calcForm", hasProperty("a", is(0))))
                .andExpect(model().attribute("calcForm", hasProperty("b", is(0))));
    }

    @Test
    @WithMockUser
    @DisplayName("POST /calc - Successful addition with a model")
    void testCalculateAddition() throws Exception {
        mockMvc.perform(post("/calc")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("a", "12")
                        .param("b", "8")
                        .param("op", "add"))
                .andExpect(status().isOk())
                .andExpect(view().name("calc"))
                .andExpect(model().attribute("calcForm", hasProperty("a", is(12))))
                .andExpect(model().attribute("calcForm", hasProperty("b", is(8))))
                .andExpect(model().attribute("calcForm", hasProperty("op", is("add"))))
                .andExpect(model().attribute("result", is("20")))
                .andExpect(model().attribute("symbol", is("+")));
    }

    @Test
    @WithMockUser
    @DisplayName("POST /calc - Successful division with decimal places and formatting")
    void testCalculateDivision() throws Exception {
        mockMvc.perform(post("/calc")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("a", "5")
                        .param("b", "2")
                        .param("op", "divide"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("result", is("2.50")))
                .andExpect(model().attribute("symbol", is("/")));
    }

    @Test
    @WithMockUser
    @DisplayName("POST /calc - Checking for the division-by-zero error message")
    void testCalculateDivisionByZero() throws Exception {
        mockMvc.perform(post("/calc")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("a", "10")
                        .param("b", "0")
                        .param("op", "divide"))
                .andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist("result"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", is("You cannot divide by zero!")));
    }
}
