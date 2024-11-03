import { Component, Input, OnInit } from '@angular/core';
import { Magazine } from '../../../entities/magazine';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { EditorService } from '../../../services/editor-service';
import { AuthService } from '../../../services/auth';

@Component({
  selector: 'app-subscription-status',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './subscription-status.component.html',
  styleUrl: './subscription-status.component.css'
})
export class SubscriptionStatusComponent implements OnInit{
  
  @Input({required: true})
  magazine!: Magazine;
  form!: FormGroup;

  constructor(
    private service: EditorService,
    private auth: AuthService,
    private formBuilder: FormBuilder
  ){}

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      activo: [this.magazine.subscriptionStatus]
    });
  }

  change(): void {
    const confirm = window.confirm('Deseas actualizar el estado de las suscripciones');
    
    if(confirm){
      this.magazine.subscriptionStatus = this.form.get('activo')?.value;
      this.service.updateSubscriptionStatus(this.magazine).subscribe({
        next: () => {
          window.alert('Cambio de estado exitoso');
        },
        error: (error: any) => {
          this.form.patchValue({ activo: !this.magazine.subscriptionStatus })
          console.log(error);
          this.auth.validate(error);
        }
      });
    } else {
      this.form.patchValue({ activo: this.magazine.subscriptionStatus });
    }
  }
}