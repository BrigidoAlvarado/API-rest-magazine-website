import { Injectable } from "@angular/core";
import { RestConstants } from './rest-constants';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Account} from '../entities/account'

@Injectable({
    providedIn: 'root'
})

export class NewAccountService{
    restConstants: RestConstants;
    
    constructor(private httpClient: HttpClient){
        this.restConstants = new RestConstants( );
    }
    
    uploadFile(formData: FormData): Observable<{ token: string }>{        
        return this.httpClient.post<{ token: string }>(this.restConstants.API_URL+'new-account', formData);
    }
}