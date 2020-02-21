package com.stackroute.gipherrecommendersystem.service;

import com.stackroute.gipherrecommendersystem.exception.GifNotFoundException;
import com.stackroute.gipherrecommendersystem.model.Gipher;
import com.stackroute.gipherrecommendersystem.repository.GipherRecommenderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GipherRecommenderServiceImpl implements GipherRecommenderService {
	private GipherRecommenderRepository gipherRecommenderRepository;

	public GipherRecommenderServiceImpl(GipherRecommenderRepository gipherRecommenderRepository) {
		this.gipherRecommenderRepository = gipherRecommenderRepository;
	}

	@Override
	public List<Gipher> getAllUserGifsFromRecommendedList() throws Exception {
		return gipherRecommenderRepository.findAll();
	}

	@Override
	public List<Gipher> getAllGipherByRecommend(String recommendBy) throws GifNotFoundException {
		return gipherRecommenderRepository.getAllGipherByRecommend(recommendBy);
	}
}