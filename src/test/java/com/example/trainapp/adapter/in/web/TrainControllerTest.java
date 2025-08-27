package com.example.trainapp.adapter.in.web;


import com.example.trainapp.TrainappApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(classes = TrainappApplication.class)
@AutoConfigureMockMvc
class TrainControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldResetAndValidate() throws Exception {
        mockMvc.perform(post("/api/train/reset/0A0").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.wagons").value("0A0"));

        mockMvc.perform(get("/api/train/validate"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void shouldAddRemoveReplace() throws Exception {
        mockMvc.perform(post("/api/train/reset/0")).andExpect(status().isOk());

        mockMvc.perform(post("/api/train/right/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"type\":\"A\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.wagons").value("0A"));

        mockMvc.perform(put("/api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"index\":0,\"type\":\"B\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.wagons").value("BA"));

        mockMvc.perform(delete("/api/train/right"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.wagons").value("B"));
    }

    @Test
    void shouldSortEndpoint() throws Exception {
        mockMvc.perform(post("/api/train/reset/0AAABB0"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/train/sort"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value(true));
    }

}