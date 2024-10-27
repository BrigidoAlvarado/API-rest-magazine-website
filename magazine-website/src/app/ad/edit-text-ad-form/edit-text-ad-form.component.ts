import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Ad } from '../../../entities/ad/ad';
import { TextAd } from '../../../entities/ad/text-ad';
import { TextAdService } from '../../../services/ad/text-ad-service';
import { AuthService } from '../../../services/auth';

@Component({
  selector: 'app-edit-text-ad-form',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './edit-text-ad-form.component.html',
  styleUrl: './edit-text-ad-form.component.css'
})
export class EditTextAdFormComponent implements OnInit{
@Input({required: true})
  ad!: Ad;
  form!: FormGroup;
  textAd!: TextAd;
  
  constructor(
    private formBuilder: FormBuilder, 
    private textAdService: TextAdService,
    private auth: AuthService,){
      
      this.textAdService.getAdById(this.ad.id).subscribe({
      next: (textAd: TextAd) => {
        this.textAd = textAd;
      }, 
      error: (error: any) => {
        console.log(error);
        this.auth.validate(error);
      }
    }); }

  ngOnInit(): void {
      this.form = this.formBuilder.group({
        text: [this.textAd.text ,[Validators.required]]
      });
  }

  submit(){
    if(this.form.valid){
      this.textAdService.updateTextAd(this.textAd).subscribe({
        next: () => {
          window.alert('Anuncio actualizado correctamente');
        },
        error: (error: any) => {
          console.log(error);
          this.auth.validate(error);
        }
      })
    }
  }
}