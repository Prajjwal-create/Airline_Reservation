package com.coforge.training.airline.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coforge.training.airline.model.AdminContent;
import com.coforge.training.airline.repository.AdminContentRepo;
import com.coforge.training.airline.service.AdminContentService;

@Service
public class AdminContentServiceImpl implements AdminContentService{

	@Autowired
	private AdminContentRepo repo;
	
	@Override
	public AdminContent newAdmin(AdminContent admin) {
		// TODO Auto-generated method stub
		return repo.save(admin);
	}

	@Override
	public List<AdminContent> getAllAdmin() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public AdminContent getOneAdmin(String email) {
		// TODO Auto-generated method stub
		return repo.findById(email).get();
	}

	@Override
	public String deleteAdmin(String email) {
		// TODO Auto-generated method stub
		repo.deleteById(email);
		if(repo.existsById(email))
		{
			return "Id is not delete";
		}
		return "Id is deleted";
	}

}
