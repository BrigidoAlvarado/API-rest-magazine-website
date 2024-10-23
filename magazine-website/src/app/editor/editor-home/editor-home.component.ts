import { Component } from '@angular/core';
import { EditorHeaderComponent } from "../editor-header/editor-header.component";

@Component({
  selector: 'app-editor-home',
  standalone: true,
  imports: [EditorHeaderComponent],
  templateUrl: './editor-home.component.html',
  styleUrl: './editor-home.component.css'
})
export class EditorHomeComponent {

}
