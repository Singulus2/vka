/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { VkaTestModule } from '../../../test.module';
import { VkaVkaDetailComponent } from 'app/entities/vka-vka/vka-vka-detail.component';
import { VkaVka } from 'app/shared/model/vka-vka.model';

describe('Component Tests', () => {
    describe('VkaVka Management Detail Component', () => {
        let comp: VkaVkaDetailComponent;
        let fixture: ComponentFixture<VkaVkaDetailComponent>;
        const route = ({ data: of({ vka: new VkaVka(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [VkaTestModule],
                declarations: [VkaVkaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(VkaVkaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(VkaVkaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.vka).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
