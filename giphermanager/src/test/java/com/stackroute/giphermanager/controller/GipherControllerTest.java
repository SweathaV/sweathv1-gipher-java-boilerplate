package com.stackroute.giphermanager.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.giphermanager.model.Gipher;
import com.stackroute.giphermanager.service.GipherService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class GipherControllerTest {

	private Gipher gipher;
	private List<Gipher> gifList;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private GipherService gipherService;

	@Before
	public void setUp() throws Exception {

		// Initializing gipherExternalDTO
		String embed_url = "https://giphy.com/embed/5qMCjrwNW0k2Q";
		gifList = new ArrayList<>();
		// Initializing gipher object
		gipher = new Gipher("Jhon123", embed_url, "Jhon123", "Jhon123", "Jhon123", "Jhon123");
		gifList.add(gipher);
		gipher = new Gipher("Jhon234", embed_url, "Jhon234", "Jhon234", "Jhon123", "Jhon134");
		gifList.add(gipher);

	}

	@After
	public void teardown() throws Exception {
		gipher = null;
		gifList.removeAll(gifList);
	}
	
    
	@Test
	public void testGetAllGipherByUserId() throws Exception {
		when(gipherService.getAllGipherByUserId("Jhon123")).thenReturn(gifList);		
		mockMvc.perform(get("/api/v1/gipher/user/{userid}", "Jhon123")
				.contentType(MediaType.APPLICATION_JSON).content(jsonToString(gipher))).andExpect(status().isOk())
				.andDo(print());

		verify(gipherService, times(1)).getAllGipherByUserId("Jhon123");

	}
	
	@Test
	public void testGetAllGipherByBookmarkBy() throws Exception {

		when(gipherService.getAllGipherByBookmark("Jhon123")).thenReturn(gifList);
		mockMvc.perform(get("/api/v1/gipher/bookmark/{bookmarkedBy}", "Jhon123")
				.contentType(MediaType.APPLICATION_JSON).content(jsonToString(gipher))).andExpect(status().isOk())
				.andDo(print());

		verify(gipherService, times(1)).getAllGipherByBookmark("Jhon123");
	}
	
	@Test
	public void testGetAllGipherByFavouritedBy() throws Exception {

		when(gipherService.getAllGipherByFavorite("Jhon123")).thenReturn(gifList);
		mockMvc.perform(get("/api/v1/gipher/favourite/{favouritedBy}", "Jhon123")
				.contentType(MediaType.APPLICATION_JSON).content(jsonToString(gipher))).andExpect(status().isOk())
				.andDo(print());

		verify(gipherService, times(1)).getAllGipherByFavorite("Jhon123");
	}
	
	@Test
	public void testGetAllGipherFromExternalAPI() throws Exception {
		String searchgif = "searchGif";
		when(gipherService.getGipherFromExternalAPI("Jhon123",searchgif)).thenReturn(gifList);
		mockMvc.perform(get("/api/v1/gipher/externalapi/{userId}/{query}", "Jhon123",searchgif)
				.contentType(MediaType.APPLICATION_JSON).content(jsonToString(gipher))).andExpect(status().isOk())
				.andDo(print());

		verify(gipherService, times(1)).getGipherFromExternalAPI("Jhon123",searchgif);
	}
	

	private static String jsonToString(final Object obj) throws JsonProcessingException {
		String result;
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			result = jsonContent;
		} catch (JsonProcessingException e) {
			result = "Json Processing error";
		}
		return result;
	}

}
