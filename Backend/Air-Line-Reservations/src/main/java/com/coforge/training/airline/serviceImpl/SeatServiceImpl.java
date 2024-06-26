package com.coforge.training.airline.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coforge.training.airline.model.BookFlightSeatData;
import com.coforge.training.airline.model.Seats;
import com.coforge.training.airline.repository.SeatsRepo;
import com.coforge.training.airline.service.SeatService;

@Service
public class SeatServiceImpl implements SeatService{

	@Autowired
	private SeatsRepo repo;

	@Override
	public List<Seats> getBookFlightSeatData(long flightid, String seattype) {
		// TODO Auto-generated method stub
		return repo.findAllByFlightidAndSeattype(flightid, seattype);
	}

	@Override
	public List<Seats> getFlightBySeats(long flightid) {
		// TODO Auto-generated method stub
		return repo.findByFlightid(flightid);
	}

	@Override
	public String deleteById(long seatid) {
		// TODO Auto-generated method stub
		if(repo.existsById(seatid))
		{
			repo.deleteById(seatid);
			return "Delete Seat";
		}
		return "Id is not exist";
	}
	
}
