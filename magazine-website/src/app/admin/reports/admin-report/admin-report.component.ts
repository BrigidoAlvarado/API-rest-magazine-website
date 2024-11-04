import { Component } from '@angular/core';
import { AdminHeaderComponent } from "../../admin-header/admin-header.component";
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-admin-report',
  standalone: true,
  imports: [AdminHeaderComponent, RouterModule],
  templateUrl: './admin-report.component.html',
  styleUrl: './admin-report.component.css'
})
export class AdminReportComponent {

}
