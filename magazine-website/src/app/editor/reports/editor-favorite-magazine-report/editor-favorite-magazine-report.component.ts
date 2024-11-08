import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { EditorHeaderComponent } from "../../editor-header/editor-header.component";
import { Magazine } from '../../../../entities/magazine';
import { EditorService } from '../../../../services/editor-service';
import { AuthService } from '../../../../services/auth';
import { EditorReportsService } from '../../../../services/editor-reports';

@Component({
  selector: 'app-editor-favorite-magazine-report',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, EditorHeaderComponent],
  templateUrl: './editor-favorite-magazine-report.component.html',
  styleUrl: './editor-favorite-magazine-report.component.css'
})
export class EditorFavoriteMagazineReportComponent implements OnInit {

  form!: FormGroup;
  options!: Magazine[];
  magazineList!: Magazine[]

  constructor(
    private editorService: EditorService,
    private auth: AuthService,
    private formBuilder: FormBuilder,
    private service: EditorReportsService
  ){}

  ngOnInit(): void {
      this.editorService.getPublihsedMagazines().subscribe({
        next: (options: Magazine[]) => {
          this.options = options;
        },
        error: (error: any) => {
          console.log('error al cargar las revistas publicadas', error);
          this.auth.validate(error);
        }
      });

      this.form = this.formBuilder.group({
        id: [0]
      });
      this.submit();
  }

  submit(): void {
    let id = this.form.get('id')?.value;
    if(!id){id = 0}
    this.service.getFavoriteMagazineReport(id).subscribe({
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
