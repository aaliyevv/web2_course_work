package com.example.subway.config;

import com.example.subway.entity.Subway;
import com.example.subway.repository.SubwayRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataBootstrapper {

    @Bean
    public CommandLineRunner initData(SubwayRepository subwayRepository) {
        return args -> {
            subwayRepository.save(new Subway("Ganjlik", "Both Green and Red line", 5000000L));
            subwayRepository.save(new Subway("Nizami", "Green Line", 3500000L));
            subwayRepository.save(new Subway("28 May", "Both Green and Red line", 6000000L));
            subwayRepository.save(new Subway("Sahil", "Red Line", 3000000L));
        };
    }
}
