package com.turinglabs.keyconnect.server.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ServerStatusControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testHealthy() throws Exception {
    mockMvc.perform(
        get("/v1/server/status")
    ).andExpect(
        status().is(200)
    ).andExpect(
          jsonPath("$.status").value("healthy")
    );
  }

}
