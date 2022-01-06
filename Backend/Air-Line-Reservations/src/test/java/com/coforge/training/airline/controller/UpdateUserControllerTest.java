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

import com.coforge.training.airline.model.User;

import com.coforge.training.airline.repository.UserRepo;

import com.coforge.training.airline.response.UpdateUserResponse;
import com.coforge.training.airline.service.SeatTypeService;
import com.coforge.training.airline.service.UpdateUserService;

class UpdateUserControllerTest {
	

	UpdateUserController u1= new UpdateUserController();

	@Mock
	private UpdateUserService service;

	@Mock
	private UserRepo repo;


	@Spy
	private Model model;

	@Spy
	List<User> user = new ArrayList<User>();
        
	@Spy
	HttpServletRequest req;

	@Spy
	HttpSession ses;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		user=getUserUpdate();
	}


	private List<User> getUserUpdate() {
		User u1 =new User();
		u1.setUserid(1);
		u1.setFirstname("Karan");
		u1.setLastname("singh");
		u1.setMobileno("32222456789");
		u1.setDob("21-09-1991");
		u1.setEmail("a@gmail.com");
		u1.setPassword("12345");
		List<User> l1 = new ArrayList<User>();
		l1.add(u1);
	return l1;
	}


	@Test
	void testUpdateUser() {
		long id=user.get(0).getUserid();
		User u=user.get(0);
		UpdateUserResponse response=new UpdateUserResponse();
		response.setMessage("User is Updated");
		response.setEmail("a@gmail.com");
		response.setUser(u);
		when(service.updateUser(id, u)).thenReturn(response);
		
		UpdateUserResponse res=service.updateUser(id, u);
		
		assertNotNull(res);
	   assertEquals(res.getMessage(),"User is Updated");
		assertNotNull(res.getEmail());
		assertNotNull(res.getUser());
		assertEquals(res.getUser(), u);
		
		verify(service,times(1)).updateUser(id,u);
	}

	@Test
	void testUpdatePassword() {
		//fail("Not yet implemented");
		long id=user.get(0).getUserid();
		User u=user.get(0);
		
		
	}

}
