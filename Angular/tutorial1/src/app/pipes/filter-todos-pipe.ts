import { Pipe, PipeTransform } from '@angular/core';
import { Todo } from '../model/todo.type';

@Pipe({
  name: 'filterTodos'
})
export class FilterTodosPipe implements PipeTransform {
  /**
   * Filters the todo items based on the search term.
   * @param value The array of todo items to filter.
   * @param searchTerm The term to filter the todo items by.
   * @returns Filtered array of todo items that match the search term.
   */
  transform(value: Todo[], searchTerm: string): Todo[] {
    if (!value) {
      console.log('No value provided, returning empty array');
      return [];
    }

    if (!searchTerm || searchTerm.trim() === '') {
      console.log('No search term or blank string provided, returning all todos');
      return value;
    }

    const lowerCaseSearchTerm = searchTerm.toLowerCase();
    return value.filter(todo =>
      todo.title.toLowerCase().includes(lowerCaseSearchTerm)
    );
  }

}
