package com.coforge.training.airline.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coforge.training.airline.model.Card;
import com.coforge.training.airline.repository.CardRepo;
import com.coforge.training.airline.service.CardService;

@Service
public class CardServiceImpl implements CardService{

	@Autowired
	private CardRepo repo;


	@Override
	public Card saveCard(Card card) {
		// TODO Auto-generated method stub
		return repo.save(card);
	}


	@Override
	public List<Card> getCard() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}


	@Override
	public Boolean verifycard(long cardno) {
		// TODO Auto-generated method stub
		return repo.existsById(cardno);
	}


	@Override
	public Boolean verifyCardDetails(Card card) {
		// TODO Auto-generated method stub
//		boolean res=false;
		if(repo.existsById(card.getCardno()))
		{
			Card check=repo.findById(card.getCardno()).get();
			
			if(check.getCardno()==card.getCardno() && 
					check.getRecardno()==card.getRecardno() && 
					check.getCvv()==card.getCvv()) 
			{
				return true;
			}
		}

		return false;
	}


	@Override
	public Boolean verifyByCode(Card card) {
		// TODO Auto-generated method stub
		if(repo.existsById(card.getCardno()))
		{
			Card check=repo.findById(card.getCardno()).get();
			if(check.getCardno()==card.getCardno() && check.getCode()==card.getCode()) 
			{
				return true;
			}
		}

		return false;
	}

}
