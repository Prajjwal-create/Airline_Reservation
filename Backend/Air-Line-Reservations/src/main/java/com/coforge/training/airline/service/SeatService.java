package com.coforge.training.airline.service;

import java.util.List;

import com.coforge.training.airline.model.Seats;

public interface SeatService {

	List<Seats> getBookFlightSeatData(long flightid, String seattype);

	List<Seats> getFlightBySeats(long flightid);

	String deleteById(long seatid);

}
