import { Component, Input, OnInit } from '@angular/core';
import { AdService } from '../../../services/ad-service';
import { Ad } from '../../../entities/ad/ad';
import { AuthService } from '../../../services/auth';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-ad-status',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './ad-status.component.html',
  styleUrl: './ad-status.component.css'
})
export class AdStatusComponent implements OnInit{
@Input({required: true})
  ad!: Ad;
  form!: FormGroup;

  constructor(private adService: AdService, private auth: AuthService, private formBuilder: FormBuilder){
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      activo: [this.ad.status]
    });
  }

  change():void {
    const confirm = window.confirm('Deseas actualizar el estado del anuncio');
    
    if(confirm){
      this.ad.status = this.form.get('activo')?.value;
      console.log(this.ad.status)
      this.adService.updateStatus(this.ad).subscribe({
        next: () => {
          window.alert('Cambio de estado exitoso');
        },
        error: (error: any) => {
          this.form.patchValue({ activo: this.ad.status })
          console.log(error);
          this.auth.validate(error);
          window.location.reload();
        }
      });
      //enviar request
    } else {
      this.form.patchValue({ activo: this.ad.status });
    }
  }

  submit():void {  }
}
