import { Component, OnInit } from '@angular/core';
import { AdminHeaderComponent } from "../../admin-header/admin-header.component";
import { Magazine } from '../../../../entities/magazine';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminReportService } from '../../../../services/admin-report-service';
import { AuthService } from '../../../../services/auth';
import { Filter } from '../../../../entities/filter';

@Component({
  selector: 'app-comment-magazine-report',
  standalone: true,
  imports: [AdminHeaderComponent,FormsModule, ReactiveFormsModule],
  templateUrl: './comment-magazine-report.component.html',
  styleUrl: './comment-magazine-report.component.css'
})
export class CommentMagazineReportComponent implements OnInit {

  magazineList!: Magazine[];
  form!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private service: AdminReportService,
    private auth: AuthService
  ){}

  ngOnInit(): void {
      this.form = this.formBuilder.group({
        startDate: [null],
        endDate: [null]
      });
      this.submit();
  }

  submit(): void {
    let filter = this.form.value as Filter;
    this.service.getCommentMagazineReport(filter).subscribe({
      next: (magazineList: Magazine[]) => {
        console.log('se cargo ',magazineList)
        this.magazineList = magazineList;
      },
      error: (error: any) => {
        console.log('error al cargar el reporte de revistas comentadas ',error);
        this.auth.validate(error);
      }
    });
  }
}
