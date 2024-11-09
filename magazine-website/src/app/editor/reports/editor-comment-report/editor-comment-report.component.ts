import { Component, OnInit } from '@angular/core';
import { EditorHeaderComponent } from "../../editor-header/editor-header.component";
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Filter } from '../../../../entities/filter';
import { Magazine } from '../../../../entities/magazine';
import { AuthService } from '../../../../services/auth';
import { EditorReportsService } from '../../../../services/editor-reports';
import { EditorService } from '../../../../services/editor-service';

@Component({
  selector: 'app-editor-comment-report',
  standalone: true,
  imports: [EditorHeaderComponent,FormsModule, ReactiveFormsModule],
  templateUrl: './editor-comment-report.component.html',
  styleUrl: './editor-comment-report.component.css'
})
export class EditorCommentReportComponent implements OnInit{

  filter!: Filter
  magazineList!: Magazine[]
  options!: Magazine[]
  form!: FormGroup

  constructor(
    private auth: AuthService,
    private service: EditorReportsService,
    private editorService: EditorService,
    private formBuilder: FormBuilder
  ){}

  ngOnInit(): void {
    this.editorService.getPublihsedMagazines().subscribe({
      next: (options: Magazine[]) => {
        this.options = options;
      },
      error: (error: any) => {
        console.log('error al cargar las revistas publicadas del editor', error);
        this.auth.validate(error);
      }
    });

    this.form = this.formBuilder.group({
      id: [0],
      startDate: [null],
      endDate: [null]
    });
    this.submit();
  }

  submit(): void {
    this.filter = this.form.value as Filter;
    
    this.service.getCommentsReport(this.filter).subscribe({
      next: (magazineList: Magazine[]) => {
        this.magazineList = magazineList;
      },
      error: (error: any) => {
        console.log('error al cargar el listado de las revistas con mas me gusta', error);
        this.auth.validate(error);
      }
    });
  }
}
