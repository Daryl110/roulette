package com.masivian.roulette.service;

import com.masivian.roulette.model.Bet;
import com.masivian.roulette.model.Roulette;
import com.masivian.roulette.repository.RouletteRepository;
import com.masivian.roulette.repository.BetRepository;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

@Service
public class RouletteService {

    private final RouletteRepository rouletteRepository;
    private final BetRepository betRepository;

    public RouletteService(RouletteRepository rouletteRepository, BetRepository betRepository) {
        this.rouletteRepository = rouletteRepository;
        this.betRepository = betRepository;
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

    public Map<String, Bet> closeRoulette(String id) throws Exception {
        this.rouletteRepository.closeRoulette(id);

        return this.closeBetsInRoulette(id);
    }

    private Map<String, Bet> closeBetsInRoulette(String idRoulette) {
        Map<String, Bet> result = new HashMap<>();
        this.betRepository.findByRouletteId(idRoulette).forEach(new BiConsumer<String, Bet>() {
            @Override
            public void accept(String s, Bet bet) {
                if (bet.getIdRoulette().equals(idRoulette) && bet.getState().equalsIgnoreCase("open")) {
                    bet.setState("closed");
                    bet.setUpdatedAt(new Date());
                    result.put(s, bet);
                    betRepository.edit(s, bet);
                }
            }
        });

        return result;
    }

    public Map<String, Bet> createBetInRoulette(String idRoulette, Bet bet) throws Exception {
        Map<String, Bet> response = new HashMap<>();
        Roulette roulette = this.rouletteRepository.findById(idRoulette);
        String color = roulette.getColor(bet.getColor());
        if (!roulette.getState().equalsIgnoreCase("open")) {
            throw new Exception("the roulette isn't open");
        }
        if (bet.getBet() > roulette.getMaxBet()) {
            throw new Exception("the bet is greater than the maximum roulette bet value");
        } else if (bet.getBet() <= 0) {
            throw new Exception("the bet cannot be less than or equal to zero");
        } else if (!roulette.containsNumber(bet.getNumber()))
            throw new Exception("the number of bet not exists in roulette");
        if (color == null) {
            throw new Exception("bet color not exists");
        } else {
            bet.setColor(color);
        }
        response.put(this.betRepository.save(bet), bet);

        return response;
    }
}
