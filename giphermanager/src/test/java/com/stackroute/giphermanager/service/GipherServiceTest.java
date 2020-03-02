package com.stackroute.giphermanager.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.stackroute.giphermanager.exception.GipherNotCreatedException;
import com.stackroute.giphermanager.model.Gipher;
import com.stackroute.giphermanager.repository.GipherRepository;

@RunWith(MockitoJUnitRunner.class)
public class GipherServiceTest {

	private Gipher gipher;
	private List<Gipher> gifList;

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	GipherServiceImpl gipherService;

	@Mock
	private GipherRepository gipherRepo;

	private String embed_url;

	Optional<Gipher> options;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		gipher = new Gipher();

		// Initializing gipherExternalDTO
		embed_url = "https://giphy.com/embed/5qMCjrwNW0k2Q";
		gifList = new ArrayList<>();
		// Initializing gipher object
		gipher = new Gipher("Jhon123", embed_url, "Jhon123", "Jhon123", "Jhon123", "Jhon123");
		gifList.add(gipher);
		gipher = new Gipher("Jhon234", embed_url, "Jhon234", "Jhon234", "Jhon123", "Jhon134");
		gifList.add(gipher);
		options = Optional.of(gipher);

	}

	@After
	public void teardown() throws Exception {
		gipher = null;
		gifList.removeAll(gifList);
	}

	@Test
	public void testCreateGipherSuccess() throws Exception {
		when(gipherRepo.insert(gipher)).thenReturn(gipher);
		Gipher savedGipher = gipherService.createGipher(gipher);
		Assert.assertEquals(gipher, savedGipher);
	}

}
