import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { SpecifikacijeService } from '../service/specifikacije.service';

import { SpecifikacijeComponent } from './specifikacije.component';

describe('Specifikacije Management Component', () => {
  let comp: SpecifikacijeComponent;
  let fixture: ComponentFixture<SpecifikacijeComponent>;
  let service: SpecifikacijeService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [SpecifikacijeComponent],
    })
      .overrideTemplate(SpecifikacijeComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(SpecifikacijeComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(SpecifikacijeService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 123 }],
          headers,
        })
      )
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.specifikacijes?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
