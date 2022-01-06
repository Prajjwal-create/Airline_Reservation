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

import com.coforge.training.airline.enums.rolesEnums;


import com.coforge.training.airline.model.User;


import com.coforge.training.airline.repository.UserRepo;
import com.coforge.training.airline.request.VerifyUserCrenditials;
import com.coforge.training.airline.response.LoginUserResponse;
import com.coforge.training.airline.response.RegisterNewUser;

import com.coforge.training.airline.service.LoginService;


class LoginControllerTest {
	
	 

	@Mock
	private LoginService  service;

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
		user=getLoginList();
	}

	private List<User> getLoginList() {
		User u1= new User();
		u1.setEmail("r@gmail.com");
		u1.setFirstname("Rahul");
		u1.setLastname("rai");
		u1.setMobileno("12222133456");
		u1.setPassword("qwerty");
		u1.setRole(rolesEnums.Admin);
		List<User> res= new ArrayList<User>();
		res.add(u1);
		return res;
	}

	@Test
	void testRegisterNewUser() {
		User u=user.get(0);
		RegisterNewUser rUser= new RegisterNewUser();
		rUser.setEmail("r@gmail.com");
		rUser.setMessage("User Is Register");
		rUser.setUser(u);
		when(service.registerNewUser(u)).thenReturn(rUser);
		RegisterNewUser res=service.registerNewUser(u);
		
		assertNotNull(res);
	   assertEquals(res.getMessage(),"User Is Register");
	   equals(res.getEmail());
    assertNotNull(res.getUser());
		assertEquals(res.getUser(), u);
		
		verify(service,times(1)).registerNewUser(u);
	}

	@Test
	void testLoginUser() {
		User u=user.get(0);
		LoginUserResponse r= new LoginUserResponse();
		r.setEmail("r@gmail.com");
		r.setMessage("Login USer");
		//rUser.setRole(rolesEnums.Admin);
		r.setUser(u);
		when(service.loginUser(u)).thenReturn(r);
		LoginUserResponse res=service.loginUser(u);
		
		assertNotNull(res);
	   assertEquals(res.getMessage(),"Login USer");
	   equals(res.getEmail());
    assertNotNull(res.getUser());
		assertEquals(res.getUser(), u);
		
		verify(service,times(1)).loginUser(u);
	}

	@Test
	void testGetByUserID() {
		User res=new User();
		
		res.setUserid(user.get(0).getUserid());
		

		when(service.getUserById(user.get(0).getUserid())).thenReturn(res);
		

		User addc=service.getUserById(user.get(0).getUserid());
		
		assertNotNull(addc.getUserid());
		assertEquals(res.getUserid(), addc.getUserid());

		verify(service,times(1)).getUserById(user.get(0).getUserid());	
	}

	@Test
	void testGetByEmail() {
		User res=new User();
		res.setEmail(res.getEmail());
		
		when(service.getUserByEmail(res.getEmail())).thenReturn(res);
		
		User addc=service.getUserByEmail(res.getEmail());
		
		assertNotNull(addc);
		assertEquals(res.getEmail(),addc.getEmail());
		
		verify(service,times(1)).getUserByEmail(res.getEmail());
	}

	@Test
	void testGetAllUsers() {
List<User> res=user;
		
		when(service.getAllUser()).thenReturn(res);
		
		res=service.getAllUser();

		assertNotNull(res);
		
		verify(service,times(1)).getAllUser();
	}

	@Test
	void testVerifyEmail() {
		boolean b =true;
		User res=new User();
		res.setEmail(res.getEmail());
		
		when(service.verifyEmail(res.getEmail())).thenReturn(b);
		
		boolean addc=service.verifyEmail(res.getEmail());
		
		
		assertTrue(b==addc);
		//assertTrue(addc);
		verify(service,times(1)).verifyEmail(res.getEmail());
	}
	@Test
	void testVerifyforCrenditials()
	{
		boolean a= true;
		User u=user.get(0);
		VerifyUserCrenditials resp=new VerifyUserCrenditials();
		resp.setEmail("r@gmail.com");
		resp.setPhoneno("12222133456");
		
		when(service.verifyUserdata(resp)).thenReturn(a);
		
		
		boolean res=service.verifyUserdata(resp);
		
		assertNotNull(resp);
		
     	assertNotNull(resp.getEmail());
     	assertEquals(u.getEmail(), resp.getEmail());
     	assertNotNull(resp.getPhoneno());

	
      	verify(service,times(1)).verifyUserdata(resp);
	}

	
}
