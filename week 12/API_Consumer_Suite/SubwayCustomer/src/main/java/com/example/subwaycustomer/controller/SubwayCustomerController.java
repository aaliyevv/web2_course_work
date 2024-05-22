package com.example.subwaycustomer.controller;

import com.example.subwaycustomer.entity.dto.SubwayDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/customer/subway")
@Validated
public class SubwayCustomerController {

    private final String baseUrl = "http://localhost:8080/subway";
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/list")
    public List<SubwayDTO> listSubways() {
        ResponseEntity<List> response = restTemplate.getForEntity(baseUrl + "/list", List.class);
        return response.getBody();
    }

    @DeleteMapping("/{id}")
    public void deleteSubway(@PathVariable("id") Long id) {
        restTemplate.delete(baseUrl + "/" + id);
    }

    @PostMapping("/")
    public void addSubway(@RequestBody SubwayDTO subwayDTO) {
        restTemplate.postForEntity(baseUrl + "/", subwayDTO, Void.class);
    }

    @PutMapping("/{id}")
    public void updateSubway(@PathVariable("id") Long id, @RequestBody SubwayDTO subwayDTO) {
        restTemplate.put(baseUrl + "/" + id, subwayDTO);
    }

    @PatchMapping("/{id}")
    public void patchSubway(@PathVariable("id") Long id, @RequestBody SubwayDTO subwayDTO) {
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        restTemplate.patchForObject(baseUrl + "/" + id, subwayDTO, Void.class);
    }

}
