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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.ui.Model;

import com.coforge.training.airline.model.FlightCompany;
import com.coforge.training.airline.repository.FlightCompanyRepo;
import com.coforge.training.airline.service.FlightCompanyService;

class FlightCompanyControllerTest {
	//FlightCompany Test
	FlightCompanyController c1= new FlightCompanyController();

	@Mock
	private FlightCompanyService service;

	@Mock
	private FlightCompanyRepo repo;


	@Spy
	private Model model;

	@Spy
	List<FlightCompany> fCompany = new ArrayList<FlightCompany>();

	@Spy
	HttpServletRequest req;

	@Spy
	HttpSession ses;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		fCompany=getCompanyList();
	}

	private List<FlightCompany> getCompanyList() {
		FlightCompany f1= new FlightCompany();
		f1.setCompanyid(1);
		f1.setCompanyname("Airline");
		f1.setCountry("India");
		f1.setCode("Abc123");
		List<FlightCompany> res=new ArrayList<FlightCompany>();
		res.add(f1);
		return res;
	}



	@Test
	void testSave() {
		FlightCompany add=new FlightCompany();

		add.setCompanyid(fCompany.get(0).getCompanyid());
		add.setCompanyname(fCompany.get(0).getCompanyname());
		add.setCountry(fCompany.get(0).getCountry());
		add.setCode(fCompany.get(0).getCode());

		//when(service.newFlight(fCompany.get(0))).thenReturn(add);
		when(service.newFlight(fCompany.get(0))).thenReturn(add);


		FlightCompany res=service.newFlight(add);

		assertEquals(add, res);

		assertNotNull(res);

		verify(service,times(1)).newFlight(add);
	}

	@Test
	void testGetAllCompany() {

		List<FlightCompany> res=fCompany;

		when(service.getAllCompany()).thenReturn(res);

		res=service.getAllCompany();

		assertNotNull(res);

		verify(service,times(1)).getAllCompany();

	}

	@Test
	void testGetCompanyById() {
		FlightCompany res=new FlightCompany();
		res.setCompanyid(fCompany.get(0).getCompanyid());
		res.setCompanyname(fCompany.get(0).getCompanyname());
		res.setCountry(fCompany.get(0).getCountry());
		res.setCode(fCompany.get(0).getCode());

		when(service.getCompanyById(fCompany.get(0).getCompanyid())).thenReturn(res);
		//		when(service.getCompanyById(fCompany.get(0).getCompanyname())).thenReturn(res);
		//		when(service.getCompanyById(fCompany.get(0).getCountry())).thenReturn(res);
		//		when(service.getCompanyById(fCompany.get(0).getCountry())).thenReturn(res);

		FlightCompany addc=service.getCompanyById(fCompany.get(0).getCompanyid());
		//FlightCompany addc=service.getCompanyById(fCompany.get(0).get);

		assertNotNull(addc.getCompanyid());

		verify(service,times(1)).getCompanyById(fCompany.get(0).getCompanyid());	
	}

	@Test
	void testGetCompanyByCompany() {
		FlightCompany res=new FlightCompany();
		//	res.setCompanyid(fCompany.get(0).getCompanyid());
		res.setCompanyname(fCompany.get(0).getCompanyname());
		res.setCountry(fCompany.get(0).getCountry());
		res.setCode(fCompany.get(0).getCode());

		when(service.getCompanyByCompany(fCompany.get(0).getCompanyname())).thenReturn(res);
		//when(service.getCompanyByCompany(fCompany.get(0).getCompanyname())).thenReturn(res);
		//	when(service.getCompanyByCompany(fCompany.get(0).getCountry())).thenReturn(res);
		//	when(service.getCompanyByCompany(fCompany.get(0).getCountry())).thenReturn(res);

		FlightCompany addc=service.getCompanyByCompany(fCompany.get(0).getCompanyname());
		//FlightCompany addc=service.getCompanyById(fCompany.get(0).get);

		assertNotNull(addc.getCompanyname());

		verify(service,times(1)).getCompanyByCompany(fCompany.get(0).getCompanyname());
	}

	@Test
	void testGetCompanyByCountry() {
		String country="Japan";
		FlightCompany f1= new FlightCompany();
		f1.setCompanyid(1);
		f1.setCompanyname("Airline");
		f1.setCountry("Japan");
		f1.setCode("Abc123");
		List<FlightCompany> res=new ArrayList<FlightCompany>();
		res.add(f1);

		when(service.getCompanyByCountry(country)).thenReturn(res);

		List<FlightCompany> x=   service.getCompanyByCountry(country);
		assertEquals(x.get(0).getCountry(), country);
		verify(service,times(1)).getCompanyByCountry(fCompany.get(0).getCompanyname());
	}

	@Test
	void testGetFlightByCountryCode() {
		String code="qwerty";
		FlightCompany f1= new FlightCompany();
		f1.setCompanyid(5);
		f1.setCompanyname("Airline");
		f1.setCountry("Japan");
		f1.setCode("qwerty");
		List<FlightCompany> res=new ArrayList<FlightCompany>();
		res.add(f1);

		when(service.getFlightByCountryCode(code)).thenReturn(res);
		List<FlightCompany> x=   service.getFlightByCountryCode(code);
		assertEquals(x.get(0).getCode(), code);

	}

	@Test
	void testDeleteMapping() {
		String del = "Id is deleted";

		when(service.deleteCompany(fCompany.get(0).getCompanyid())).thenReturn(del);

		String str=service.deleteCompany(fCompany.get(0).getCompanyid());

		assertNotNull(str);

		verify(service,times(1)).deleteCompany(fCompany.get(0).getCompanyid());
	}

	@Test
	@Disabled
	void testUpdatecompany() {
		//		String comp="IndiaWay";
		//		FlightCompany f1= new FlightCompany();
		//		f1.setCompanyid(5);
		//		f1.setCompanyname("Airline");
		//		//	f1.setCountry("Japan");
		//		//f1.setCode("qwerty");
		//
		//		when(service.updatecompany(fCompany.get(0).getCompanyid(), comp)).thenReturn(f1);
		//
		//
		//		FlightCompany addc=service.updatecompany(fCompany.get(0).getCompanyid(), comp));
		//		//FlightCompany addc=service.getCompanyById(fCompany.get(0).get);
		//
		//		assertNotNull(addc.getCompanyname());
		//
		//		verify(service,times(1)).updatecompany(fCompany.get(0).getCompanyname(),comp);
	}

}