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

import com.coforge.training.airline.model.AirPort;


import com.coforge.training.airline.repository.AirPortRepo;
import com.coforge.training.airline.response.AirPortSaveResponse;

import com.coforge.training.airline.service.AirportService;


class AirPortControllerTest {
	
	AirPort a1= new AirPort();

	@Mock
	private AirportService  service;

	@Mock
	private  AirPortRepo repo;


	@Spy
	private Model model;

	@Spy
	List<AirPort> airport = new ArrayList<AirPort>();

	@Spy
	HttpServletRequest req;

	@Spy
	HttpSession ses;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		airport=getAirPortList();
	}

	

	private List<AirPort> getAirPortList() {
	AirPort a = new AirPort();
	a.setAirportid(1);
	a.setAirportname("Jamini");
	a.setAirportcountry("Japan");
	a.setAirportcity("Karm");
	a.setAirportphoneno("213445566");
	a.setAirportpincode("211134");
	a.setAdminemail("admin@gmail.com");
	List<AirPort> res = new ArrayList<AirPort>();
	  res.add(a);
	  return res;
	}



//	@Test
//	void testSaveAirport() {
		//fail("Not yet implemented");
//	  AirPortSaveResponse add=new AirPortSaveResponse();

//		add.setAirportid(airport.get(0).getAirportid() );
//		add.setAirportname(airport.get(0).getAirportname());
//		add.setAirportcountry(airport.get(0).getAirportcountry());
//		add.setAirportcity(airport.get(0).getAirportcity());
//		add.setAirportphoneno(airport.get(0).getAirportphoneno());
//		add.setAirportpincode(airport.get(0).getAirportpincode());
//		add.setAdminemail(airport.get(0).getAdminemail());
//	  add.setAirport(airport.get(0));
//		
//		when(service.saveAirport(airport.get(0))).thenReturn(add);
//		AirPortSaveResponse air=service.saveAirport(add);
//		assertEquals(add, air);
//		assertNotNull(air);
//		verify(service,times(1)).saveAirport(add);
//	}

	@Test
	void testGetAirportById() {
		AirPort res=new AirPort();
		res.setAirportid(airport.get(0).getAirportid() );
		res.setAirportname(airport.get(0).getAirportname());
		res.setAirportcountry(airport.get(0).getAirportcountry());
		res.setAirportcity(airport.get(0).getAirportcity());
		res.setAirportphoneno(airport.get(0).getAirportphoneno());
		res.setAirportpincode(airport.get(0).getAirportpincode());
		res.setAdminemail(airport.get(0).getAdminemail());

		when(service.getAirPortById(airport.get(0).getAirportid())).thenReturn(res);
		

		AirPort addc=service.getAirPortById(airport.get(0).getAirportid());
		//FlightCompany addc=service.getCompanyById(fCompany.get(0).get);

		assertNotNull(addc.getAirportid());
		assertEquals(addc, res);

		verify(service,times(1)).getAirPortById(airport.get(0).getAirportid());	
	}

	@Test
	void testGetAllAirPort() {
		List<AirPort> res=airport;
		when(service.getAllAirport()).thenReturn(res);
		res=service.getAllAirport();
		assertNotNull(res); //(assertnot null)=a passed parameter must not be null  for 
		verify(service,times(1)).getAllAirport();
	}

	@Test
	void testGetAirPortByEmail() {
		AirPort res=new AirPort();
		res.setAdminemail(airport.get(0).getAdminemail());
		
		List<AirPort> air=new ArrayList<AirPort>();
		air.add(res); // because return type is list so we add email to list
		
		when(service.getAirPortByEmail(airport.get(0).getAdminemail())).thenReturn(air);
		
		List<AirPort> addc=service.getAirPortByEmail(airport.get(0).getAdminemail());
		
		assertNotNull(addc.get(0).getAdminemail()); //a passed parameter must not be null if it is null then test will be fail
		assertEquals(addc, air);
		verify(service,times(1)).getAirPortByEmail(airport.get(0).getAdminemail());	
	}

	@Test
	void testUpdateAirport() {
		long id=airport.get(0).getAirportid();
         
		AirPort air=airport.get(0);
		 AirPortSaveResponse resp=new  AirPortSaveResponse();
		resp.setMessage("Airport is updated");
		resp.setAirport(air);
		when(service.updateAirport(id, air)).thenReturn(resp);
		
		 AirPortSaveResponse res=service.updateAirport(id, air);
		
		assertNotNull(res);
		//assertEquals(res.getMessage(), "AirPort is updated");
		assertEquals(res.getMessage(),"Airport is updated");
		assertNotNull(res.getAirport());
		assertEquals(res.getAirport(), air );
		
		verify(service,times(1)).updateAirport(id, air);
		
		
	}

	@Test
	void testDeleteAirport() {
		String del = "AirPort is deleted";
		when(service.deleteAirport(airport.get(0).getAirportid())).thenReturn(del);// if id is present the return del
		String str=service.deleteAirport(airport.get(0).getAirportid());
		assertNotNull(str); //a passed parameter must not be null if it ia null then test case fails
		assertEquals(del, str);
		verify(service,times(1)).deleteAirport(airport.get(0).getAirportid());
	}

}
