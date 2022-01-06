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

import com.coforge.training.airline.enums.GenderEnums;
import com.coforge.training.airline.model.BookFlightSeatData;


import com.coforge.training.airline.repository.BookFlightSeatDataRepo;

import com.coforge.training.airline.response.BookFlightSeatDataAllListResponse;
import com.coforge.training.airline.response.SeatAvailabilityListResponse;

import com.coforge.training.airline.service.BookFlightSeatDataService;


class BookFlightSeatDataControllerTest {

	@Mock
	private BookFlightSeatDataService service;

	@Mock
	private BookFlightSeatDataRepo repo;


	@Spy
	private Model model;

	@Spy
	List<BookFlightSeatData> bTicket = new ArrayList<BookFlightSeatData>();

	@Spy
	HttpServletRequest req;

	@Spy
	HttpSession ses;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		bTicket=getBookTicketList();
	}

	private List<BookFlightSeatData> getBookTicketList() {
		BookFlightSeatData b1= new BookFlightSeatData();
		b1.setBookflightseatid(1L);
		b1.setUserid(16756);
		b1.setName("Raj");
		b1.setEmail("a@gmail.com");
		b1.setPhoneno("56656656677");
		b1.setPassportno("Asevdgt");
		b1.setCountry("japan");
		b1.setBookid(1L);
		b1.setGender(GenderEnums.Male);
		b1.setSeatno(5);
		b1.setSeattype("Economy");
		b1.setFlightid(786689);
		b1.setPrice(6700.00);
		List<BookFlightSeatData> l = new ArrayList<BookFlightSeatData>();
		l.add(b1);
		return l;
	}

	@Test
	void testGetDataById() {
		BookFlightSeatData res=new BookFlightSeatData();
		//BookFlightSeatData a= bTicket.get(0);
		res.setBookflightseatid(bTicket.get(0).getBookflightseatid());
		// seat=sType.get(0);
		
		when(service.getById(bTicket.get(0).getBookflightseatid())).thenReturn(res);
		
	  BookFlightSeatData b=service.getById(bTicket.get(0).getBookflightseatid());
		assertNotNull(res);
		
		assertEquals(b,res);
		verify(service,times(1)).getById(bTicket.get(0).getBookflightseatid());
	}

	@Test
	void testGetDataByFlightId() {
		BookFlightSeatData res=new BookFlightSeatData();
		res.setFlightid(bTicket.get(0).getFlightid());
		long id=bTicket.get(0).getFlightid();
		//res.setFlightid(bTicket.get(0).getFlightid());
		BookFlightSeatData s= bTicket.get(0);
		BookFlightSeatDataAllListResponse seatavail= new BookFlightSeatDataAllListResponse();
		seatavail.setSeats(bTicket);
		seatavail.setCheck(true);
		//seatavail.setTotalseats(5);
	     seatavail.setMessage("Seat Is available");
	//	seatavail.setTotalseats(0);
		//seatavail.set
		
		when(service.getAllSeatsByFlight(bTicket.get(0).getFlightid())).thenReturn(seatavail);
		
		BookFlightSeatDataAllListResponse b1= service.getAllSeatsByFlight(bTicket.get(0).getFlightid());
		
		assertNotNull(b1);
	
		
		//assertEquals(res,b1.get);
		verify(service,times(1)).getAllSeatsByFlight(bTicket.get(0).getFlightid());
	}

	@Test
	void testGetAllSeatAvailability() {
		long id=bTicket.get(0).getFlightid();
		BookFlightSeatData seat=bTicket.get(0);
		SeatAvailabilityListResponse s = new SeatAvailabilityListResponse();
		s.setFlightid(786689);
		s.setMessage("Seat is available");
		//s.setSeats(bTicket);
		
	when(service.checkAllAvailability(id)).thenReturn(s);
		
	SeatAvailabilityListResponse res=service.checkAllAvailability(id);
		
		assertNotNull(res);
		assertEquals(res.getMessage(),"Seat is available");
		//assertNotNull(res.getSeat());
		assertEquals(res.getFlightid(), id);
		
		verify(service,times(1)).checkAllAvailability(id);
	}

	@Test
	void testGetBookFlightByFlightIdAndSeattype() {
		BookFlightSeatData res=new BookFlightSeatData();
		res.setFlightid(bTicket.get(0).getBookflightseatid());
		res.setSeattype(bTicket.get(0).getSeattype());
		
		List<BookFlightSeatData> b1 =new ArrayList<BookFlightSeatData>();
		b1.add(res);
		when(service.getBookFlightSeatData(bTicket.get(0).getFlightid(),bTicket.get(0).getSeattype())).thenReturn(b1);
		
		List<BookFlightSeatData> x=   service.getBookFlightSeatData(bTicket.get(0).getFlightid(),bTicket.get(0).getSeattype());
		
		assertNotNull(res);
//		assertEquals(res.getMessage(), "Seat Type is updated");
		assertNotNull(res.getFlightid());
		assertNotNull(res.getSeattype());
		assertEquals(b1,x);
		
	  verify(service,times(1)).getBookFlightSeatData(bTicket.get(0).getFlightid(),bTicket.get(0).getSeattype());
	}

}
