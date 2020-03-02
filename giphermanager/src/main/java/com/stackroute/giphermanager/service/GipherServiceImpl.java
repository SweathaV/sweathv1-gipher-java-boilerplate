package com.stackroute.giphermanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.stackroute.giphermanager.exception.GipherNotCreatedException;
import com.stackroute.giphermanager.exception.GipherNotFoundExeption;
import com.stackroute.giphermanager.helper.GipherHelper;
import com.stackroute.giphermanager.model.Gipher;
import com.stackroute.giphermanager.repository.GipherRepository;

@Service
public class GipherServiceImpl implements GipherService {
	
	@Autowired
	GipherRepository gipherRepository;
	
	@Autowired
	GipherHelper gipherHelper;
	
	public GipherServiceImpl(GipherRepository gipherRepository, GipherHelper gipherHelper) {
		super();
		this.gipherRepository = gipherRepository;
		this.gipherHelper = gipherHelper;
	}

	@Override
	public List<Gipher> getAllGiphers() throws GipherNotFoundExeption {
		return gipherRepository.findAll();
	}
	
	@Override
	public List<Gipher> getAllGipherByUserId(String userId) throws GipherNotFoundExeption {
		return gipherRepository.getAllGipherByUserId(userId);
	}
	
	@Override
	public Gipher createGipher(Gipher gipher) throws GipherNotCreatedException{
		try {
			return gipherRepository.insert(gipher);
		}catch(DuplicateKeyException e) {
			throw new GipherNotCreatedException("Gipher not created due to duplicate key");
		}
		
	}
	
	@Override
	public Gipher updateGipher(Gipher gipher,String gipherId) {
		Optional<Gipher> categoryToUpdate = gipherRepository.findById(gipherId);
		Gipher updatedGipher = null;
		if(null != categoryToUpdate) {
			updatedGipher = gipherRepository.save(gipher); 
			 return gipher;
		}
		 return updatedGipher;
		
	}

	
	@Override
	public boolean deleteGipher(String gipherId) throws GipherNotFoundExeption{
	//	gipherRepository.deleteById(gipherId);
		try {
			gipherRepository.deleteById(gipherId);
			return true;
		} catch (Exception e) {
			throw new GipherNotFoundExeption("GipherNotFoundExeption");
		}
	}


	@Override
	public void deleteAllGiphers() throws GipherNotFoundExeption {
		gipherRepository.deleteAll();
	}


	@Override
	public List<Gipher> getAllGipherByBookmark(String bookMarkedBy) throws GipherNotFoundExeption {
		return gipherRepository.getAllGipherByBookmark(bookMarkedBy);
	}

	@Override
	public List<Gipher> getAllGipherByFavorite(String favoritedBy) throws GipherNotFoundExeption {
		return gipherRepository.getAllGipherByFavorite(favoritedBy);
	}
	
	@Override
	public List<Gipher> getGipherFromExternalAPI(String userId,String query){	
		List<Gipher> giphers = gipherHelper.getGipherFromExternalAPI(userId,query);
		for(Gipher gipher : giphers) {
			try {
				createGipher(gipher);
			} catch (GipherNotCreatedException e) {
				
			}
		}
			
		return giphers;
	}

	@Override
	public Optional<Gipher> getGipherById(String gipherId) throws GipherNotFoundExeption {
		return gipherRepository.findById(gipherId);
	}
}
