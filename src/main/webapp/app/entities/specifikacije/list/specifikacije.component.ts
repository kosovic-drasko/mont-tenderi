import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISpecifikacije } from '../specifikacije.model';
import { SpecifikacijeService } from '../service/specifikacije.service';
import { SpecifikacijeDeleteDialogComponent } from '../delete/specifikacije-delete-dialog.component';

@Component({
  selector: 'jhi-specifikacije',
  templateUrl: './specifikacije.component.html',
})
export class SpecifikacijeComponent implements OnInit {
  specifikacijes?: ISpecifikacije[];
  isLoading = false;

  constructor(protected specifikacijeService: SpecifikacijeService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.specifikacijeService.query().subscribe({
      next: (res: HttpResponse<ISpecifikacije[]>) => {
        this.isLoading = false;
        this.specifikacijes = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: ISpecifikacije): number {
    return item.id!;
  }

  delete(specifikacije: ISpecifikacije): void {
    const modalRef = this.modalService.open(SpecifikacijeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.specifikacije = specifikacije;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
