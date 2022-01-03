package com.coforge.training.airline.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coforge.training.airline.model.BookFlightSeatData;
import com.coforge.training.airline.model.BookTickets;
import com.coforge.training.airline.model.PaymentStatus;
import com.coforge.training.airline.random.RandomNoGenerator;
import com.coforge.training.airline.repository.AdminContentRepo;
import com.coforge.training.airline.repository.BookTicketsRepo;
import com.coforge.training.airline.repository.FlightRepo;
import com.coforge.training.airline.repository.UserRepo;
import com.coforge.training.airline.response.BookTicketSaveResponse;
import com.coforge.training.airline.service.BookTicketService;

@Service
public class BookTicketServiceImpl implements BookTicketService
{

	@Autowired
	private BookTicketsRepo repo;

	@Autowired
	private FlightRepo flightRepo;
	
	@Autowired
	private AdminContentRepo adminRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public BookTicketSaveResponse saveBookTicket(BookTickets ticket) {
		// TODO Auto-generated method stub
		
		BookTicketSaveResponse res=new BookTicketSaveResponse();
		
		if(userRepo.existsById(ticket.getUserid()) && userRepo.existsByEmail(ticket.getEmail()))
		{
			if(flightRepo.existsById(ticket.getFlightid()))
			{
//				set Total No of Seats
				int totalNoOfSeats=ticket.getBookseats().size();
				ticket.setTotalnoofseats(totalNoOfSeats);
				
//				Set Booking Time
				SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
				Date time = new Date();
				
				ticket.setBookingtime(timeFormat.format(time));
				
//				Set Booking Date
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date date = new Date();
				
				ticket.setBookingdate(dateFormat.format(date));
				
//				Set Book ID
				RandomNoGenerator rand=new RandomNoGenerator();
				long random=rand.getRamdomNumber();
				
				ticket.setBookid(random);
				
//				Set Book Id in PaymentStatus
				PaymentStatus status=ticket.getPaymentstatus();
				
				status.setBookid(random);
				status.setUserid(ticket.getUserid());
				status.setFlightid(ticket.getFlightid());
				
				ticket.setPaymentstatus(status);
				
				
//				Set Book Flight Seat Data
				List<BookFlightSeatData> allSeats=ticket.getBookseats();
				for(BookFlightSeatData seats:allSeats)
				{
					seats.setUserid(ticket.getUserid());
					seats.setBookid(random);
					seats.setFlightid(ticket.getFlightid());
//					--------
//					assign seats
					
				}
				
				ticket.setBookseats(allSeats);
				
				System.out.println(ticket);
				
//				BookTickets tick=repo.save(ticket);
				
//				return 
				res.setMessage("Book Ticket is Successfull");
				res.setCheck(true);
				res.setEmail(ticket.getEmail());
				res.setBookticket(ticket);
				
				
			}
			else
			{
				res.setMessage("This Flight is not exist");
				res.setCheck(false);
			}
		}
		else
		{
			res.setMessage("User is Incorrect");
			res.setCheck(false);
			res.setBookticket(ticket);
			res.setEmail(ticket.getEmail());
		}
		
		return res;
	}
	
}
