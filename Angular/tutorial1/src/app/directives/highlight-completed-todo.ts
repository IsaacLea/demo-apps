import { Directive, ElementRef, inject, input, effect } from '@angular/core';

@Directive({
  selector: '[appHighlightCompletedTodo]'
})
export class HighlightCompletedTodo {

  isCompleted = input<boolean>(false);
  element = inject(ElementRef);

  constructor() {
    // Use effect to react to signal changes
    effect(() => {
      if (this.isCompleted()) {
        this.element.nativeElement.style.backgroundColor = 'lightgreen';
        this.element.nativeElement.style.textDecoration = 'line-through';
      } else {
        this.element.nativeElement.style.backgroundColor = 'lightcoral';
        this.element.nativeElement.style.textDecoration = 'none';
      }
      
      // Additional styles you could add:
      // this.element.nativeElement.style.color = this.isCompleted() ? 'darkgreen' : 'darkred';
      // this.element.nativeElement.style.fontWeight = this.isCompleted() ? 'bold' : 'normal';
    });
  }

}
