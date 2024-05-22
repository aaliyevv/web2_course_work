package com.example.subway.controller;

import com.example.subway.entity.Subway;
import com.example.subway.entity.dto.SubwayDTO;
import com.example.subway.repository.SubwayRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SubwayControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SubwayRepository subwayRepository;

    @Autowired
    private ObjectMapper objectMapper;
    private String updatedName, updatedDescription;
    private long updatedAverageVisitorsPerMonths;
    private SubwayDTO subwayDTO;

    @BeforeEach
    void setUp() {
        subwayRepository.deleteAll();
        // Initialize the SubwayDTO object
        subwayDTO = new SubwayDTO();
        subwayDTO.setName("Bakmil");
        subwayDTO.setDescription("Purple line");
        subwayDTO.setAverageVisitorsPerMonths(1000L);
        // Initialize values for PUT/PATCH
        updatedName = "8 noyabr";
        updatedDescription = "New Purple line";
        updatedAverageVisitorsPerMonths = 2000L;
    }

    @Test
    void shouldListAllSubways() throws Exception {

        mockMvc.perform(post("/subway/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(subwayDTO)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/subway/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Bakmil"));
    }

    @Test
    void shouldDeleteSubway() throws Exception {
        mockMvc.perform(post("/subway/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(subwayDTO)))
                .andExpect(status().isOk());

        Optional<Subway> subway = subwayRepository.findAll().stream().findFirst();
        subway.ifPresent(s -> {
            try {
                mockMvc.perform(delete("/subway/" + s.getId()))
                        .andExpect(status().isOk());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        mockMvc.perform(get("/subway/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void shouldAddSubway() throws Exception {
        mockMvc.perform(post("/subway/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(subwayDTO)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/subway/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Bakmil"));
    }

    @Test
    void shouldUpdateSubway() throws Exception {
        mockMvc.perform(post("/subway/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(subwayDTO)))
                .andExpect(status().isOk());

        Optional<Subway> subway = subwayRepository.findAll().stream().findFirst();
        subway.ifPresent(s -> {
            SubwayDTO updatedSubwayDTO = new SubwayDTO();
            updatedSubwayDTO.setName(updatedName);
            updatedSubwayDTO.setDescription(updatedDescription);
            updatedSubwayDTO.setAverageVisitorsPerMonths(updatedAverageVisitorsPerMonths);


            try {
                mockMvc.perform(put("/subway/" + s.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(updatedSubwayDTO)))
                        .andExpect(status().isOk());

                mockMvc.perform(get("/subway/list"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$[0].name").value(updatedName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    void shouldPatchSubway() throws Exception {
        mockMvc.perform(post("/subway/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(subwayDTO)))
                .andExpect(status().isOk());

        Optional<Subway> subway = subwayRepository.findAll().stream().findFirst();
        subway.ifPresent(s -> {
            SubwayDTO patchSubwayDTO = new SubwayDTO();
            patchSubwayDTO.setName(updatedName);

            try {
                mockMvc.perform(patch("/subway/" + s.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(patchSubwayDTO)))
                        .andExpect(status().isOk());

                mockMvc.perform(get("/subway/list"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$[0].name").value(updatedName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}