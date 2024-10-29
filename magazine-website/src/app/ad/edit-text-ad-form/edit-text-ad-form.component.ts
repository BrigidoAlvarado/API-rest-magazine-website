import { Component, Input } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
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
export class EditTextAdFormComponent {
  form!: FormGroup;
  @Input()
  ad!: Ad;
  textAd!: TextAd | null;

  constructor(
    private formBuilder: FormBuilder ,
    private adService: TextAdService, 
    private auth: AuthService){
    }

  ngOnInit(): void {
      this.form = this.formBuilder.group({
        text: [null, [Validators.required]]
      });
  }

  execute():void {

    //realizar el request
    this.adService.getAdById(this.ad.id).subscribe({
      next: (texAd: TextAd) => {
        this.textAd = texAd;
        this.form.patchValue({ text: this.textAd.text });
      },
      error: (error: any) => {
        console.log(error);
        this.auth.validate(error);
      }
    });
  }

  submit(){
    this.textAd = this.form.value as TextAd;
    this.adService.updateTextAd(this.textAd,this.ad.id).subscribe({
      next: () => {
        window.alert('Se realizo la actualizacion correctamente');        
      }, 
      error: (error: any) => {
        console.log(error);
        this.auth.validate(error);
      }
    });
  }
}