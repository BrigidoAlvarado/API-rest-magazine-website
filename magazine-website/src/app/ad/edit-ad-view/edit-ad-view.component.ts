import { Component, Input } from '@angular/core';
import { Ad } from '../../../entities/ad/ad';
import { EditTextAdFormComponent } from "../edit-text-ad-form/edit-text-ad-form.component";

@Component({
  selector: 'app-edit-ad-view',
  standalone: true,
  imports: [EditTextAdFormComponent],
  templateUrl: './edit-ad-view.component.html',
  styleUrl: './edit-ad-view.component.css'
})
export class EditAdViewComponent {
@Input({required: true})
  ad!: Ad;

}
