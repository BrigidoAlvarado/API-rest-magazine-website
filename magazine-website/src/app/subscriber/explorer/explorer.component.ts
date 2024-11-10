import { Component, OnInit } from '@angular/core';
import { Magazine } from '../../../entities/magazine';
import { SubscriberService } from '../../../services/subscriber-service';
import { AuthService } from '../../../services/auth';
import { MagazineExplorerViewComponent } from "../magazine-explorer-view/magazine-explorer-view.component";
import { SubscriberHeaderComponent } from "../subscriber-header/subscriber-header.component";
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ShowAdComponent } from "../../ad/show-ad/show-ad.component";

@Component({
  selector: 'app-explorer',
  standalone: true,
  imports: [MagazineExplorerViewComponent, SubscriberHeaderComponent, FormsModule, ReactiveFormsModule, ShowAdComponent],
  templateUrl: './explorer.component.html',
  styleUrl: './explorer.component.css'
})
export class ExplorerComponent implements OnInit{

  magazineList!: Magazine[];
  form!: FormGroup;

  constructor(
    private service: SubscriberService,
    private auth: AuthService,
    private formBuilder: FormBuilder){}

  ngOnInit(): void {
      this.form = this.formBuilder.group({
        category: [''],
        tag: ['']
      });
      this.submit();
  }

   submit(): void {
    console.log('se hizo submit')
    let category = this.form.get('category')?.value;
      let tag = this.form.get('tag')?.value;

      console.log('category is: ',category,' tag is: ',tag)
    if(this.form.valid){

      this.service.getExplorerMagazines(category, tag).subscribe({
        next: (magazineList: Magazine []) => {
          console.log('request exitoso');
          this.magazineList = magazineList;
          console.log(magazineList.length);
        },
        error: (error: any) => {
          console.log('error al traer las revistas para la vista explorer: ',error);
          this.auth.validate(error);
        }
      });
    }
  }

}
