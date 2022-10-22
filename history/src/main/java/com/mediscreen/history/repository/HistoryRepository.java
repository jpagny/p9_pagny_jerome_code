package com.mediscreen.history.repository;

import com.mediscreen.history.model.HistoryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends MongoRepository<HistoryEntity, Long> {
}
