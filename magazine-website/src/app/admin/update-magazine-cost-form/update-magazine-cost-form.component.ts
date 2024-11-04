import { Component, Input, OnInit } from '@angular/core';
import { Magazine } from '../../../entities/magazine';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { AdminService } from '../../../services/admin-servie';
import { AuthService } from '../../../services/auth';

@Component({
  selector: 'app-update-magazine-cost-form',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './update-magazine-cost-form.component.html',
  styleUrl: './update-magazine-cost-form.component.css'
})
export class UpdateMagazineCostFormComponent implements OnInit {
  @Input({required: true})
  magazine!: Magazine;
  form!: FormGroup;

  constructor(private service: AdminService, private auth: AuthService, private formBuilder: FormBuilder){}

  ngOnInit(): void {
      this.form = this.formBuilder.group({
          dailyCost: [this.magazine.dailyCost, [Validators.required, Validators.min(1)]]
      });
  }

  submit(): void {
    if(this.form.valid){
      this.magazine.dailyCost = this.form.get('dailyCost')?.value;
      this.service.updateDailyCost(this.magazine).subscribe({
        next: () => {
          window.alert('Actualizacion de costo diario exitoso');
        },
        error: (error: any) => {
          console.log('error al actualizar el costo ', error);
          this.auth.validate(error);
        }
      })
    }
  }
}
