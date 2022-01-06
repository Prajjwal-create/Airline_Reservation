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

import com.coforge.training.airline.model.seatType;
import com.coforge.training.airline.repository.SeatTypeRepo;
import com.coforge.training.airline.response.SeatTypeUpdateResponse;
import com.coforge.training.airline.service.SeatTypeService;

class SeatTypeControllerTest {

	
	SeatTypeController c1= new SeatTypeController();

	@Mock
	private SeatTypeService service;

	@Mock
	private SeatTypeRepo repo;


	@Spy
	private Model model;

	@Spy
	List<seatType> sType = new ArrayList<seatType>();

	@Spy
	HttpServletRequest req;

	@Spy
	HttpSession ses;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		sType=getSeatTypeList();
	}


	private List<seatType> getSeatTypeList() {
		seatType s1= new seatType();
		s1.setSeattypeid(1);
		s1.setSeattypename("Economy");
		s1.setSeattypecode("B11");
		List<seatType> res=new ArrayList<seatType>();
		res.add(s1);
		return res;
	}

	@Test
	void testSaveNewSeat() {
		seatType add=new seatType();

		add.setSeattypeid(sType.get(0).getSeattypeid());
		add.setSeattypename(sType.get(0).getSeattypename());
		add.setSeattypecode(sType.get(0).getSeattypecode());
		

		
		when(service.saveNewSeat(sType.get(0))).thenReturn(add);
		seatType res=service.saveNewSeat(add);
		assertEquals(add, res);
		assertNotNull(res);
		verify(service,times(1)).saveNewSeat(add);
	}

	@Test
	void testGetAll() {
		List<seatType> res=sType;
		when(service.getAll()).thenReturn(res);
		res=service.getAll();
		assertNotNull(res); //a passed parameter must not be null  for assertnot null
		verify(service,times(1)).getAll();
           
	}

	@Test
	void testGetByOne() {
		seatType res=new seatType();
		res.setSeattypeid(sType.get(0).getSeattypeid());
		res.setSeattypename(sType.get(0).getSeattypename());
		res.setSeattypecode(sType.get(0).getSeattypecode());
		//res.setCode(fCompany.get(0).getCode());

		when(service.getByOne(sType.get(0).getSeattypeid())).thenReturn(res);
		

		seatType addc=service.getByOne(sType.get(0).getSeattypeid());
		//FlightCompany addc=service.getCompanyById(fCompany.get(0).get);

		assertNotNull(addc.getSeattypeid());
       assertEquals(addc, res);
		verify(service,times(1)).getByOne(sType.get(0).getSeattypeid());	
	}

	@Test
	void testUpdate() {
		long id=sType.get(0).getSeattypeid();
		seatType seat=sType.get(0);
		SeatTypeUpdateResponse response=new SeatTypeUpdateResponse();
		response.setMessage("Seat Type is updated");
		response.setSeat(seat);
		when(service.update(id, seat)).thenReturn(response);
		
		SeatTypeUpdateResponse res=service.update(id, seat);
		
		assertNotNull(res);
		assertEquals(res.getMessage(), "Seat Type is updated");
		assertNotNull(res.getSeat());
		assertEquals(res.getSeat(), seat);
		
		verify(service,times(1)).update(id, seat);
		
		
	}

	@Test
	void testDeleteMap() {
		String del = "Seat is deleted";
		when(service.deleteMap(sType.get(0).getSeattypeid())).thenReturn(del);// if id is present the return del
		String str=service.deleteMap(sType.get(0).getSeattypeid());
		assertNotNull(str); //a passed parameter must not be null if it ia null then test case fails
		verify(service,times(1)).deleteMap(sType.get(0).getSeattypeid());
	}

}