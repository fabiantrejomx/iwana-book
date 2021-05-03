import { TestBed } from '@angular/core/testing';

import { HttpRequestAuthorService } from './http-request-author.service';

describe('HttpRequestAuthorService', () => {
  let service: HttpRequestAuthorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpRequestAuthorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
