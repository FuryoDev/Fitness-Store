import { Pipe, PipeTransform } from '@angular/core';
import * as numberToWords from 'number-to-words';
@Pipe({
  name: 'toCardinal'
})
export class ToCardinalPipe implements PipeTransform {
  transform(value: string, language: string = 'en'): string | null {
    if (value) {
      const number = numberToWords.toWords(value);
      return this.parseNumber(number);
    }
    return null;
  }

  private parseNumber(number: string): string {
    const numericValue = parseInt(number, 10);
    if (!isNaN(numericValue)) {
      return numericValue.toString();
    }
    return '';
  }
}
