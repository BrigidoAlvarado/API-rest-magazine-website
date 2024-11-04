import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { AdService } from '../../../services/ad-service';
import { AuthService } from '../../../services/auth';
import { Amount } from '../../../entities/amount';
import { ImageAdService } from '../../../services/ad/image-ad-service';
import { ImageAd } from '../../../entities/ad/image-ad';

@Component({
  selector: 'app-image-ad',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './image-ad.component.html',
  styleUrl: './image-ad.component.css'
})
export class ImageAdComponent implements OnInit{

  form!: FormGroup;
  amount!: Amount;
  selectedFile!: File;

  constructor(
    private adService: AdService,
    private auth: AuthService,
    private formBuilder: FormBuilder,
    private service: ImageAdService){
      this.chargeAmmount();
    }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      time:  [null, [Validators.required]],
      text:  [null, [Validators.required]],
      date:  [null, [Validators.required]],
      image: [null, [Validators.required]]
    });  
  }

  submit():void {
    console.log('se hizo submit');
    if(this.form.valid){
      let imageAd = this.form?.value as ImageAd;
      imageAd.kind = 'textImageAd';
      imageAd.image = this.selectedFile;
      
      this.service.buyImageAd(imageAd).subscribe({
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

  onFileChange(event: Event): void {
    const input = event.target as HTMLInputElement;
    
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      this.selectedFile = file;
    }

  }

  private chargeAmmount(): void {
    this.adService.getCost('textImageAd').subscribe({
      next: (amount: Amount) => {
        this.amount = amount;
      },
      error: (error: any) =>{console.log(error);}
    });
  }
}
