import { Component, Input } from '@angular/core';
import { Ad } from '../../../../../entities/ad/ad';
import { ExportService } from '../../../../../services/export-service';
import { AuthService } from '../../../../../services/auth';

@Component({
  selector: 'app-admint-ad-export',
  standalone: true,
  imports: [],
  templateUrl: './admint-ad-export.component.html',
  styleUrl: './admint-ad-export.component.css'
})
export class AdmintAdExportComponent {

  @Input({required: true})
  adList!: Ad[]

  constructor(
    private service: ExportService,
    private auth: AuthService
  ){}

  submit(): void {

    this.service.exportAdminAd(this.adList).subscribe({
      next: (data: Blob) => {
        const blob = new Blob([data], { type: 'application/pdf' });
        const url = window.URL.createObjectURL(blob);
        window.open(url);
      },
      error: (error: any) => {
        console.log('error al exportar el reported de anuncios ',error);
        this.auth.validate(error);
      }
    });

  }
  
}
