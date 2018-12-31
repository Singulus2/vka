import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IVkaVka } from 'app/shared/model/vka-vka.model';
import { AccountService } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { VkaVkaService } from './vka-vka.service';

@Component({
    selector: 'jhi-vka-vka',
    templateUrl: './vka-vka.component.html'
})
export class VkaVkaComponent implements OnInit, OnDestroy {
    currentAccount: any;
    vkas: IVkaVka[];
    error: any;
    success: any;
    eventSubscriber: Subscription;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;
    criteria: any;

    constructor(
        protected vkaService: VkaVkaService,
        protected parseLinks: JhiParseLinks,
        protected jhiAlertService: JhiAlertService,
        protected accountService: AccountService,
        protected activatedRoute: ActivatedRoute,
        protected router: Router,
        protected eventManager: JhiEventManager
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
       this.criteria = {
            mkt1: null,
            vnr: null,
            wirkungGueltigAb: null,
            wirkungGueltigBis: null,
            areSet() {
                return this.mkt1 != null || this.vnr != null || this.wirkungGueltigAb != null || this.wirkungGueltigBis != null;
            },
            clear() {
                this.mkt1 = null;
                this.vnr = null;
                this.wirkungDatAb = null;
                this.wirkungDatBis = null;
            }
        };
     }
        loadAll() {
         const criteria = [];

        if (this.criteria.areSet()) {
            if (this.criteria.mkt1 != null && this.criteria.mkt1 !== '') {
                criteria.push({key: 'mkt1.equals', value: this.criteria.mkt1});
            }
            if (this.criteria.vnr != null && this.criteria.vnr >= 0) {
                criteria.push({key: 'vnr.equals', value: this.criteria.vnr});
            }
           if (this.criteria.wirkungGueltigAb != null && this.criteria.wirkungGueltigAb >= 0) {
                criteria.push({key: 'wirkungGueltigAb.equals', value: this.criteria.wirkungGueltigAb});
            }
           if (this.criteria.wirkungGueltigBis != null && this.criteria.wirkungGueltigBis >= 0) {
                criteria.push({key: 'wirkungGueltigBis.equals', value: this.criteria.wirkungGueltigBis});
            }
        }
            this.vkaService
            .query({
                criteria,
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IVkaVka[]>) => this.paginateVkas(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(['/vka-vka'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/vka-vka',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.criteria.clear();
        this.loadAll();
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInVkas();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IVkaVka) {
        return item.id;
    }

    registerChangeInVkas() {
        this.eventSubscriber = this.eventManager.subscribe('vkaListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }
    search(criteria) {
        if (criteria.areSet()) {
            this.loadAll();
        }
    }

    paginateVkas(data: IVkaVka[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.vkas = data;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
