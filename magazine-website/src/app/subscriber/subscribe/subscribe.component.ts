import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { SubscriberService } from '../../../services/subscriber-service';
import { Magazine } from '../../../entities/magazine';
import { AuthService } from '../../../services/auth';

@Component({
  selector: 'app-subscribe',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './subscribe.component.html',
  styleUrl: './subscribe.component.css'
})
export class SubscribeComponent implements OnInit {
  @Input({required: true})
  magazine!: Magazine;
  form!: FormGroup;

  constructor(
    private formBuilder:FormBuilder, 
    private service: SubscriberService, 
    private auth: AuthService){}

  ngOnInit(): void {
      this.form = this.formBuilder.group({
        date: [new Date().toISOString().split('T')[0], [Validators.required]]
      });
  }

  submit(): void {
    if(this.form.valid){
      let magazine = this.form?.value as Magazine;
      magazine.id = this.magazine.id;
      this.service.subscribe(magazine).subscribe({
        next: () => {
          window.alert("suscripcion exitosa");
          window.location.reload();
        },
        error: (error: any) => {
          console.log('error al suscribirse',error);
          this.auth.validate(error);
        }
      });
    }
  }
}
