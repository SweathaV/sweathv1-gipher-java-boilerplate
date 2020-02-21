package com.stackroute.gipherrecommendersystem.repository;

import com.stackroute.gipherrecommendersystem.model.Gipher;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface GipherRecommenderRepository extends MongoRepository<Gipher, String>  {

    //@Query("{ 'recommendBy': ?0}")
    @Query("{'recommendBy':{$exists:true}}")
	public List<Gipher> getAllGipherByRecommend(String recommendBy);

}
