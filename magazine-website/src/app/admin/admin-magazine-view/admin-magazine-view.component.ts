import { Component, OnInit } from '@angular/core';
import { AdminHeaderComponent } from "../admin-header/admin-header.component";
import { Magazine } from '../../../entities/magazine';
import { AdminService } from '../../../services/admin-servie';
import { AuthService } from '../../../services/auth';
import { UpdateMagazineCostFormComponent } from "../update-magazine-cost-form/update-magazine-cost-form.component";

@Component({
  selector: 'app-admin-magazine-view',
  standalone: true,
  imports: [AdminHeaderComponent, UpdateMagazineCostFormComponent],
  templateUrl: './admin-magazine-view.component.html',
  styleUrl: './admin-magazine-view.component.css'
})
export class AdminMagazineViewComponent implements OnInit {

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
