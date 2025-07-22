import { Routes } from '@angular/router';


import { Todos } from './todos/todos';
import { Home } from './home/home';

// This approach eagerly loads all the components for the routes.  For larger applications, consider using lazy loading for better performance.
export const routes: Routes = [

    { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'home', component: Home },
    { path: 'todos', component: Todos }

];


