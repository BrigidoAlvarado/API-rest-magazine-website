import { Component } from '@angular/core';
import { EditorHeaderComponent } from "../editor-header/editor-header.component";
import { PublishedMagazinesListComponent } from "../published-magazines-list/published-magazines-list.component";
import { ShowAdComponent } from "../../ad/show-ad/show-ad.component";

@Component({
  selector: 'app-editor-home',
  standalone: true,
  imports: [EditorHeaderComponent, PublishedMagazinesListComponent, ShowAdComponent],
  templateUrl: './editor-home.component.html',
  styleUrl: './editor-home.component.css'
})
export class EditorHomeComponent {

}
