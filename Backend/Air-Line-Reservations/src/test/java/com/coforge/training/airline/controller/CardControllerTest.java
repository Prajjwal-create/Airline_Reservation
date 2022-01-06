package com.coforge.training.airline.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.ui.Model;

import com.coforge.training.airline.model.Card;

import com.coforge.training.airline.repository.CardRepo;

import com.coforge.training.airline.service.CardService;


class CardControllerTest {
	
	@Mock
	private CardService service;

	@Mock
	private CardRepo repo;


	@Spy
	private Model model;

	@Spy
	List<Card> card = new ArrayList<Card>();

	@Spy
	HttpServletRequest req;

	@Spy
	HttpSession ses;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		card=getCardList();
	}

	private List<Card> getCardList() {
		Card c1 = new Card();
		c1.setCardno(0);
		c1.setCode(0);
		c1.setCvv(0);
		c1.setName(null);
		c1.setRecardno(0);
		List<Card> l1= new ArrayList<Card>();
		l1.add(c1);
		return l1;
	}

	@Test
	void testSave() {
		Card add=new Card();

		add.setCardno(card.get(0).getCardno());
		add.setCode(card.get(0).getCode());
		add.setCvv(card.get(0).getCvv());
		add.setName(card.get(0).getName());
		add.setRecardno(card.get(0).getRecardno());
		

		
		when(service.saveCard(card.get(0))).thenReturn(add);
		Card res=service.saveCard(add);
		assertEquals(add, res);
		assertNotNull(res);
		verify(service,times(1)).saveCard(add);
	}

	@Test
	void testGetAllCard() {
		List<Card> res=card;
		when(service.getCard()).thenReturn(res);
		res=service.getCard();
		assertNotNull(res); //a passed parameter must not be null  for assertnot null
		verify(service,times(1)).getCard();
	}

	@Test
	void testVerifyCard() {
		boolean a= true;
//		Card c=card.get(0);
		Card c =new Card();
		c.setCardno(card.get(0).getCardno());
//		VerifyUserCrenditials resp=new VerifyUserCrenditials();
//		resp.setEmail("r@gmail.com");
//		resp.setPhoneno("12222133456");
		
		when(service.verifycard(card.get(0).getCardno())).thenReturn(a);
		
		
		boolean res=service.verifycard(c.getCardno());
		
		assertNotNull(c);
		
     	
     	assertEquals(c.getCardno(), card.get(0).getCardno());
    

	
      	verify(service,times(1)).verifycard(c.getCardno());
		
	}

	@Test
	void testVerify() {
	
	}

	@Test
	void testVerifyByCode() {
		
	}

}
