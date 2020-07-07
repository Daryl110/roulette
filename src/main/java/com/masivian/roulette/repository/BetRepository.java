package com.masivian.roulette.repository;

import com.masivian.roulette.model.Bet;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

@Repository
public class BetRepository extends RedisRepository<Bet> {

    public BetRepository(RedisTemplate<String, Object> redisTemplate) {
        super(redisTemplate, "Bet");
    }

    public Map<String, Bet> findByRouletteId(String idRoulette) {
        Map<String, Bet> result = new HashMap<>();
        this.hashOperations.entries(this.key).forEach(new BiConsumer<String, Bet>() {
            @Override
            public void accept(String s, Bet bet) {
                if (bet.getIdRoulette().equals(idRoulette)) result.put(s, bet);
            }
        });

        return result;
    }
}
