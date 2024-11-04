import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Amount } from '../../../entities/amount';
import { AuthService } from '../../../services/auth';
import { AdService } from '../../../services/ad-service';
import { VideoAd } from '../../../entities/ad/video-ad';
import { VideoAdService } from '../../../services/ad/video-ad-service';

@Component({
  selector: 'app-vide-ad',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './vide-ad.component.html',
  styleUrl: './vide-ad.component.css'
})
export class VideAdComponent implements OnInit{

  form!: FormGroup;
  amount!: Amount;

  constructor(
    private formBuilder: FormBuilder,
    private auth: AuthService,
    private adService: AdService,
    private serivice: VideoAdService){
      this.chargeAmmount();
    }

  ngOnInit(): void {
      this.form = this.formBuilder.group({
      time: [null, [Validators.required]],
      link: [null, [Validators.required]],
      date: [null, [Validators.required]],
      text: [null, [Validators.required]]
    });  
  }

  private chargeAmmount(): void {
    this.adService.getCost('videoAd').subscribe({
      next: (amount: Amount) => {
        this.amount = amount;
      },
      error: (error: any) =>{console.log(error);}
    });
  }

  submit(): void {
    if(this.form.valid){
      
      let videoAd = this.form?.value as VideoAd;
      videoAd.kind = 'videoAd';
      this.serivice.buyVideoAd(videoAd).subscribe({
        next: (amount: Amount) => {
          alert('compra exitosa\n saldo actual: '+amount.amount);
          this.form.reset();
        },
        error: (error: any) => {
          console.log(error);
          this.auth.validateMessage(error,'No tienes dinero suficiente para realizar la compra');
          
        }
      });
   }
  }
  
}