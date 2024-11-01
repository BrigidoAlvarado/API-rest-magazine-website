import { Component } from '@angular/core';
import { PostMagazineFormComponent } from "../post-magazine-form/post-magazine-form.component";
import { EditorHeaderComponent } from "../editor-header/editor-header.component";

@Component({
  selector: 'app-post-view',
  standalone: true,
  imports: [PostMagazineFormComponent, EditorHeaderComponent],
  templateUrl: './post-view.component.html',
  styleUrl: './post-view.component.css'
})
export class PostViewComponent {

}
