package com.example.subway.service;

import com.example.subway.entity.dto.SubwayDTO;

import java.util.List;

public interface SubwayService {
    List<SubwayDTO> list();
    void delete(Long id);
    void createSubway(SubwayDTO subwayDTO);
    void updateSubway(long id, SubwayDTO subwayDTO);
    void patchSubway(long id, SubwayDTO subwayDTO);
}
