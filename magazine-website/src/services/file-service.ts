import { Injectable } from "@angular/core";
import { RestConstants } from "./rest-constants";
import { HttpClient } from "@angular/common/http";
import { AuthService } from "./auth";
import { map, Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class FileService {

    constants!: RestConstants;

    constructor(private http: HttpClient, private auth: AuthService){
        this.constants = new RestConstants();
    }

    getPhoto(userName: string, userType: string): Observable<Blob> {
        const headers = this.auth.getHeader();
        return this.http.get(`${this.constants.API_URL}file/photo/${userName}/${userType}`, 
         { 
            headers,
            responseType: 'blob' 
        });
    }

    getPdf(id: number): Observable<Blob> {
        const headers = this.auth.getHeader();
        return this.http.get(`${this.constants.API_URL}file/pdf/${id}`,
            { headers,
                responseType: 'blob'
            });
    }

    getImage(id: number): Observable<Blob> {
        const headers = this.auth.getHeader();
        return this.http.get(`${this.constants.API_URL}file/image/${id}`,
            { headers,
                responseType: 'blob'
            });
    }

    
    downloadPdf(fileId: number): Observable<{ blob: Blob, fileName: string }> {
        return this.http.get(`/api/file/download/${fileId}`, {
          responseType: 'blob',
          observe: 'response' // Permite acceder a los encabezados de respuesta
        }).pipe(
          map(response => {
            const contentDisposition = response.headers.get('Content-Disposition');
            let fileName = 'archivo.pdf'; // Nombre predeterminado
    
            if (contentDisposition) {
              const match = contentDisposition.match(/filename="(.+)"/);
              if (match) {
                fileName = match[1];
              }
            }
    
            return { blob: response.body as Blob, fileName };
          })
        );
      }
}