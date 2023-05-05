import { Injectable } from '@angular/core';
import * as FileSaver from "file-saver";
import {saveAs} from "file-saver";

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor() { }

  saveImage(imageFile: File, productName: string) {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsArrayBuffer(imageFile);
      reader.onload = (event) => {
        if (event.target && event.target.result) {
          const imageData = event.target.result as ArrayBuffer;
          const newFilePath = `assets/images/${productName}`;
          const newFile = new File([imageData], productName, { type: imageFile.type });
          saveAs(newFile, newFilePath);
          console.log(`Image ${imageFile.name} copied and saved as ${newFilePath}`);
          resolve(newFilePath);
        } else {
          console.error(`Error reading image data for file ${imageFile.name}`);
          reject(`Error reading image data for file ${imageFile.name}`);
        }
      };
      reader.onerror = (event) => {
        console.error(event);
        reject(event);
      };
    });
  }
}
