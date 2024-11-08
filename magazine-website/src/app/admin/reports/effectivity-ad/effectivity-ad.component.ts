import { Component, OnInit } from '@angular/core';
import { Advertiser } from '../../../../entities/advertiser';
import { AdminHeaderComponent } from "../../admin-header/admin-header.component";
import { ShowAdComponent } from "../../../ad/show-ad/show-ad.component";
import { AdminReportService } from '../../../../services/admin-report-service';
import { AuthService } from '../../../../services/auth';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Filter } from '../../../../entities/filter';

@Component({
  selector: 'app-effectivity-ad',
  standalone: true,
  imports: [AdminHeaderComponent, ShowAdComponent, FormsModule, ReactiveFormsModule],
  templateUrl: './effectivity-ad.component.html',
  styleUrl: './effectivity-ad.component.css'
})
export class EffectivityAdComponent implements OnInit {

  url: string = 'effectivity-ad-report';
  advertiserList!: Advertiser[];
  form!: FormGroup;
  filter!: Filter;

  constructor(
    private service: AdminReportService,
    private auth: AuthService,
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
    console.log('start date: ',this.filter.startDate);
    console.log('end date: ',this.filter.endDate);
    this.service.getEffectivityAdReport(this.filter).subscribe({
      next: (advertiserList: Advertiser[]) => {
        this.advertiserList = advertiserList;
      },
      error: (error: any) => {
        console.log('error al cagar el repote de efectividad ',error);
        this.auth.validate(error);
      }
    });
  }
}