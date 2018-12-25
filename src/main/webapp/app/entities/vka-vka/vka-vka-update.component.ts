import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IVkaVka } from 'app/shared/model/vka-vka.model';
import { VkaVkaService } from './vka-vka.service';

@Component({
    selector: 'jhi-vka-vka-update',
    templateUrl: './vka-vka-update.component.html'
})
export class VkaVkaUpdateComponent implements OnInit {
    vka: IVkaVka;
    isSaving: boolean;

    constructor(protected vkaService: VkaVkaService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ vka }) => {
            this.vka = vka;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.vka.id !== undefined) {
            this.subscribeToSaveResponse(this.vkaService.update(this.vka));
        } else {
            this.subscribeToSaveResponse(this.vkaService.create(this.vka));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IVkaVka>>) {
        result.subscribe((res: HttpResponse<IVkaVka>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
