package com.example.subway.service.impl;

import com.example.subway.entity.Subway;
import com.example.subway.entity.dto.SubwayDTO;
import com.example.subway.mapper.SubwayMapper;
import com.example.subway.repository.SubwayRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class SubwayServiceImplTest {

    @Mock
    private SubwayRepository subwayRepository;

    @Mock
    private SubwayMapper subwayMapper;

    @InjectMocks
    private SubwayServiceImpl subwayService;
    private AutoCloseable autoCloseable;
    private long subwayId, existingAverageVisitorsPerMonths, updatedAverageVisitorsPerMonths;
    private String existingName, existingDescription, updatedName, updatedDescription;
    private Subway existingSubway;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        subwayService = new SubwayServiceImpl(subwayRepository, subwayMapper);

        subwayId = 1L;

        // Set up for existing values
        existingName = "Bakmil";
        existingDescription = "Existing Purple line";
        existingAverageVisitorsPerMonths = 1000L;
        existingSubway = new Subway(existingName, existingDescription, existingAverageVisitorsPerMonths);

        // Set up for updated values
        updatedName = "8 noyabr";
        updatedDescription = "New Purple Line";
        updatedAverageVisitorsPerMonths = 2000L;
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void list() {
        //when
        subwayService.list();
        //then
        verify(subwayRepository).findAll();
    }

    @Test
    void delete() {
        //when
        subwayService.delete(subwayId);
        //then
        verify(subwayRepository).deleteById(subwayId);
    }

    @Test
    void createSubway() {
        // Arrange
        SubwayDTO subwayDTO = new SubwayDTO();
        subwayDTO.setId(subwayId);
        subwayDTO.setName(existingName);
        subwayDTO.setDescription(existingDescription);
        subwayDTO.setAverageVisitorsPerMonths(existingAverageVisitorsPerMonths);

        Subway subway = subwayMapper.INSTANCE.subwayDTOToSubway(subwayDTO);

        when(subwayMapper.subwayDTOToSubway(subwayDTO)).thenReturn(subway);
        when(subwayRepository.save(subway)).thenReturn(subway);
        // Act
        subwayService.createSubway(subwayDTO);
        assertEquals(subwayDTO.toString(), subway.toString()); // due to the object id
    }

    @Test
    void updateSubway() {
        //given
        // Mock existing subway in repository
        when(subwayRepository.findById(subwayId)).thenReturn(Optional.of(existingSubway));

        SubwayDTO updatedSubwayDTO = new SubwayDTO();
        updatedSubwayDTO.setName(updatedName);
        updatedSubwayDTO.setDescription(updatedDescription);
        updatedSubwayDTO.setAverageVisitorsPerMonths(updatedAverageVisitorsPerMonths);

        // when
        subwayService.updateSubway(subwayId, updatedSubwayDTO);

        // Repo check
        ArgumentCaptor<Subway> subwayArgumentCaptor = ArgumentCaptor.forClass(Subway.class);
        verify(subwayRepository).findById(subwayId);
        verify(subwayRepository).save(subwayArgumentCaptor.capture());

        // Then
        Subway savedSubway = subwayArgumentCaptor.getValue();
        assertEquals(updatedName, savedSubway.getName());
        assertEquals(updatedDescription, savedSubway.getDescription());
        assertEquals(updatedAverageVisitorsPerMonths, savedSubway.getAverageVisitorsPerMonths());
    }

    @Test
    void patchSubway() {
        //given
        // Mock existing subway in repository
        when(subwayRepository.findById(subwayId)).thenReturn(Optional.of(existingSubway));

        SubwayDTO updatedSubwayDTO = new SubwayDTO();
        updatedSubwayDTO.setName(updatedName);
        updatedSubwayDTO.setDescription(updatedDescription);

        // when
        subwayService.updateSubway(subwayId, updatedSubwayDTO);

        // Repo check
        ArgumentCaptor<Subway> subwayArgumentCaptor = ArgumentCaptor.forClass(Subway.class);
        verify(subwayRepository).findById(subwayId);
        verify(subwayRepository).save(subwayArgumentCaptor.capture());

        // Then
        Subway savedSubway = subwayArgumentCaptor.getValue();
        assertEquals(updatedName, savedSubway.getName());
        assertEquals(updatedDescription, savedSubway.getDescription());
        assertEquals(existingSubway.getAverageVisitorsPerMonths(), savedSubway.getAverageVisitorsPerMonths()); //main check
    }

}