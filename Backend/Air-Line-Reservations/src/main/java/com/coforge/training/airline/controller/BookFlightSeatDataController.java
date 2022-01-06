package com.coforge.training.airline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coforge.training.airline.model.BookFlightSeatData;
import com.coforge.training.airline.model.Seats;
import com.coforge.training.airline.response.BookFlightSeatDataAllListResponse;
import com.coforge.training.airline.response.SeatAvailabilityListResponse;
import com.coforge.training.airline.service.BookFlightSeatDataService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/bookflightseats")
public class BookFlightSeatDataController {

	@Autowired
	private BookFlightSeatDataService service;
	
	
//	Get Data By id
//	URL -> http://localhost:8090/bookflightseats/244
	@GetMapping("/{bookflightseatid}")
	public ResponseEntity<BookFlightSeatData> getDataById(@PathVariable("bookflightseatid") long bookflightseatid)
	{
		BookFlightSeatData res=service.getById(bookflightseatid);
		return ResponseEntity.ok().body(res);
	}
	
	
//	Get Data By Flight id
//	URL -> http://localhost:8090/bookflightseats/flightby/250327208
	@GetMapping("/flightby/{flightid}")
	public ResponseEntity<BookFlightSeatDataAllListResponse> getDataByFlightId(@PathVariable("flightid") long flightid)
	{
		BookFlightSeatDataAllListResponse res=service.getAllSeatsByFlight(flightid);
		return ResponseEntity.ok().body(res);
	}
	
	
//	Check Seat Availability
//	URL -> http://localhost:8090/bookflightseats/seatavailability/250327208
	@GetMapping("seatavailability/{flightid}")
	public ResponseEntity<SeatAvailabilityListResponse> getAllSeatAvailability(@PathVariable("flightid") long flightid)
	{
		SeatAvailabilityListResponse res=service.checkAllAvailability(flightid);
		return ResponseEntity.ok().body(res);
	}
	
	@GetMapping("/getflightbyseats/{flightid}/{seattype}")
	public ResponseEntity<List<BookFlightSeatData>> getBookFlightByFlightIdAndSeattype(@PathVariable("flightid") long flightid, @PathVariable("seattype") String seattype)
	{
		List<BookFlightSeatData> res=service.getBookFlightSeatData(flightid,seattype);
		return ResponseEntity.ok().body(res);
		
	
	}

}
