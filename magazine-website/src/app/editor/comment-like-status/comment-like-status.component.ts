import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { EditorService } from '../../../services/editor-service';
import { AuthService } from '../../../services/auth';
import { Magazine } from '../../../entities/magazine';

@Component({
  selector: 'app-comment-like-status',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './comment-like-status.component.html',
  styleUrl: './comment-like-status.component.css'
})
export class CommentLikeStatusComponent implements OnInit {

  @Input({required: true})
  magazine!: Magazine;
  form!: FormGroup;

  constructor(private service: EditorService, private auth: AuthService, private formBuilder: FormBuilder){}

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      activo: [this.magazine.commentStatus]
    });
  }

  submit(): void {}

  change(): void {
    const confirm = window.confirm('Deseas actualizar el estado de los comentarios y me gusta');
    
    if(confirm){
      this.magazine.commentStatus = this.form.get('activo')?.value;
      this.service.updateCommentLikeStatus(this.magazine).subscribe({
        next: () => {
          window.alert('Cambio de estado exitoso');
        },
        error: (error: any) => {
          this.form.patchValue({ activo: this.magazine.commentStatus })
          console.log(error);
          this.auth.validate(error);
        }
      });
    } else {
      this.form.patchValue({ activo: this.magazine.commentStatus });
    }
  }
}
