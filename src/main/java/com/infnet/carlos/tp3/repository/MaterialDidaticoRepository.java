package com.infnet.carlos.tp3.repository;

import com.infnet.carlos.tp3.model.MaterialDidatico;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialDidaticoRepository extends MongoRepository<MaterialDidatico, String> {
}
