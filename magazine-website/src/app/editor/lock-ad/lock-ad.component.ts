import { Component, Input, OnInit } from '@angular/core';
import { AdService } from '../../../services/ad-service';
import { EditorService } from '../../../services/editor-service';
import { Amount } from '../../../entities/amount';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { LockAd } from '../../../entities/lockAd';
import { AuthService } from '../../../services/auth';

@Component({
  selector: 'app-lock-ad',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './lock-ad.component.html',
  styleUrl: './lock-ad.component.css'
})
export class LockAdComponent implements OnInit {

  cost!: number;
  form!: FormGroup;

  constructor(
    private costService: AdService, 
    private editorService: EditorService,
    private formBuilder: FormBuilder,
    private auth: AuthService){}

  ngOnInit(): void {
      this.costService.getCost("lockAd").subscribe({
        next: (amount: Amount) => {
          this.cost = amount.amount;
        },
        error: (error: any) => {
          console.log('error al cargar el precio del bloque: ',error);
        }
      });

      this.form = this.formBuilder.group({
        days: [null, [Validators.required, Validators.min(1)]],
        date: [null, [Validators.required]]
      });
  }

  submit(): void {
    if(this.form.valid){
      
      let lockAd = this.form.value as LockAd;
      lockAd.kind = 'lockAd';
      this.editorService.buyLockAd(lockAd).subscribe({
        next: (amount: Amount) => {
          window.alert('La compra fue realizada exitosamente \n tu saldo actual es de: '+amount.amount);
          this.form.reset();
        },
        error: (error: any) => {
          console.log('error al comprar el bloqueo',error);
          this.auth.validateMessage(error, 'No tiene suficiente dinero para realizar la compra');
        }
      })
    }
  }

}
