import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { SubscriberService } from '../../../services/subscriber-service';
import { AuthService } from '../../../services/auth';

@Component({
  selector: 'app-comment',
  standalone: true,
  imports: [RouterModule, ReactiveFormsModule],
  templateUrl: './comment.component.html',
  styleUrl: './comment.component.css'
})
export class CommentComponent implements OnInit{
  @Input({required: true})
  id!: number;
  @Input({required: true})
  commentStatus!: boolean;
  form!: FormGroup;

  constructor(private service: SubscriberService, private auth: AuthService, private builder:FormBuilder){}

  ngOnInit(): void {
      this.form = this.builder.group({
        comment: [null, [Validators.required]]
      });
  }

  submit(): void {
    if(this.form.valid){
      let comment = this.form.get('comment')?.value;
      this.service.comment(this.id,comment).
      subscribe({
        next: () => {
          this.form.reset();
          window.alert('Ha comentado exitosamente');
        }, 
        error: (error: any) => {
          console.log(error);
          this.auth.validate(error);
        }
      });
    }
  }
}
