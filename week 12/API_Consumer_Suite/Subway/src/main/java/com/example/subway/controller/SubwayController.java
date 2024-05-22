package com.example.subway.controller;

import com.example.subway.entity.dto.SubwayDTO;
import com.example.subway.service.SubwayService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/subway")
@Validated
public class SubwayController {

    private final SubwayService subwayService;

    public SubwayController(SubwayService subwayService) {
        this.subwayService = subwayService;
    }

    @GetMapping("/list")
    public List<SubwayDTO> listSubways() {
        return subwayService.list();
    }

    @DeleteMapping("/{id}")
    public void deleteSubway(@PathVariable("id") Long id) {
        subwayService.delete(id);
    }

    @PostMapping("/")
    public void addSubway(@RequestBody @Valid SubwayDTO subwayDTO) {
        subwayService.createSubway(subwayDTO);
    }

    @PutMapping("/{id}")
    public void updateSubway(@PathVariable("id") Long id, @RequestBody @Valid SubwayDTO subwayDTO) {
        subwayService.updateSubway(id, subwayDTO);
    }

    @PatchMapping("/{id}")
    public void patchSubway(@PathVariable("id") Long id, @RequestBody @Valid SubwayDTO subwayDTO) {
        subwayService.patchSubway(id, subwayDTO);
    }


}
