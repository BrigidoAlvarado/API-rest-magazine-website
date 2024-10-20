import { Component } from '@angular/core';
import { NewAccountFormComponent } from "../new-account-form/new-account-form.component";

@Component({
  selector: 'app-new-account',
  standalone: true,
  imports: [NewAccountFormComponent],
  templateUrl: './new-account.component.html',
  styleUrl: './new-account.component.css'
})
export class NewAccountComponent {

}
