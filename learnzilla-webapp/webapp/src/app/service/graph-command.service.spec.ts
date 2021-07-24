import { TestBed } from '@angular/core/testing';

import { GraphCommandService } from './graph-command.service';

describe('GraphCommandService', () => {
  let service: GraphCommandService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GraphCommandService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
