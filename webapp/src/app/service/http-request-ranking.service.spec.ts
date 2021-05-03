import { TestBed } from '@angular/core/testing';

import { HttpRequestRankingService } from './http-request-ranking.service';

describe('HttpRequestRankingService', () => {
  let service: HttpRequestRankingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpRequestRankingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
