import { Component, Input, OnInit } from '@angular/core';
import { Magazine } from '../../../entities/magazine';
import { AuthService } from '../../../services/auth';
import { AdService } from '../../../services/ad-service';
import { Amount } from '../../../entities/amount';
import { PostViewComponent } from "../../editor/post-view/post-view.component";
import { NewMagazineViewComponent } from "../new-magazine-view/new-magazine-view.component";

@Component({
  selector: 'app-new-magazines-list',
  standalone: true,
  imports: [PostViewComponent, NewMagazineViewComponent],
  templateUrl: './new-magazines-list.component.html',
  styleUrl: './new-magazines-list.component.css'
})
export class NewMagazinesListComponent  {
  @Input({required: true})
  magazineList!: Magazine[];
}
