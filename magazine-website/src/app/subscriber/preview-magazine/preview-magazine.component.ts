import { Component, Input, OnInit } from '@angular/core';
import { Magazine } from '../../../entities/magazine';
import { MagazineService } from '../../../services/editor-service/magazine-service';
import { AuthService } from '../../../services/auth';

@Component({
  selector: 'app-preview-magazine',
  standalone: true,
  imports: [],
  templateUrl: './preview-magazine.component.html',
  styleUrl: './preview-magazine.component.css'
})
export class PreviewMagazineComponent implements OnInit{
  @Input({required: true})
  id!: number;
  magazine!: Magazine;

  constructor(private magazineService: MagazineService, private auth: AuthService){ }

  ngOnInit(): void {
      this.magazineService.getMagazineById(this.id).subscribe({
        next: (magazine: Magazine) => {
          console.log('magazine preview success');
          console.log(magazine.tagsList[0]);
          this.magazine = magazine;
        },
        error: (error: any) => {
          console.log(error);
          this.auth.validate(error);
        }
      });
  }

}
