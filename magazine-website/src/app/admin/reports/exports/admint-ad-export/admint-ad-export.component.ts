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
    private exportService: ExportService,
    private auth: AuthService
  ){
    console.log(`el tamanio del arreglo es ${this.adList}`);
  }

  

}
