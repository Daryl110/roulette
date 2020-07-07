package com.masivian.roulette.service;

import com.masivian.roulette.model.Roulette;
import com.masivian.roulette.repository.RouletteRepository;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class RouletteService {

    private final RouletteRepository rouletteRepository;

    public RouletteService(RouletteRepository rouletteRepository) {
        this.rouletteRepository = rouletteRepository;
    }

    public Map<String, Roulette> findAll() {
        return this.rouletteRepository.findAll();
    }

    public String create(Roulette roulette) {
        return this.rouletteRepository.save(roulette);
    }

    public String openRoulette(String id) {
        try {
            this.rouletteRepository.openRoulette(id);
        } catch (Exception e) {

            return "Fail Operation, Cause: " + e.getMessage();
        }

        return "Success Operation, Open Roulette {id: " + id + "}";
    }
}
