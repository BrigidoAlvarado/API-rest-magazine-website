import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Filter } from '../../../../entities/filter';
import { LockAd } from '../../../../entities/lockAd';
import { AuthService } from '../../../../services/auth';
import { EditorReportsService } from '../../../../services/editor-reports';
import { EditorHeaderComponent } from "../../editor-header/editor-header.component";
import { ShowAdComponent } from "../../../ad/show-ad/show-ad.component";

@Component({
  selector: 'app-editor-payment-report',
  standalone: true,
  imports: [EditorHeaderComponent, FormsModule, ReactiveFormsModule, ShowAdComponent],
  templateUrl: './editor-payment-report.component.html',
  styleUrl: './editor-payment-report.component.css'
})
export class EditorPaymentReportComponent implements OnInit{

  form!: FormGroup;
  filter!: Filter;
  lockAdList!: LockAd [];

  constructor(
    private auth: AuthService,
    private formBuilder: FormBuilder,
    private service: EditorReportsService
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
    this.service.getPaymentReport(this.filter).subscribe({
      next: (lockAdList: LockAd[]) => {
        this.lockAdList = lockAdList;
      },
      error: (error: any) => {
        console.log('error al cargar el reporte de pagos ',error);
        this.auth.validate(error);
      }
    });
  }

  calculateTotal(): number {
    let total = 0;
    if(this.lockAdList.length > 0){
      this.lockAdList.forEach( lock => {
        total += lock.cost;
      });
    }
    return total;
  }

}
