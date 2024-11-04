import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Filter } from '../../../../entities/filter';
import { Ad } from '../../../../entities/ad/ad';
import { AdminReportService } from '../../../../services/admin-report-service';
import { AuthService } from '../../../../services/auth';
import { AdminHeaderComponent } from "../../admin-header/admin-header.component";

@Component({
  selector: 'app-admin-ad-reports',
  standalone: true,
  imports: [AdminHeaderComponent, FormsModule, ReactiveFormsModule],
  templateUrl: './admin-ad-reports.component.html',
  styleUrl: './admin-ad-reports.component.css'
})
export class AdminAdReportsComponent implements OnInit {
  form!: FormGroup;
  filter!: Filter;
  adList!: Ad[];

  constructor(
    private service: AdminReportService,
    private auth: AuthService,
    private formBuilder: FormBuilder){}

    ngOnInit(): void {  
      this.form = this.formBuilder.group({
        startDate: [null],
        endDate: [null],
        kind: [null]
      });
      this.submit();
    }

    submit(): void {
      this.filter = this.form.value as Filter;

      this.setNull();

      this.service.getAdReports(this.filter).subscribe({
        next: (adList: Ad[]) => {
          this.adList = adList;
        },
        error: (error: any) => {
          console.log('error al cargar el reporte de anuncios',error);
          this.auth.validate(error);
        }
      });
    }

   private setNull(): void{
      if(this.filter.kind === ''){
        this.filter.kind = null;
      }
      if( !this.filter.startDate ){
        this.filter.startDate = null;
      }
      if( !this.filter.endDate ){
        this.filter.endDate = null;
      }
    }
}
