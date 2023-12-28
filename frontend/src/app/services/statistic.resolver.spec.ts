import { TestBed } from '@angular/core/testing';
import { ResolveFn } from '@angular/router';

import { statisticResolver } from './statistic.resolver';

describe('statisticResolver', () => {
  const executeResolver: ResolveFn<boolean> = (...resolverParameters) => 
      TestBed.runInInjectionContext(() => statisticResolver(...resolverParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeResolver).toBeTruthy();
  });
});
