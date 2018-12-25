import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { VkaVkaVkaModule } from './vka-vka/vka-vka.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        VkaVkaVkaModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VkaEntityModule {}
