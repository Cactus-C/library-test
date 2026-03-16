import { Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'authority',
    data: { pageTitle: 'librarymdApp.adminAuthority.home.title' },
    loadChildren: () => import('./admin/authority/authority.routes'),
  },
  {
    path: 'book',
    data: { pageTitle: 'librarymdApp.book.home.title' },
    loadChildren: () => import('./book/book.routes'),
  },
  {
    path: 'author',
    data: { pageTitle: 'librarymdApp.author.home.title' },
    loadChildren: () => import('./author/author.routes'),
  },
  {
    path: 'client',
    data: { pageTitle: 'librarymdApp.client.home.title' },
    loadChildren: () => import('./client/client.routes'),
  },
  {
    path: 'publisher',
    data: { pageTitle: 'librarymdApp.publisher.home.title' },
    loadChildren: () => import('./publisher/publisher.routes'),
  },
  {
    path: 'borrowed-book',
    data: { pageTitle: 'librarymdApp.borrowedBook.home.title' },
    loadChildren: () => import('./borrowed-book/borrowed-book.routes'),
  },
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;
