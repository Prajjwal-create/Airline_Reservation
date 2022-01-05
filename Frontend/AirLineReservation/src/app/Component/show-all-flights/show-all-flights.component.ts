import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FlightService } from 'src/app/Service/flight.service';

@Component({
  selector: 'app-show-all-flights',
  templateUrl: './show-all-flights.component.html',
  styleUrls: ['./show-all-flights.component.css']
})
export class ShowAllFlightsComponent implements OnInit {

  from:any;
  to:any;
  date:any;

  allFlights:any;

  constructor(private service:FlightService,
      private router:Router
    ) { }

  ngOnInit()
  {
    this.getAllFlights();
  }

  getAllFlights()
  {
    this.service.getAllFlights().subscribe((response) => {
      console.log(response);
      this.allFlights=response;
    });
  }

  raset()
  {
    this.from=null;
    this.to=null;
    this.date=null;
  }

  getSeatType(flightid:any,seatType:any)
  {
    console.log(flightid,seatType);
  }

  book(flightid:any)
  {
    this.router.navigate(['/selectseat',flightid])
  }

}
