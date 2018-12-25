import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVkaVka } from 'app/shared/model/vka-vka.model';

@Component({
    selector: 'jhi-vka-vka-detail',
    templateUrl: './vka-vka-detail.component.html'
})
export class VkaVkaDetailComponent implements OnInit {
    vka: IVkaVka;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ vka }) => {
            this.vka = vka;
        });
    }

    previousState() {
        window.history.back();
    }
}
