import { Component, OnInit } from '@angular/core';
import { AdminHeaderComponent} from '../admin-header/admin-header.component';
import { Magazine } from '../../../entities/magazine';
import { MagazineService } from '../../../services/editor-service/magazine-service';
import { AuthService } from '../../../services/auth';
import { NewMagazinesListComponent } from '../new-magazines-list/new-magazines-list.component';

@Component({
  selector: 'app-admin-home',
  standalone: true,
  imports: [AdminHeaderComponent, NewMagazinesListComponent],
  templateUrl: './admin-home.component.html',
  styleUrl: './admin-home.component.css'
})
export class AdminHomeComponent implements OnInit{
  magazineList!: Magazine[];

  constructor(private service: MagazineService, private auth: AuthService){}

  ngOnInit(): void {
      this.service.getNewMagazineList().subscribe({
        next: (magazineList: Magazine[]) => {
          this.magazineList = magazineList;
          console.log('se cargo'+magazineList)
          console.log(magazineList[0].tittle);
          console.log(magazineList[0].id);
          console.log(magazineList[1].tittle)
        },
        error: (error: any) => {
          console.log(error);
          this.auth.validate(error);
        }
      })
  }
}
