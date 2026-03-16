import { IClient, NewClient } from './client.model';

export const sampleWithRequiredData: IClient = {
  id: 16289,
  firstName: 'Sadye',
  lastName: 'Macejkovic',
  phone: '(606) 840-815',
};

export const sampleWithPartialData: IClient = {
  id: 13585,
  firstName: 'Ilene',
  lastName: 'Barton',
  address: 'absolve mortally',
  phone: '(403) 529-330',
};

export const sampleWithFullData: IClient = {
  id: 31496,
  firstName: 'Zelda',
  lastName: 'Herman',
  address: 'advocate off granular',
  phone: '415.772.6928 ',
};

export const sampleWithNewData: NewClient = {
  firstName: 'Ida',
  lastName: 'Smitham',
  phone: '(322) 743-905',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
