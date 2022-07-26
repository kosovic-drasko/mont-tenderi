import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IPonude, Ponude } from '../ponude.model';
import { PonudeService } from '../service/ponude.service';
import { IPostupci } from 'app/entities/postupci/postupci.model';
import { PostupciService } from 'app/entities/postupci/service/postupci.service';
import { IPonudjaci } from 'app/entities/ponudjaci/ponudjaci.model';
import { PonudjaciService } from 'app/entities/ponudjaci/service/ponudjaci.service';
import { ISpecifikacije } from 'app/entities/specifikacije/specifikacije.model';
import { SpecifikacijeService } from 'app/entities/specifikacije/service/specifikacije.service';

@Component({
  selector: 'jhi-ponude-update',
  templateUrl: './ponude-update.component.html',
})
export class PonudeUpdateComponent implements OnInit {
  isSaving = false;

  postupcisSharedCollection: IPostupci[] = [];
  ponudjacisSharedCollection: IPonudjaci[] = [];
  specifikacijesSharedCollection: ISpecifikacije[] = [];

  editForm = this.fb.group({
    id: [],
    sifraPostupka: [null, [Validators.required]],
    sifraPonude: [null, [Validators.required]],
    brojPartije: [null, [Validators.required]],
    nazivProizvodjaca: [],
    zasticeniNaziv: [],
    ponudjanaKolicina: [null, [Validators.required]],
    ponudjenaVrijednost: [null, [Validators.required]],
    jedinicnaCijena: [],
    rokIsporuke: [null, [Validators.required]],
    sifraPonudjaca: [null, [Validators.required]],
    selected: [],
    postupci: [],
    ponudjaci: [],
    specifikacije: [],
  });

  constructor(
    protected ponudeService: PonudeService,
    protected postupciService: PostupciService,
    protected ponudjaciService: PonudjaciService,
    protected specifikacijeService: SpecifikacijeService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ponude }) => {
      this.updateForm(ponude);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ponude = this.createFromForm();
    if (ponude.id !== undefined) {
      this.subscribeToSaveResponse(this.ponudeService.update(ponude));
    } else {
      this.subscribeToSaveResponse(this.ponudeService.create(ponude));
    }
  }

  trackPostupciById(_index: number, item: IPostupci): number {
    return item.id!;
  }

  trackPonudjaciById(_index: number, item: IPonudjaci): number {
    return item.id!;
  }

  trackSpecifikacijeById(_index: number, item: ISpecifikacije): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPonude>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(ponude: IPonude): void {
    this.editForm.patchValue({
      id: ponude.id,
      sifraPostupka: ponude.sifraPostupka,
      sifraPonude: ponude.sifraPonude,
      brojPartije: ponude.brojPartije,
      nazivProizvodjaca: ponude.nazivProizvodjaca,
      zasticeniNaziv: ponude.zasticeniNaziv,
      ponudjanaKolicina: ponude.ponudjanaKolicina,
      ponudjenaVrijednost: ponude.ponudjenaVrijednost,
      jedinicnaCijena: ponude.jedinicnaCijena,
      rokIsporuke: ponude.rokIsporuke,
      sifraPonudjaca: ponude.sifraPonudjaca,
      selected: ponude.selected,
      postupci: ponude.postupci,
      ponudjaci: ponude.ponudjaci,
      specifikacije: ponude.specifikacije,
    });

    this.postupcisSharedCollection = this.postupciService.addPostupciToCollectionIfMissing(this.postupcisSharedCollection, ponude.postupci);
    this.ponudjacisSharedCollection = this.ponudjaciService.addPonudjaciToCollectionIfMissing(
      this.ponudjacisSharedCollection,
      ponude.ponudjaci
    );
    this.specifikacijesSharedCollection = this.specifikacijeService.addSpecifikacijeToCollectionIfMissing(
      this.specifikacijesSharedCollection,
      ponude.specifikacije
    );
  }

  protected loadRelationshipsOptions(): void {
    this.postupciService
      .query()
      .pipe(map((res: HttpResponse<IPostupci[]>) => res.body ?? []))
      .pipe(
        map((postupcis: IPostupci[]) =>
          this.postupciService.addPostupciToCollectionIfMissing(postupcis, this.editForm.get('postupci')!.value)
        )
      )
      .subscribe((postupcis: IPostupci[]) => (this.postupcisSharedCollection = postupcis));

    this.ponudjaciService
      .query()
      .pipe(map((res: HttpResponse<IPonudjaci[]>) => res.body ?? []))
      .pipe(
        map((ponudjacis: IPonudjaci[]) =>
          this.ponudjaciService.addPonudjaciToCollectionIfMissing(ponudjacis, this.editForm.get('ponudjaci')!.value)
        )
      )
      .subscribe((ponudjacis: IPonudjaci[]) => (this.ponudjacisSharedCollection = ponudjacis));

    this.specifikacijeService
      .query()
      .pipe(map((res: HttpResponse<ISpecifikacije[]>) => res.body ?? []))
      .pipe(
        map((specifikacijes: ISpecifikacije[]) =>
          this.specifikacijeService.addSpecifikacijeToCollectionIfMissing(specifikacijes, this.editForm.get('specifikacije')!.value)
        )
      )
      .subscribe((specifikacijes: ISpecifikacije[]) => (this.specifikacijesSharedCollection = specifikacijes));
  }

  protected createFromForm(): IPonude {
    return {
      ...new Ponude(),
      id: this.editForm.get(['id'])!.value,
      sifraPostupka: this.editForm.get(['sifraPostupka'])!.value,
      sifraPonude: this.editForm.get(['sifraPonude'])!.value,
      brojPartije: this.editForm.get(['brojPartije'])!.value,
      nazivProizvodjaca: this.editForm.get(['nazivProizvodjaca'])!.value,
      zasticeniNaziv: this.editForm.get(['zasticeniNaziv'])!.value,
      ponudjanaKolicina: this.editForm.get(['ponudjanaKolicina'])!.value,
      ponudjenaVrijednost: this.editForm.get(['ponudjenaVrijednost'])!.value,
      jedinicnaCijena: this.editForm.get(['jedinicnaCijena'])!.value,
      rokIsporuke: this.editForm.get(['rokIsporuke'])!.value,
      sifraPonudjaca: this.editForm.get(['sifraPonudjaca'])!.value,
      selected: this.editForm.get(['selected'])!.value,
      postupci: this.editForm.get(['postupci'])!.value,
      ponudjaci: this.editForm.get(['ponudjaci'])!.value,
      specifikacije: this.editForm.get(['specifikacije'])!.value,
    };
  }
}
