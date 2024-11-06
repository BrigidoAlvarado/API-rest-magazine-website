import { Component, OnInit } from '@angular/core';
import { EarningReport } from '../../../../entities/admin-report/earning-report';
import { AdminReportService } from '../../../../services/admin-report-service';
import { AuthService } from '../../../../services/auth';
import { AdminHeaderComponent } from "../../admin-header/admin-header.component";
import { ShowAdComponent } from "../../../ad/show-ad/show-ad.component";

@Component({
  selector: 'app-earning',
  standalone: true,
  imports: [AdminHeaderComponent, ShowAdComponent],
  templateUrl: './earning.component.html',
  styleUrl: './earning.component.css'
})
export class EarningComponent implements OnInit{
url: string ='admin-earning';
  earningReport!: EarningReport;

  constructor(private service: AdminReportService, private auth: AuthService){}

  ngOnInit(): void {
      this.service.getEarningReport().subscribe({
        next: (earningReport: EarningReport) => {
          this.earningReport = earningReport;
          console.log(earningReport.magazineList.length);
          console.log(earningReport.lockAdList.length);
        },
        error: (error: any) => {
          console.log('error al cargar el earning report ',error);
          this.auth.validate(error);
        }
      })
  }

}
