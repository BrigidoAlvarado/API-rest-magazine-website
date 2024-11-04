import { Component, Input, OnInit } from '@angular/core';
import { Magazine } from '../../../entities/magazine';
import { MagazineService } from '../../../services/editor-service/magazine-service';
import { AuthService } from '../../../services/auth';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { UsersHome } from '../../../services/users-home';
import { AdService } from '../../../services/ad-service';
import { Amount } from '../../../entities/amount';

@Component({
  selector: 'app-new-magazine-view',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './new-magazine-view.component.html',
  styleUrl: './new-magazine-view.component.css'
})
export class NewMagazineViewComponent implements OnInit {
  @Input({required: true})
  magazine!: Magazine;
  form!: FormGroup;
  globalCost!: number;
  
  constructor(
    private magazineService: MagazineService, 
    private auth: AuthService,
    private formBuilder: FormBuilder,
    private usersHome: UsersHome,
    private costService: AdService){
    }

    ngOnInit(): void {
        this.costService.getCost("magazine").subscribe({
          next: (amount: Amount) => {
            this.globalCost = amount.amount;
            this.form = this.formBuilder.group({
              dailyCost: [this.globalCost, [Validators.required, Validators.min(1)]]
            });
          },
          error: (error: any) => {
            console.log(error);
            this.auth.validate(error);
          }
        });
        this.form = this.formBuilder.group({
          dailyCost: [null, [Validators.required, Validators.min(1)]]
        });
    }

    submit(): void {
      let id = this.magazine.id;
      if(this.form.valid){

        this.magazine = this.form?.value as Magazine;
        this.magazine.id = id;

        this.magazineService.setCost(this.magazine).subscribe({
          next: () => {
            console.log('exito')
            window.location.reload();
          },
          error: (error: any) => {
            console.log(error);
            this.auth.validate(error);
          }
        });
        
      }
    }
}
