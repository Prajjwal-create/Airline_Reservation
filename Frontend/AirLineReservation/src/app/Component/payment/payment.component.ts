import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PaymentTicket } from 'src/app/Class/payment-ticket';
import { CardService } from 'src/app/Service/card.service';
import { UPIService } from 'src/app/Service/upi.service';
import { CardNumberValidator } from 'src/app/shared/CardNumberValidator';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  bookDetails:any;
  bookForm:any;

  billDetails:any;
  billForm:any;

  upiForm:FormGroup | any;

  cardForm:FormGroup | any;


  constructor(private fb:FormBuilder,
      private router:Router,
      private upiService:UPIService,
      private cardService:CardService
    ) { }

  ngOnInit(){
    this.bookDetails=localStorage.getItem("bookForm");
    this.bookForm=JSON.parse(this.bookDetails);
    // console.log(this.bookForm);

    this.billDetails=localStorage.getItem("bill");
    this.billForm=JSON.parse(this.billDetails);
    console.log(this.billForm);

    this.upiForm=this.fb.group({
      upiid:['',[Validators.required,Validators.pattern('[a-zA-Z0-9.\-_]{2,256}@[a-zA-Z]{2,64}'), Validators.maxLength(20), Validators.minLength(8)]]
    });

    this.cardForm=this.fb.group({
      cardno: ['',[Validators.required,Validators.minLength(10),Validators.maxLength(12)]],
      recardno: ['',[Validators.required,Validators.minLength(10),Validators.maxLength(12)]],
      cvv: ['',[Validators.required,Validators.minLength(3),Validators.maxLength(3)]],
      cardhldername: ['',[Validators.required,Validators.pattern('^[a-zA-Z]+$')]]
    },{validator: CardNumberValidator});

  }

  get upi() { return this.upiForm.controls; }

  submitform()
  {
    // console.log(this.upiForm.value);
    let upiis=this.upiForm.value;
    this.upiService.verifyByUPI(upiis.upiid).subscribe((response) => {
      if(response==true)
      {
        this.router.navigate(['/upiOTP',upiis.upiid]);
      }
      else{
        alert("UPI is not Valid");
      }
    });
  }

  cardSubmit()
  {
    // console.log(this.cardForm.value);
    this.cardService.verifyCard(this.cardForm.value).subscribe((reposnse) => {
      if(reposnse==true)
      {
        localStorage.setItem("card",JSON.stringify(this.cardForm.value));
        this.router.navigate(['/cardcorder']); 
      }
      else
      {
        alert("Card Details is not right");
      }
    });
  }

}
