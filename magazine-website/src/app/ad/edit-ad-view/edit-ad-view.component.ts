import { Component, Input } from '@angular/core';
import { Ad } from '../../../entities/ad/ad';

@Component({
  selector: 'app-edit-ad-view',
  standalone: true,
  imports: [],
  templateUrl: './edit-ad-view.component.html',
  styleUrl: './edit-ad-view.component.css'
})
export class EditAdViewComponent {
@Input({required: true})
  ad!: Ad;
}
