import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Todo } from '../model/todo.type';

@Injectable({
  // Provided in root makes this service available application-wide
  providedIn: 'root'
})
export class TodosService {
  
  private apiUrl = 'https://jsonplaceholder.typicode.com/todos?_limit=50';
  
  // todoItems: Todo[] = [
  //   { id: 1, userId: 1, title: 'Learn Angular', completed: false },
  //   { id: 2, userId: 1, title: 'Build a Todo App', completed: false },
  //   { id: 3, userId: 1, title: 'Deploy the App', completed: false }
  // ];

  // Using inject() function for dependency injection
  private http = inject(HttpClient);

  fetchTodos(): Observable<Todo[]> {
    return this.http.get<Todo[]>(this.apiUrl);
  }
}
