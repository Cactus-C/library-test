import dayjs from 'dayjs/esm';

import { IBorrowedBook, NewBorrowedBook } from './borrowed-book.model';

export const sampleWithRequiredData: IBorrowedBook = {
  id: 27089,
  borrowDate: dayjs('2026-03-15T21:52'),
};

export const sampleWithPartialData: IBorrowedBook = {
  id: 22378,
  borrowDate: dayjs('2026-03-15T11:51'),
};

export const sampleWithFullData: IBorrowedBook = {
  id: 29509,
  borrowDate: dayjs('2026-03-15T08:07'),
};

export const sampleWithNewData: NewBorrowedBook = {
  borrowDate: dayjs('2026-03-15T08:39'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
