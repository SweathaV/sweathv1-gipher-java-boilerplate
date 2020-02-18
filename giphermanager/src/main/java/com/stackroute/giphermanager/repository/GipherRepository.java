package com.stackroute.giphermanager.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.stackroute.giphermanager.model.Gipher;

@Repository
public interface GipherRepository extends MongoRepository<Gipher, String> {
	
	@Query("{ 'userId': ?0}")
	public List<Gipher> getAllGipherByUserId(String userId);
	
	@Query("{ 'bookMarkedBy': ?0}")
	public List<Gipher> getAllGipherByBookmark(String bookMarkedBy);

	@Query("{ 'favouritedBy': ?0}")
	public List<Gipher> getAllGipherByFavorite(String favouritedBy);

}