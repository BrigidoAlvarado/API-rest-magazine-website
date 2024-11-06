import { Component, OnInit } from '@angular/core';
import { AdminHeaderComponent } from "../admin-header/admin-header.component";
import { Magazine } from '../../../entities/magazine';
import { AdminService } from '../../../services/admin-servie';
import { AuthService } from '../../../services/auth';
import { UpdateMagazineCostFormComponent } from "../update-magazine-cost-form/update-magazine-cost-form.component";
import { ShowAdComponent } from "../../ad/show-ad/show-ad.component";

@Component({
  selector: 'app-admin-magazine-view',
  standalone: true,
  imports: [AdminHeaderComponent, UpdateMagazineCostFormComponent, ShowAdComponent],
  templateUrl: './admin-magazine-view.component.html',
  styleUrl: './admin-magazine-view.component.css'
})
export class AdminMagazineViewComponent implements OnInit {

  url: string = 'admin-magazine';
  magazineList: Magazine[] | null = null;

  constructor(private service: AdminService, private auth: AuthService ){}

  ngOnInit(): void {
      this.service.getMagazineList().subscribe({
        next: (magazineList: Magazine[]) => {
          this.magazineList = magazineList;
        },
        error: (error: any ) => {
          console.log('error al cargar el listado de revistas:',error);
          this.auth.validate(error);
        }
      })
  }
}
