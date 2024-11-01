import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { EditorService } from '../../../services/editor-service';
import { AuthService } from '../../../services/auth';

@Component({
  selector: 'app-published-magazine-view',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './published-magazine-view.component.html',
  styleUrl: './published-magazine-view.component.css'
})
export class PublishedMagazineViewComponent implements OnInit{
@Input({required: true})
  id!: number;
  form!: FormGroup;
  selectedFile!: File;

  constructor(private formBuilder: FormBuilder,private service: EditorService, private auth: AuthService){}

  ngOnInit(): void {
      this.form = this.formBuilder.group({
        file: [null, [Validators.required]]
      });
  }

  onFileChange(event: Event): void {
    const input = event.target as HTMLInputElement;
    if(input.files && input.files.length > 0){
      const file = input.files[0];
      this.selectedFile = file;
    }
  }

  submit(): void {
    if(this.form.valid){
      this.service.postNewNumber(this.id,this.selectedFile).subscribe({
        next: () => {
          window.alert('Se ha publicado un nuevo numero a la revista');
          this.form.reset();
        },
        error: (error: any) => {
          console.log(error);
          this.auth.validate(error);
        }
      })
    }
  }
}
