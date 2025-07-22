import { Component, inject, OnInit, signal } from '@angular/core';
import { TodoItem } from '../components/todo-item/todo-item';
import { TodosService } from '../service/todos.service';
import { Todo } from '../model/todo.type';
import { FormsModule } from '@angular/forms';
import { FilterTodosPipe } from '../pipes/filter-todos-pipe';

@Component({
  selector: 'app-todos',
  imports: [TodoItem, FormsModule, FilterTodosPipe],
  templateUrl: './todos.html',
  styleUrl: './todos.css'
})
// Implementing OnInit to handle initialization logic
export class Todos implements OnInit {
  

  // Injecting the TodosService to access todo items
  todoService = inject(TodosService);

  isLoading = signal<boolean>(true); // Signal to track loading state
  todoItems = signal<Todo[]>([]);
  searchTerm = signal<string>(''); // Signal for search term

  ngOnInit(): void {
    console.log('Todos component initialized');
    // console.log('Todo items:', this.todoService.todoItems);
    // Initializing todoItems signal with the items from the service

    this.todoService.fetchTodos()
      .subscribe({
        next: (todos) => {
          console.log('Fetched todos:', todos);
          // Ensure we have an array
          if (Array.isArray(todos)) {
            this.todoItems.set(todos);
          } else {
            console.error('Expected array but got:', todos);
            this.todoItems.set([]);
          }
          this.isLoading.set(false); 
        },
        error: (error) => {
          console.error('Error fetching todos:', error);
          this.todoItems.set([]); // Set empty array on error
          this.isLoading.set(false);
          // You could also show a user-friendly error message here
          // this.errorMessage = 'Failed to load todo items';
        }
      });

    // Handle the todo item here
  
    // this.todoItems.set(this.todoService.fetchTodo().pipe();
  }

  updateTodoItem(todoItem: Todo) {
  // Handle the toggled todo item here
  console.log('Parent received item:', todoItem);

  // Update the local todoItems signal with the toggled todo
  this.todoItems.update((items) =>
    items.map((item) => (item.id === todoItem.id ? { ...item, completed: !todoItem.completed } : item))
  );

  }
  
}
