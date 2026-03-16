import { IBook, NewBook } from './book.model';

export const sampleWithRequiredData: IBook = {
  id: 3991,
  isbn: 'blue regal',
  name: 'nocturnal wordy',
  publishYear: 'for ',
  copies: 16468,
};

export const sampleWithPartialData: IBook = {
  id: 20784,
  isbn: 'blah legislat',
  name: 'upwardly',
  publishYear: 'matt',
  copies: 22742,
};

export const sampleWithFullData: IBook = {
  id: 8637,
  isbn: 'sniff',
  name: 'however quicker',
  publishYear: 'reas',
  copies: 23703,
  picture: 'accelerator fuss',
};

export const sampleWithNewData: NewBook = {
  isbn: 'taxicab nor',
  name: 'agreeable boo',
  publishYear: 'boyf',
  copies: 543,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
