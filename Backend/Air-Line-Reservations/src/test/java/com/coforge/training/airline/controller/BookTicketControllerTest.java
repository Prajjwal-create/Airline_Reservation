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


import com.coforge.training.airline.model.BookTickets;


import com.coforge.training.airline.repository.BookTicketsRepo;

import com.coforge.training.airline.service.BookTicketService;


class BookTicketControllerTest {
	
	@Mock
	private BookTicketService service;

	@Mock
	private BookTicketsRepo repo;


	@Spy
	private Model model;

	@Spy
	List<BookTickets> bTicket = new ArrayList<BookTickets>();

	@Spy
	HttpServletRequest req;

	@Spy
	HttpSession ses;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		bTicket=getBookTicketList();
		
	}


	private List<BookTickets> getBookTicketList() {
		BookTickets b1= new BookTickets();
		b1.setBookid(1);
		b1.setFlightid(123);
		b1.setUserid(23);
		b1.setEmail("a@gmail.com");
		b1.setTicketpaymentmethod("UPI");
		b1.setTotalnoofseats(20);
		b1.setBookingdate("12-09-2021");
		b1.setBookingtime("10:30 pm");
	//	b1.setPaymentstatus();
	//	b1.setBookseats(null);
		List<BookTickets> tk = new ArrayList<BookTickets>();
		tk.add(b1);
		return tk;
	}


	@Test
	void testSaveBookTicket() {
		
		
//		BookTicketSaveResponse bok = new BookTicketSaveResponse();
//		bok.setBookticket((BookTickets) bTicket);
//		bok.setEmail();
//		bok.setMessage);
	}

	@Test
	void testGetAllTickets() {
		List<BookTickets> res=bTicket;
		when(service.getAllTickets()).thenReturn(res);
		res=service.getAllTickets();
		assertNotNull(res); //a passed parameter must not be null  for assertnot null
		verify(service,times(1)).getAllTickets();
	}

	@Test
	void testGetTicketById() {
		BookTickets res=new BookTickets();
		res.setBookid(bTicket.get(0).getBookid());
		
		when(service.getBookTicketById(bTicket.get(0).getBookid())).thenReturn(res);
		BookTickets bt=service.getBookTicketById(bTicket.get(0).getBookid());
		
           assertNotNull(bt.getBookid());
		assertEquals(res.getBookid(),bt.getBookid());

		verify(service,times(1)).getBookTicketById(bTicket.get(0).getBookid());	
		
		
	}

	@Test
	void testGetTicketByFlightId() {
		BookTickets res=new BookTickets();
		res.setFlightid(bTicket.get(0).getFlightid());
		
		when(service.getBookTicketById(bTicket.get(0).getFlightid())).thenReturn(res);
		BookTickets bt=service.getBookTicketById(bTicket.get(0).getFlightid());
		
           assertNotNull(bt.getFlightid());
		assertEquals(res.getFlightid(),bt.getFlightid());

		verify(service,times(1)).getBookTicketById(bTicket.get(0).getFlightid());	
	}

	@Test
	void testGetTicketByUserId() {
		BookTickets res=new BookTickets();
		res.setUserid(bTicket.get(0).getUserid());
		
		List<BookTickets> lBook = new ArrayList<BookTickets>();
		lBook.add(res);
		//res.setUserid(bTicket.get(0).getUserid());
		
		when(service.getBookTicketsByUserId(bTicket.get(0).getUserid())).thenReturn(lBook);
		List<BookTickets> bt=service.getBookTicketsByUserId(bTicket.get(0).getUserid());
		
           assertNotNull(bt.get(0).getUserid());
		assertEquals(bt.get(0).getUserid(),lBook.get(0).getUserid());

	    verify(service,times(1)).getBookTicketsByUserId(bTicket.get(0).getUserid());	
	}

	@Test
	void testGetAllTicketsByEmail() {
		BookTickets res=new BookTickets();
		res.setEmail(bTicket.get(0).getEmail());
		
		List<BookTickets> air=new ArrayList<BookTickets>();
		air.add(res); // because return type is list so we add email to list
		
		when(service.getAllTicketsByEmail(bTicket.get(0).getEmail())).thenReturn(air);
		
		List<BookTickets> addc=service.getAllTicketsByEmail(bTicket.get(0).getEmail());
		
		assertNotNull(addc.get(0).getEmail()); //a passed parameter must not be null if it is null then test will be fail
		assertEquals(air.get(0).getEmail(),addc.get(0).getEmail());
		verify(service,times(1)).getAllTicketsByEmail(bTicket.get(0).getEmail());	
	}

}
