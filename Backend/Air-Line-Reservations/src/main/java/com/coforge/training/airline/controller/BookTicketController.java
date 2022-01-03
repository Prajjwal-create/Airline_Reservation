package com.coforge.training.airline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coforge.training.airline.model.BookTickets;
import com.coforge.training.airline.response.BookTicketSaveResponse;
import com.coforge.training.airline.service.BookTicketService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/bookticket")
public class BookTicketController {

	@Autowired
	private BookTicketService service;
	
	
	
//	Book Ticket 
	@PostMapping()
	public ResponseEntity<BookTicketSaveResponse> saveBookTicket(@RequestBody BookTickets ticket)
	{
		BookTicketSaveResponse res=service.saveBookTicket(ticket);
		return ResponseEntity.ok().body(res);
	}
	
//	Get All tickets
	
//	Get Ticket By id
	
//	Get Ticket By Flight Id
	
	
//	Get Ticket By User Id
	
	
	
	
	
	
}
