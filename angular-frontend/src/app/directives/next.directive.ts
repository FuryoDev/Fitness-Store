import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
  selector: '[appNext]'
})
export class NextDirective {

  constructor(private elRef: ElementRef) { }

  @HostListener('click')
  nextFunction() {
    let elm = this.elRef.nativeElement.parentElement.parentElement.children[0];
    let items = elm.getElementsByClassName("item");

    // Déplacer le slider vers la gauche de la largeur d'un élément
    elm.style.transform = `translateX(${items[0].offsetWidth}px)`;

    // Après l'animation, déplacer le premier élément à la fin et réinitialiser la transformation
    setTimeout(() => {
      elm.appendChild(items[0]);
      elm.style.transform = '';
    }, 500);
  }
}
