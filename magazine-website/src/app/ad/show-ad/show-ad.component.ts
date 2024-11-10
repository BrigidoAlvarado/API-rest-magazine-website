import { Component, Input, input } from '@angular/core';
import { ShowAdBodyComponent } from "../show-ad-body/show-ad-body.component";

@Component({
  selector: 'app-show-ad',
  standalone: true,
  imports: [ShowAdBodyComponent],
  templateUrl: './show-ad.component.html',
  styleUrl: './show-ad.component.css'
})
export class ShowAdComponent {
  @Input({required: true})
  url!: string;
  @Input({required: false})
  editor!: string;
  
  type!: string;

  constructor(){
    this.type = this.getType();
  }

  private getRandomNumber(): number {
    return Math.floor(Math.random() * 3) + 1;
  }

   getType(): string {
    switch(this.getRandomNumber()){
      case 1: return 'textAd';

      case 2: return 'textImageAd';

      case 3: return 'videoAd';      

      default: return 'error';
    }
  }
}
