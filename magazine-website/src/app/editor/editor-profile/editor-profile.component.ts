import { Component } from '@angular/core';
import { EditorHeaderComponent } from "../editor-header/editor-header.component";
import { ProfileRequestComponent } from "../../user/profile-request/ad-profile.component";

@Component({
  selector: 'app-editor-profile',
  standalone: true,
  imports: [EditorHeaderComponent, ProfileRequestComponent],
  templateUrl: './editor-profile.component.html',
  styleUrl: './editor-profile.component.css'
})
export class EditorProfileComponent {

}
