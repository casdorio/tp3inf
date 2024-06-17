package com.infnet.carlos.tp3.service;

import com.infnet.carlos.tp3.model.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CursoCacheService {

    private static final String HASH_KEY = "CACHE_CURSO";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void cacheCurso(Curso curso) {
        redisTemplate.opsForHash().put(HASH_KEY, curso.getId().toString(), curso);
        redisTemplate.expire(HASH_KEY, 1, TimeUnit.HOURS);
    }

    public Curso getCursoFromCache(Long id) {
        return (Curso) redisTemplate.opsForHash().get(HASH_KEY, id.toString());
    }

    public void evictCursoFromCache(Long id) {
        redisTemplate.opsForHash().delete(HASH_KEY, id.toString());
    }
}
