package com.stackroute.gipherrecommendersystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.gipherrecommendersystem.model.Gipher;
import com.stackroute.gipherrecommendersystem.service.GipherRecommenderService;


@RestController
@CrossOrigin(origins = "*")
public class GipherRecommenderController {

	@Autowired
	GipherRecommenderService gipherRecommenderService;

	public GipherRecommenderController(GipherRecommenderService gipherRecommenderService) {
		this.gipherRecommenderService = gipherRecommenderService;
	}

	@GetMapping("/api/v1/gipherrecommendersystem/recommend/{recommendBy}")
	public ResponseEntity<?> getAllGipherByRecommendBy(@PathVariable("recommendBy") String recommendBy) {
		List<Gipher> giphers;
		try {
			System.out.println("Inside gipherrecommendersystem" + recommendBy);
			giphers = gipherRecommenderService.getAllGipherByRecommend(recommendBy);
			return new ResponseEntity<>(giphers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}