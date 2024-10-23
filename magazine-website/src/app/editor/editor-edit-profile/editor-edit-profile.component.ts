import { Component } from '@angular/core';
import { EditorHeaderComponent } from "../editor-header/editor-header.component";
import { EditProfileComponent } from "../../user/edit-profile/edit-profile.component";

@Component({
  selector: 'app-editor-edit-profile',
  standalone: true,
  imports: [EditorHeaderComponent, EditProfileComponent],
  templateUrl: './editor-edit-profile.component.html',
  styleUrl: './editor-edit-profile.component.css'
})
export class EditorEditProfileComponent {

}
