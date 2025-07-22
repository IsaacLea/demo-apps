import { Component, signal } from '@angular/core';
import { Greeting } from '../components/greeting/greeting';
import { input } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Counter } from '../components/counter/counter';

@Component({
  selector: 'app-home',
  imports: [Greeting, FormsModule, Counter],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {
  protected readonly message = 'Welcome to the Home Component!';

  protected readonly greetingMessage = signal('Hello from Home Component!');
  protected newMessage = '';

  updateGreeting() {
    if (this.newMessage.trim()) {
      this.greetingMessage.set(this.newMessage);
      this.newMessage = '';
    }
  }

  onKeyPress(event: KeyboardEvent) {
    console.log('Key pressed:', event.key);
    // if (event.key === 'Enter') {
    //   this.updateGreeting();
    // }
  }
}
