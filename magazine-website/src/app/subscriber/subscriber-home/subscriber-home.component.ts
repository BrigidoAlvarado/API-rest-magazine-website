import { Component } from '@angular/core';
import { SubscriberHeaderComponent } from "../subscriber-header/subscriber-header.component";

@Component({
  selector: 'app-subscriber-home',
  standalone: true,
  imports: [ SubscriberHeaderComponent],
  templateUrl: './subscriber-home.component.html',
  styleUrl: './subscriber-home.component.css'
})
export class SubscriberHomeComponent {

}
