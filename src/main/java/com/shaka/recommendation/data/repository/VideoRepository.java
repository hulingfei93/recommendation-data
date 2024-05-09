package com.shaka.recommendation.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.shaka.recommendation.data.entity.VideoEntity;

@Repository
public interface VideoRepository extends MongoRepository<VideoEntity, String> {

}
