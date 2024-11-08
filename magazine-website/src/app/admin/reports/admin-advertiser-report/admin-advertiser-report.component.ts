import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Advertiser } from '../../../../entities/advertiser';
import { AdminReportService } from '../../../../services/admin-report-service';
import { AuthService } from '../../../../services/auth';
import { AdminHeaderComponent } from "../../admin-header/admin-header.component";
import { ShowAdComponent } from "../../../ad/show-ad/show-ad.component";

@Component({
  selector: 'app-admin-advertiser-report',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, AdminHeaderComponent, ShowAdComponent],
  templateUrl: './admin-advertiser-report.component.html',
  styleUrl: './admin-advertiser-report.component.css'
})
export class AdminAdvertiserReportComponent implements OnInit{
  url: string = 'admin-advertiser-report';
  form!: FormGroup;
  advertiserList!: Advertiser[]

  constructor(
    private service: AdminReportService,
    private auth: AuthService,
    private formBuilder: FormBuilder
  ){}

  ngOnInit(): void {
      this.form = this.formBuilder.group({
        userName: [null]
      });
      this.submit();
  }

  submit(): void {
    this.service.getAdvertiserReport(this.form.get('userName')?.value).subscribe({
      next: (advertiserLis: Advertiser[]) => {
        this.advertiserList = advertiserLis;
      },
      error: (error: any) => {
        console.log('error al cargar el reporte de ganancias anunciantes',error);
        this.auth.validate(error);
      }
    });
  }

  calculteIncome(): number {
    let total = 0;
    this.advertiserList.forEach( advertiser => {
      advertiser.adList.forEach( ad => {
        total += ad.cost;
      });
    });
    return total;
  }
}