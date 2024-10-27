import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { RestConstants } from '../services/rest-constants';
import { AuthService } from '../services/auth';
import { Amount } from '../entities/amount'

@Injectable({
    providedIn: 'root'
})
export class PaymentToUpService {
    
    constructor(private http: HttpClient,private auth: AuthService){}

    sendPost(amount: Amount): Observable<Amount> {
        console.log('enviando la recarga');
        const restConstants = new RestConstants();
        const headers = this.auth.getHeader();
        return this.http.post<Amount>(restConstants.API_URL+'payment-to-up',amount,{ headers });
    }
}