import { Component } from '@angular/core';
import { SubscriberHeaderComponent } from "../subscriber-header/subscriber-header.component";
import { SubsMagazineListComponent } from "../subs-magazine-list/subs-magazine-list.component";

@Component({
  selector: 'app-subscriber-home',
  standalone: true,
  imports: [SubscriberHeaderComponent, SubsMagazineListComponent],
  templateUrl: './subscriber-home.component.html',
  styleUrl: './subscriber-home.component.css'
})
export class SubscriberHomeComponent {

}