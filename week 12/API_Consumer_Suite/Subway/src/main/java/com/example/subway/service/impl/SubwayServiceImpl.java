package com.example.subway.service.impl;

import com.example.subway.entity.Subway;
import com.example.subway.entity.dto.SubwayDTO;
import com.example.subway.mapper.SubwayMapper;
import com.example.subway.repository.SubwayRepository;
import com.example.subway.service.SubwayService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubwayServiceImpl implements SubwayService {

    private final SubwayRepository subwayRepository;
    private final SubwayMapper subwayMapper;

    public SubwayServiceImpl(SubwayRepository subwayRepository, SubwayMapper subwayMapper) {
        this.subwayRepository = subwayRepository;
        this.subwayMapper = subwayMapper;
    }

    @Override
    public List<SubwayDTO> list() {
        List<Subway> subways = subwayRepository.findAll();
        List<SubwayDTO> subwayDTOs = new ArrayList<>();

        for (Subway subway : subways) {
            SubwayDTO subwayDTO = subwayMapper.INSTANCE.subwayToSubwayDTO(subway);
            subwayDTOs.add(subwayDTO);
        }
        return subwayDTOs;
    }

    @Override
    public void delete(Long id) {
        subwayRepository.deleteById(id);
    }

    public void createSubway(SubwayDTO subwayDTO) {
        Subway subway = subwayMapper.INSTANCE.subwayDTOToSubway(subwayDTO);
        subwayRepository.save(subway);
    }

    public void updateSubway(long id, SubwayDTO subwayDTO) {
        Subway subway = subwayMapper.INSTANCE.subwayDTOToSubway(subwayDTO);
        Subway savedSubway = subwayRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Subway not found with id: " + id));
        savedSubway.setName(subway.getName());
        savedSubway.setAverageVisitorsPerMonths(subway.getAverageVisitorsPerMonths());
        savedSubway.setDescription(subway.getDescription());
        subwayRepository.save(savedSubway);
    }

    public void patchSubway(long id, SubwayDTO subwayDTO) {
        if (subwayRepository.findById(id).isPresent()) {
            Subway subway = subwayMapper.INSTANCE.subwayDTOToSubway(subwayDTO);
            Subway savedSubway = subwayRepository.findById(id).get();
            if (subway.getAverageVisitorsPerMonths() != null) {
                savedSubway.setAverageVisitorsPerMonths(subway.getAverageVisitorsPerMonths());
            }
            if (subway.getDescription() != null) {
                savedSubway.setDescription(subway.getDescription());
            }
            if (subway.getName() != null) {
                savedSubway.setName(subway.getName());
            }
            subwayRepository.save(savedSubway);
        }
    }
}
