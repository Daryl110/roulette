package com.masivian.roulette.repository;

import com.masivian.roulette.model.Roulette;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class RouletteRepository extends RedisRepository<Roulette> {
    public RouletteRepository(RedisTemplate<String, Object> redisTemplate) {
        super(redisTemplate, "Roulette");
    }

    public void openRoulette(String id) throws Exception {
        Roulette roulette = this.hashOperations.get(this.key, id);
        if (roulette == null) throw new NullPointerException("Roulette not found");
        if (roulette.getState().equalsIgnoreCase("open")) throw new Exception("roulette was already opened");
        roulette.setState("open");
        roulette.setUpdatedAt(new Date());
        this.edit(id, roulette);
    }
}
