import { Component, OnInit } from '@angular/core';
import { AdminHeaderComponent } from "../admin-header/admin-header.component";
import { Ad } from '../../../entities/ad/ad';
import { AdStatusComponent } from "../../ad/ad-status/ad-status.component";
import { AdService } from '../../../services/ad-service';
import { AuthService } from '../../../services/auth';

@Component({
  selector: 'app-update-ad-status',
  standalone: true,
  imports: [AdminHeaderComponent, AdStatusComponent],
  templateUrl: './update-ad-status.component.html',
  styleUrl: './update-ad-status.component.css'
})
export class UpdateAdStatusComponent implements OnInit {

  adList!: Ad[]

  constructor(
    private service: AdService,
    private auth: AuthService
  ){}

  ngOnInit(): void {
      this.service.getAdList().subscribe({
        next: (adList: Ad[]) => {
          this.adList = adList;
        },
        error: (error: any) => {
          console.log('error al cargar la lista de todos los anuncios', error);
          this.auth.validate(error);
        }
      })
  }
}