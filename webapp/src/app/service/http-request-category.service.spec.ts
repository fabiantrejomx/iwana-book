import { TestBed } from '@angular/core/testing';

import { HttpRequestCategoryService } from './http-request-category.service';

describe('HttpRequestCategoryService', () => {
  let service: HttpRequestCategoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpRequestCategoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
