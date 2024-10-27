import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Amount } from '../../../entities/amount';
import { PaymentToUpService} from '../../../services/payment-to-up';
import { Router } from '@angular/router';

@Component({
  selector: 'app-payment-to-up',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './payment-to-up.component.html',
  styleUrl: './payment-to-up.component.css'
})
export class PaymentToUpComponent implements OnInit {

  form!: FormGroup;

  constructor(
    private formBuilder: FormBuilder, 
    private paymentToUpService: PaymentToUpService,
    private router: Router  
  ){}

  ngOnInit(): void {
      this.form = this.formBuilder.group({
        amount: [null, [Validators.required, Validators.min(0.001)]]
      });
  }

  submit(): void {
    console.log('se hizo submit');
    if(this.form.valid){
      console.log('el form es valido');
      let amount = this.form.value as Amount;
      this.paymentToUpService.sendPost(amount).subscribe({
        next: (amount: Amount) => {
          this.form.reset();
          window.alert('La recarga se ha completado exitosamente \n'+
            'su saldo actual es de: '+amount.amount
          );
        },
        error: (error: any) => {
          console.log(error);
          if(error.status === 401){
            localStorage.clear
            window.alert('No ha iniciado sesion');
            this.router.navigate(['/login']);
          } else{
            window.alert('Error al realizar la peticion');
          }
        }
      });

    }
  }

}
