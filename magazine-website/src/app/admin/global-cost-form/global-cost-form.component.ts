import { Component, Input, OnInit } from '@angular/core';
import { AdService } from '../../../services/ad-service';
import { AuthService } from '../../../services/auth';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { GlobalCostService } from '../../../services/global-cost-service';
import { Amount } from '../../../entities/amount';

@Component({
  selector: 'app-global-cost-form',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './global-cost-form.component.html',
  styleUrl: './global-cost-form.component.css'
})
export class GlobalCostFormComponent implements OnInit{

  @Input({required: true})
  globalCost!: string;
  cost!: number;
  title!: string;
  form!: FormGroup;

  constructor(
    private adServie: AdService,
    private auth: AuthService,
    private formBuilder: FormBuilder,
    private globalCostService: GlobalCostService){ }

  ngOnInit(): void {

    this.adServie.getCost(this.globalCost).subscribe({
      next: (amount: Amount) => {
        this.cost = amount.amount;
        this.form = this.formBuilder.group({
          cost: [this.cost, [Validators.required]]
        });
      },
      error: (error: any) => {
        console.log('error al carga el precio de la db ',error);
        this.auth.validate(error);
      }
    });

    this.form = this.formBuilder.group({
      cost: [this.cost, [Validators.required]]
    });

    this.title = this.calculateTitle(this.globalCost);

  }

  submit (): void {
    if(this.form.valid){
      this.globalCostService.updateGlobalCost(this.form.get('cost')?.value, this.globalCost).
      subscribe({
        next: () => {
          window.alert('Actualizacion Exitosa');
        },
        error: (error: any) => {
          console.log('error al actualizar el costo ',error);
          this.auth.validate(error);
        }
      })
    }
  }

  private calculateTitle(kind: string): string {
    switch(kind){
      case ('lockAd'): return 'Bloqueo de Anuncios';

      case ('magazine'): return 'Revistas';

      case ('textAd'): return 'Anuncio de Texto';

      case ('textImageAd'): return 'Anuncio de Texto e Imagen';

      case ('videoAd'): return 'Anuncio de Video e Imagen';

      default: return '';
    }
  }
}
