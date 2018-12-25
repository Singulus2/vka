import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { VkaVka } from 'app/shared/model/vka-vka.model';
import { VkaVkaService } from './vka-vka.service';
import { VkaVkaComponent } from './vka-vka.component';
import { VkaVkaDetailComponent } from './vka-vka-detail.component';
import { VkaVkaUpdateComponent } from './vka-vka-update.component';
import { VkaVkaDeletePopupComponent } from './vka-vka-delete-dialog.component';
import { IVkaVka } from 'app/shared/model/vka-vka.model';

@Injectable({ providedIn: 'root' })
export class VkaVkaResolve implements Resolve<IVkaVka> {
    constructor(private service: VkaVkaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<VkaVka> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<VkaVka>) => response.ok),
                map((vka: HttpResponse<VkaVka>) => vka.body)
            );
        }
        return of(new VkaVka());
    }
}

export const vkaRoute: Routes = [
    {
        path: 'vka-vka',
        component: VkaVkaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'vkaApp.vka.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'vka-vka/:id/view',
        component: VkaVkaDetailComponent,
        resolve: {
            vka: VkaVkaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'vkaApp.vka.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'vka-vka/new',
        component: VkaVkaUpdateComponent,
        resolve: {
            vka: VkaVkaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'vkaApp.vka.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'vka-vka/:id/edit',
        component: VkaVkaUpdateComponent,
        resolve: {
            vka: VkaVkaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'vkaApp.vka.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const vkaPopupRoute: Routes = [
    {
        path: 'vka-vka/:id/delete',
        component: VkaVkaDeletePopupComponent,
        resolve: {
            vka: VkaVkaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'vkaApp.vka.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
