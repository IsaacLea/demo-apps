import { Component, input, Input, output } from '@angular/core';
import { Todo } from '../../model/todo.type';
import { HighlightCompletedTodo } from '../../directives/highlight-completed-todo';
import { UpperCasePipe } from '@angular/common';

@Component({
  selector: 'app-todo-item',
  imports: [HighlightCompletedTodo, UpperCasePipe],
  templateUrl: './todo-item.html',
  styleUrl: './todo-item.css'
})
export class TodoItem {
  todo = input.required<Todo>();

  toggleTodo(Todo: Todo): void {
    this.todo().completed = !this.todo().completed;
  }

  // Using output to emit an event when the todo is toggled
  // This allows parent components to listen for changes
  todoToggled = output<Todo>();

  todoClicked() {
    // Emit the current todo item when it is clicked
    this.todoToggled.emit(this.todo());
  }

}
