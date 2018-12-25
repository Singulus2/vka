import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VkaSharedModule } from 'app/shared';
import {
    VkaVkaComponent,
    VkaVkaDetailComponent,
    VkaVkaUpdateComponent,
    VkaVkaDeletePopupComponent,
    VkaVkaDeleteDialogComponent,
    vkaRoute,
    vkaPopupRoute
} from './';

const ENTITY_STATES = [...vkaRoute, ...vkaPopupRoute];

@NgModule({
    imports: [VkaSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [VkaVkaComponent, VkaVkaDetailComponent, VkaVkaUpdateComponent, VkaVkaDeleteDialogComponent, VkaVkaDeletePopupComponent],
    entryComponents: [VkaVkaComponent, VkaVkaUpdateComponent, VkaVkaDeleteDialogComponent, VkaVkaDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VkaVkaVkaModule {}
