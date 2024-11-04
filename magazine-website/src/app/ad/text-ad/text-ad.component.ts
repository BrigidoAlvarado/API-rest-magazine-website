import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { TextAd } from '../../../entities/ad/text-ad'
import { Amount } from '../../../entities/amount';
import { AdService } from '../../../services/ad-service';
import { AuthService } from '../../../services/auth';
import { TextAdService } from '../../../services/ad/text-ad-service';

@Component({
  selector: 'app-text-ad',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './text-ad.component.html',
  styleUrl: './text-ad.component.css'
})
export class TextAdComponent implements OnInit{

  form!: FormGroup;
  amount!: Amount;
  
  constructor(
    private formBuilder: FormBuilder,
    private adService: AdService,
    private auth: AuthService,
    private service: TextAdService){
      this.chargeAmmount();}
  
  ngOnInit(): void {
    this.form = this.formBuilder.group({
      time: [null, [Validators.required]],
      text: [null, [Validators.required]],
      date: [null, [Validators.required]]
    });  
  }

  submit(): void {
    console.log('se hizo submit');
    if(this.form.valid){
      let textAd = this.form?.value as TextAd;
      textAd.kind = 'textAd';
      this.service.postTextAd(textAd).subscribe({
        next: (amount: Amount) => {
          alert('compra exitosa\n saldo actual: '+amount.amount);
          this.form.reset();
        },
        error: (error: any) => {
          console.log(error);
          this.auth.validate(error);
          if(error.status === 403){
            alert('No tienes dinero suficiente para realizar la compra');
          }
        }
      });
    }
  }

  private chargeAmmount(): void {
    this.adService.getCost('textAd').subscribe({
      next: (amount: Amount) => {
        this.amount = amount;
      },
      error: (error: any) =>{console.log(error);}
    });
  }

}