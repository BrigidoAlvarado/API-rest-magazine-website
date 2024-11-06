import { Component, OnInit } from '@angular/core';
import { AdminHeaderComponent} from '../admin-header/admin-header.component';
import { Magazine } from '../../../entities/magazine';
import { MagazineService } from '../../../services/editor-service/magazine-service';
import { AuthService } from '../../../services/auth';
import { NewMagazinesListComponent } from '../new-magazines-list/new-magazines-list.component';
import { GlobalCostListComponent } from "../global-cost-list/global-cost-list.component";
import { ShowAdComponent } from "../../ad/show-ad/show-ad.component";

@Component({
  selector: 'app-admin-home',
  standalone: true,
  imports: [AdminHeaderComponent, NewMagazinesListComponent, GlobalCostListComponent, ShowAdComponent],
  templateUrl: './admin-home.component.html',
  styleUrl: './admin-home.component.css'
})
export class AdminHomeComponent implements OnInit{
  magazineList: Magazine[] | null = null;
  url: string = 'admin-home';

  constructor(private service: MagazineService, private auth: AuthService){}

  ngOnInit(): void {
      this.service.getNewMagazineList().subscribe({
        next: (magazineList: Magazine[]) => {
          this.magazineList = magazineList;        
        },
        error: (error: any) => {
          console.log(error);
          this.auth.validate(error);
        }
      })
  }
}
