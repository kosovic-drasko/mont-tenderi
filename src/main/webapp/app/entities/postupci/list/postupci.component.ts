import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPostupci } from '../postupci.model';
import { PostupciService } from '../service/postupci.service';
import { PostupciDeleteDialogComponent } from '../delete/postupci-delete-dialog.component';

@Component({
  selector: 'jhi-postupci',
  templateUrl: './postupci.component.html',
})
export class PostupciComponent implements OnInit {
  postupcis?: IPostupci[];
  isLoading = false;

  constructor(protected postupciService: PostupciService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.postupciService.query().subscribe({
      next: (res: HttpResponse<IPostupci[]>) => {
        this.isLoading = false;
        this.postupcis = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: IPostupci): number {
    return item.id!;
  }

  delete(postupci: IPostupci): void {
    const modalRef = this.modalService.open(PostupciDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.postupci = postupci;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
