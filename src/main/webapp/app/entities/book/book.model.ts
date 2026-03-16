import { IPublisher } from 'app/entities/publisher/publisher.model';
import { IAuthor } from 'app/entities/author/author.model';

export interface IBook {
  id: number;
  isbn?: string | null;
  name?: string | null;
  publishYear?: string | null;
  copies?: number | null;
  picture?: string | null;
  publisher?: Pick<IPublisher, 'id' | 'name'> | null;
  authors?: Pick<IAuthor, 'id' | 'lastName'>[] | null;
}

export type NewBook = Omit<IBook, 'id'> & { id: null };
