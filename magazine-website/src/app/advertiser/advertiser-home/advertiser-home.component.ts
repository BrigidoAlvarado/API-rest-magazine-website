import { Component, OnInit } from '@angular/core';
import { AdvHeaderComponent } from "../adv-header/adv-header.component";
import { Ad } from '../../../entities/ad/ad';
import { AdService } from '../../../services/ad-service';
import { PurchasedAdsListComponent } from "../purchased-ads-list/purchased-ads-list.component";
import { EditAdViewComponent } from "../../ad/edit-ad-view/edit-ad-view.component";
import { ShowAdComponent } from "../../ad/show-ad/show-ad.component";

@Component({
  selector: 'app-advertiser-home',
  standalone: true,
  imports: [AdvHeaderComponent, PurchasedAdsListComponent, EditAdViewComponent, ShowAdComponent],
  templateUrl: './advertiser-home.component.html',
  styleUrl: './advertiser-home.component.css'
})
export class AdvertiserHomeComponent implements OnInit{
  ads!: Ad[];
  url: string = 'advertiser-home';

  constructor(private adService: AdService){ }

  ngOnInit(): void {
     this.adService.getAds().subscribe({
      next: (ads: Ad[]) => {
        this.ads = ads;
      },
      error: (error: any) => {
        console.log(error);
        window.alert('Ha ocurrido un erro al cargar los anuncios comprados del usuario');
      }
     });
  }
}
