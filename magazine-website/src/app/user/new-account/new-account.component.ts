import { Component } from '@angular/core';
import { NewAccountFormComponent } from "../new-account-form/new-account-form.component";
import { ShowAdComponent } from "../../ad/show-ad/show-ad.component";

@Component({
  selector: 'app-new-account',
  standalone: true,
  imports: [NewAccountFormComponent, ShowAdComponent],
  templateUrl: './new-account.component.html',
  styleUrl: './new-account.component.css'
})
export class NewAccountComponent {
  
  url: string = 'newAccount';
}
