package com.masivian.roulette.controller;

import com.masivian.roulette.model.Roulette;
import com.masivian.roulette.service.RouletteService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/roulettes")
public class RouletteController {
    private final RouletteService rouletteService;

    public RouletteController(RouletteService rouletteService) {
        this.rouletteService = rouletteService;
    }

    @GetMapping
    public Map<String, Roulette> findAll() {
        return this.rouletteService.findAll();
    }

    @PostMapping
    public String create() {
        Roulette roulette = new Roulette();

        return this.rouletteService.create(roulette);
    }

    @PutMapping("/{id}/open")
    public String openRoulette(@PathVariable String id) {
        return this.rouletteService.openRoulette(id);
    };
}
