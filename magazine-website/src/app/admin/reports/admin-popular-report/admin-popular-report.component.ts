import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Filter } from '../../../../entities/filter';
import { AuthService } from '../../../../services/auth';
import { AdminReportService } from '../../../../services/admin-report-service';
import { Magazine } from '../../../../entities/magazine';
import { AdminHeaderComponent } from "../../admin-header/admin-header.component";
import { ShowAdComponent } from "../../../ad/show-ad/show-ad.component";

@Component({
  selector: 'app-admin-popular-report',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, AdminHeaderComponent, ShowAdComponent],
  templateUrl: './admin-popular-report.component.html',
  styleUrl: './admin-popular-report.component.css'
})
export class AdminPopularReportComponent implements OnInit{
  url: string = 'admin-popular-report';
  form!: FormGroup;
  filter!: Filter;
  magazineList!: Magazine [];

  constructor(
    private auth: AuthService,
    private service: AdminReportService,
    private formBuilder: FormBuilder
  ){}

  ngOnInit(): void {
      this.form = this.formBuilder.group({
        startDate: [null],
        endDate: [null]
      });
      this.submit();
  }

  submit(): void {

    this.filter = this.form.value as Filter;
    
    this.setNull();
    
    this.service.getPopularMagazineReport(this.filter).subscribe({
      next: (magazineList: Magazine[]) => {
        this.magazineList = magazineList;
      },
      error: (error: any) => {
        console.log('error al cargar el reporte de revistas populares',error);
        this.auth.validate(error);
      }
    });

  }

  private setNull(): void{

    if( !this.filter.startDate || this.filter.startDate.toString() === ''){
      this.filter.startDate = null;
    }
    if( !this.filter.endDate || this.filter.endDate.toString() === ''){
      this.filter.endDate = null;
    }
  }
}
