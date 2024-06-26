package com.coforge.training.airline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coforge.training.airline.model.Seats;
import com.coforge.training.airline.service.SeatService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/seat")
public class SeatController {

	@Autowired
	private SeatService service;
	
	@GetMapping("/getflightbyseats/{flightid}/{seattype}")
	public ResponseEntity<List<Seats>> getBookFlightByFlightIdAndSeattype(@PathVariable("flightid") long flightid, @PathVariable("seattype") String seattype)
	{
		List<Seats> res=service.getBookFlightSeatData(flightid,seattype);
		return ResponseEntity.ok().body(res);
	}
	
	@GetMapping("/getbyflightid/{flightid}")
	public ResponseEntity<List<Seats>> getFlightBySeats(@PathVariable("flightid") long flightid)
	{
		List<Seats> res=service.getFlightBySeats(flightid);
		return ResponseEntity.ok().body(res);
	}
	
	
	@DeleteMapping("/delete/{seatid}")
	public ResponseEntity<String> deleteSeatById(@PathVariable("seatid") long seatid)
	{
		String res=service.deleteById(seatid);
		return ResponseEntity.ok().body(res);
	}
	
	
}
