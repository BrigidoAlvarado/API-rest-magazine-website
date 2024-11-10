import { Component, Input } from '@angular/core';
import { FileService } from '../../../services/file-service';

@Component({
  selector: 'app-download-pdf',
  standalone: true,
  imports: [],
  templateUrl: './download-pdf.component.html',
  styleUrl: './download-pdf.component.css'
})
export class DownloadPdfComponent {

  @Input({required: true})
  id!: number

  constructor(
    private service: FileService
  ){}

  downloadPdf(): void {

    this.service.downloadPdf(this.id).subscribe({
      next: (response) => {
        const url = window.URL.createObjectURL(response.blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = response.fileName;
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
        window.URL.revokeObjectURL(url);
      },
      error: (error: any) => {
        console.error('Error al descargar el archivo', error);
      }
    });
  }
}
