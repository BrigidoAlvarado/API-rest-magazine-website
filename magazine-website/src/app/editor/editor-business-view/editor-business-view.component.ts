import { Component } from '@angular/core';
import { EditorHeaderComponent } from "../editor-header/editor-header.component";
import { PaymentToUpComponent } from "../../user/payment-to-up/payment-to-up.component";
import { LockAdComponent } from "../lock-ad/lock-ad.component";
import { ShowAdComponent } from "../../ad/show-ad/show-ad.component";

@Component({
  selector: 'app-editor-business-view',
  standalone: true,
  imports: [EditorHeaderComponent, PaymentToUpComponent, LockAdComponent, ShowAdComponent],
  templateUrl: './editor-business-view.component.html',
  styleUrl: './editor-business-view.component.css'
})
export class EditorBusinessViewComponent {

}
