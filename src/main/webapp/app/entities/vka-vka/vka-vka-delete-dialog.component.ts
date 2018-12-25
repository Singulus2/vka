import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVkaVka } from 'app/shared/model/vka-vka.model';
import { VkaVkaService } from './vka-vka.service';

@Component({
    selector: 'jhi-vka-vka-delete-dialog',
    templateUrl: './vka-vka-delete-dialog.component.html'
})
export class VkaVkaDeleteDialogComponent {
    vka: IVkaVka;

    constructor(protected vkaService: VkaVkaService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.vkaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'vkaListModification',
                content: 'Deleted an vka'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-vka-vka-delete-popup',
    template: ''
})
export class VkaVkaDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ vka }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(VkaVkaDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.vka = vka;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
