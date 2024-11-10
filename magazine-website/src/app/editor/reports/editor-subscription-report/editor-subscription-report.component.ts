import { Component, OnInit } from '@angular/core';
import { EditorHeaderComponent } from "../../editor-header/editor-header.component";
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Filter } from '../../../../entities/filter';
import { Magazine } from '../../../../entities/magazine';
import { AuthService } from '../../../../services/auth';
import { EditorReportsService } from '../../../../services/editor-reports';
import { EditorService } from '../../../../services/editor-service';
import { ShowAdComponent } from "../../../ad/show-ad/show-ad.component";

@Component({
  selector: 'app-editor-subscription-report',
  standalone: true,
  imports: [EditorHeaderComponent, FormsModule, ReactiveFormsModule, ShowAdComponent],
  templateUrl: './editor-subscription-report.component.html',
  styleUrl: './editor-subscription-report.component.css'
})
export class EditorSubscriptionReportComponent implements OnInit {

  form!: FormGroup;
  filter!: Filter;
  magazineList!: Magazine[];
  options!: Magazine [];

  constructor(
    private auth: AuthService,
    private service: EditorReportsService,
    private formBuilder: FormBuilder,
    private editorService: EditorService
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
      id: [0],
      startDate: [null],
      endDate: [null]
    });
    this.submit();
  }

  submit(): void {
    this.filter = this.form.value as Filter;
    this.service.getSubscriptionReport(this.filter).subscribe ({
      next: (magazineList: Magazine[]) => {
        this.magazineList = magazineList;
      },
      error: (error: any) => {
        console.log('error al cargar el reporte de suscripciones a revistas', error);
        this.auth.validate(error);
      }
    })
  }
}