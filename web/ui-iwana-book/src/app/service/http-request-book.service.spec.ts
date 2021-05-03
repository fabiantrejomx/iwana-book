import { TestBed } from '@angular/core/testing';

import { HttpRequestBookService } from './http-request-book.service';

describe('HttpRequestBookService', () => {
  let service: HttpRequestBookService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpRequestBookService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
