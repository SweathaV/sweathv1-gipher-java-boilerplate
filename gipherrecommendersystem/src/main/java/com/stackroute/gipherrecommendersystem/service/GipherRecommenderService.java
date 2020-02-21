package com.stackroute.gipherrecommendersystem.service;
import com.stackroute.gipherrecommendersystem.exception.GifNotFoundException;
import com.stackroute.gipherrecommendersystem.model.Gipher;

import java.util.List;

public interface GipherRecommenderService {

    List<Gipher> getAllUserGifsFromRecommendedList() throws Exception;
    
    List<Gipher> getAllGipherByRecommend(String userId) throws GifNotFoundException;

}
